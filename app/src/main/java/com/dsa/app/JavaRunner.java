package com.dsa.app;

import bsh.Interpreter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class JavaRunner {

    public static class ExecutionResult {
        public final boolean success;
        public final String output;
        public final String error;
        public final long executionTimeMs;

        public ExecutionResult(boolean success, String output, String error, long executionTimeMs) {
            this.success = success;
            this.output = output;
            this.error = error;
            this.executionTimeMs = executionTimeMs;
        }
    }

    public static ExecutionResult execute(String rawCode, String stdinInput, DialogBridge.Listener dialogListener) {
        if (rawCode == null || rawCode.trim().isEmpty()) {
            return new ExecutionResult(false, "", "No Java code provided to execute.", 0);
        }

        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
        PrintStream printStream;
        try {
            printStream = new PrintStream(capturedOut, true, "UTF-8");
        } catch (Exception e) {
            printStream = new PrintStream(capturedOut, true);
        }

        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        InputStream originalIn = System.in;

        if (dialogListener != null) {
            DialogBridge.setListener(dialogListener);
        }

        String inputData = stdinInput != null ? stdinInput : "";
        ByteArrayInputStream customIn = new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8));

        System.setOut(printStream);
        System.setErr(printStream);
        System.setIn(customIn);

        boolean success = false;
        String errorMsg = null;

        try {
            String processedCode = prepareCode(rawCode);

            Interpreter interpreter = new Interpreter();
            // Pre-import common Java packages
            interpreter.eval("import java.util.*;");
            interpreter.eval("import java.io.*;");
            interpreter.eval("import java.math.*;");
            interpreter.eval("import java.text.*;");
            interpreter.eval("import javax.swing.*;");
            interpreter.eval("import javax.swing.JOptionPane;");

            interpreter.eval(processedCode);
            success = true;
        } catch (Throwable t) {
            success = false;
            errorMsg = formatError(t);
            printStream.println("\n[Execution Error]: " + errorMsg);
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
            System.setIn(originalIn);
            DialogBridge.setListener(null);
        }

        long duration = System.currentTimeMillis() - startTime;
        String finalOutput = capturedOut.toString().trim();

        return new ExecutionResult(success, finalOutput, errorMsg, duration);
    }

    public static String prepareCode(String code) {
        String sanitized = sanitizeGenerics(code);

        // Separate and hoist imports to top
        StringBuilder imports = new StringBuilder();
        StringBuilder nonImports = new StringBuilder();
        String[] lines = sanitized.split("\\r?\\n");
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.startsWith("import ") && trimmed.endsWith(";")) {
                imports.append(trimmed).append("\n");
            } else {
                nonImports.append(line).append("\n");
            }
        }

        String content = nonImports.toString().trim();

        // Android's Dalvik/ART does not support BeanShell runtime bytecode class generation.
        // If the user code defines a class (e.g., 'public class MediumRestaurant { ... }'),
        // unwrap the outer class definition so BeanShell interprets the methods and statements directly.
        Pattern outerClassPattern = Pattern.compile("^(?:public\\s+|private\\s+|protected\\s+|static\\s+|final\\s+)*class\\s+([A-Za-z0-9_]+)[^{]*\\{([\\s\\S]*)\\}\\s*$", Pattern.DOTALL);
        Matcher outerClassMatcher = outerClassPattern.matcher(content);

        if (outerClassMatcher.matches()) {
            content = outerClassMatcher.group(2).trim();
        } else {
            // Also handle class with trailing content or multiple classes: strip class header and matching closing brace if present
            Pattern anyClassPattern = Pattern.compile("(?:public\\s+|private\\s+|protected\\s+|static\\s+|final\\s+)*class\\s+([A-Za-z0-9_]+)[^{]*\\{");
            Matcher anyMatcher = anyClassPattern.matcher(content);
            if (anyMatcher.find()) {
                int openIdx = anyMatcher.end() - 1;
                int closeIdx = content.lastIndexOf('}');
                if (closeIdx > openIdx) {
                    content = content.substring(openIdx + 1, closeIdx).trim();
                }
            }
        }

        // If there is a main method (e.g. 'public static void main(String[] args)'), invoke it automatically
        Pattern mainPattern = Pattern.compile("public\\s+static\\s+void\\s+main\\s*\\(");
        if (mainPattern.matcher(content).find()) {
            content = content + "\n\nmain(new String[0]);\n";
        }

        return imports.toString() + "\n" + content;
    }

    public static String sanitizeGenerics(String code) {
        // Strip diamond operator: <>
        String s = code.replaceAll("<\\s*>", "");
        // Strip single and nested generics: <String>, <Integer, String>, etc.
        for (int i = 0; i < 3; i++) {
            s = s.replaceAll("<\\s*[A-Za-z0-9_?.\\[\\]\\s,]+>", "");
        }
        return s;
    }

    private static String formatError(Throwable t) {
        String msg = t.getMessage();
        if (msg == null || msg.trim().isEmpty()) {
            msg = t.getClass().getSimpleName();
        }
        return msg;
    }
}
