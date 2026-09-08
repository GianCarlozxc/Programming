package com.dsa.app;

public class ReviewerItem {
    public enum Type {
        CONCEPT,
        FORMULA,
        QUIZ
    }

    private final int lessonId;
    private final String lessonTag;
    private final String topic;
    private final Type type;
    private final String question;
    private final String answer;
    private final String explanation;
    private final String[] quizOptions;
    private final int correctOptionIndex;

    // For Concepts / Flashcards / Formula cards
    public ReviewerItem(int lessonId, String lessonTag, String topic, Type type, String question, String answer, String explanation) {
        this(lessonId, lessonTag, topic, type, question, answer, explanation, null, -1);
    }

    // For Quiz questions
    public ReviewerItem(int lessonId, String lessonTag, String topic, Type type, String question, String answer, String explanation, String[] quizOptions, int correctOptionIndex) {
        this.lessonId = lessonId;
        this.lessonTag = lessonTag;
        this.topic = topic;
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.explanation = explanation;
        this.quizOptions = quizOptions;
        this.correctOptionIndex = correctOptionIndex;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getLessonTag() {
        return lessonTag;
    }

    public String getTopic() {
        return topic;
    }

    public Type getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String[] getQuizOptions() {
        return quizOptions;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
