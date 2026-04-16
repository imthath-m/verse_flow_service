package com.verseflow.service.dto;

public class VoiceOverResponse {

    private String textId;
    private String status;
    private String audioUrl;
    private String format;
    private boolean generated;

    // Getters and Setters
    public String getTextId() { return textId; }
    public void setTextId(String textId) { this.textId = textId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public boolean isGenerated() { return generated; }
    public void setGenerated(boolean generated) { this.generated = generated; }
}
