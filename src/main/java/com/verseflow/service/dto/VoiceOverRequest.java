package com.verseflow.service.dto;

import jakarta.validation.constraints.NotBlank;

public class VoiceOverRequest {

    @NotBlank
    private String textId;

    @NotBlank
    private String text;

    @NotBlank
    private String language;

    // Getters and Setters
    public String getTextId() { return textId; }
    public void setTextId(String textId) { this.textId = textId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
