package com.verseflow.service.dto.quran;

public class Translation {
    private String language;
    private String text;
    private String targetTranslator;

    // Getters and Setters
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getTargetTranslator() { return targetTranslator; }
    public void setTargetTranslator(String targetTranslator) { this.targetTranslator = targetTranslator; }
}
