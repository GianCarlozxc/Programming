package com.dsa.app;

public class LessonItem {
    private final int id;
    private final String tag;
    private final String slideRef;
    private final String title;
    private final String description;
    private final String theory;

    // Console Code
    private final String easyCode;
    private final String easyOutput;
    private final String mediumCode;
    private final String mediumOutput;
    private final String mediumInputHint;
    private final String mediumDefaultInput;
    private final String advanceCode;
    private final String advanceOutput;
    private final String advanceInputHint;
    private final String advanceDefaultInput;

    // GUI (JOptionPane) Code & Output
    private final String easyGuiCode;
    private final String easyGuiOutput;
    private final String mediumGuiCode;
    private final String mediumGuiOutput;
    private final String mediumGuiInputHint;
    private final String mediumGuiDefaultInput;
    private final String advanceGuiCode;
    private final String advanceGuiOutput;
    private final String advanceGuiInputHint;
    private final String advanceGuiDefaultInput;

    // Overloaded constructor for lessons with shared or default GUI outputs
    public LessonItem(int id, String tag, String slideRef, String title, String description,
                      String theory,
                      String easyCode, String easyOutput,
                      String mediumCode, String mediumOutput, String mediumInputHint, String mediumDefaultInput,
                      String advanceCode, String advanceOutput, String advanceInputHint, String advanceDefaultInput,
                      String easyGuiCode, String mediumGuiCode, String advanceGuiCode) {
        this(id, tag, slideRef, title, description, theory,
                easyCode, easyOutput,
                mediumCode, mediumOutput, mediumInputHint, mediumDefaultInput,
                advanceCode, advanceOutput, advanceInputHint, advanceDefaultInput,
                easyGuiCode, easyOutput,
                mediumGuiCode, mediumOutput, mediumInputHint, mediumDefaultInput,
                advanceGuiCode, advanceOutput, advanceInputHint, advanceDefaultInput);
    }

    // Full constructor supporting custom GUI output and input specifications
    public LessonItem(int id, String tag, String slideRef, String title, String description,
                      String theory,
                      String easyCode, String easyOutput,
                      String mediumCode, String mediumOutput, String mediumInputHint, String mediumDefaultInput,
                      String advanceCode, String advanceOutput, String advanceInputHint, String advanceDefaultInput,
                      String easyGuiCode, String easyGuiOutput,
                      String mediumGuiCode, String mediumGuiOutput, String mediumGuiInputHint, String mediumGuiDefaultInput,
                      String advanceGuiCode, String advanceGuiOutput, String advanceGuiInputHint, String advanceGuiDefaultInput) {
        this.id = id;
        this.tag = tag;
        this.slideRef = slideRef;
        this.title = title;
        this.description = description;
        this.theory = theory;

        this.easyCode = easyCode;
        this.easyOutput = easyOutput;
        this.mediumCode = mediumCode;
        this.mediumOutput = mediumOutput;
        this.mediumInputHint = mediumInputHint;
        this.mediumDefaultInput = mediumDefaultInput;
        this.advanceCode = advanceCode;
        this.advanceOutput = advanceOutput;
        this.advanceInputHint = advanceInputHint;
        this.advanceDefaultInput = advanceDefaultInput;

        this.easyGuiCode = easyGuiCode;
        this.easyGuiOutput = easyGuiOutput;
        this.mediumGuiCode = mediumGuiCode;
        this.mediumGuiOutput = mediumGuiOutput;
        this.mediumGuiInputHint = mediumGuiInputHint;
        this.mediumGuiDefaultInput = mediumGuiDefaultInput;
        this.advanceGuiCode = advanceGuiCode;
        this.advanceGuiOutput = advanceGuiOutput;
        this.advanceGuiInputHint = advanceGuiInputHint;
        this.advanceGuiDefaultInput = advanceGuiDefaultInput;
    }

    public int getId() { return id; }
    public String getTag() { return tag; }
    public String getSlideRef() { return slideRef; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTheory() { return theory; }

    public String getEasyCode() { return easyCode; }
    public String getEasyOutput() { return easyOutput; }

    public String getMediumCode() { return mediumCode; }
    public String getMediumOutput() { return mediumOutput; }
    public String getMediumInputHint() { return mediumInputHint; }
    public String getMediumDefaultInput() { return mediumDefaultInput; }

    public String getAdvanceCode() { return advanceCode; }
    public String getAdvanceOutput() { return advanceOutput; }
    public String getAdvanceInputHint() { return advanceInputHint; }
    public String getAdvanceDefaultInput() { return advanceDefaultInput; }

    public String getEasyGuiCode() { return easyGuiCode; }
    public String getEasyGuiOutput() { return easyGuiOutput; }

    public String getMediumGuiCode() { return mediumGuiCode; }
    public String getMediumGuiOutput() { return mediumGuiOutput; }
    public String getMediumGuiInputHint() { return mediumGuiInputHint; }
    public String getMediumGuiDefaultInput() { return mediumGuiDefaultInput; }

    public String getAdvanceGuiCode() { return advanceGuiCode; }
    public String getAdvanceGuiOutput() { return advanceGuiOutput; }
    public String getAdvanceGuiInputHint() { return advanceGuiInputHint; }
    public String getAdvanceGuiDefaultInput() { return advanceGuiDefaultInput; }
}
