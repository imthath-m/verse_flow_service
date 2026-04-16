package com.verseflow.service.dto.quran;

import java.util.List;

public class Surah {
    private int number;
    private String name;
    private String englishName;
    private String englishNameTranslation;
    private int numberOfAyahs;
    private String revelationType;
    private List<Verse> ayahs;

    // Getters and Setters
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEnglishName() { return englishName; }
    public void setEnglishName(String englishName) { this.englishName = englishName; }

    public String getEnglishNameTranslation() { return englishNameTranslation; }
    public void setEnglishNameTranslation(String englishNameTranslation) { this.englishNameTranslation = englishNameTranslation; }

    public int getNumberOfAyahs() { return numberOfAyahs; }
    public void setNumberOfAyahs(int numberOfAyahs) { this.numberOfAyahs = numberOfAyahs; }

    public String getRevelationType() { return revelationType; }
    public void setRevelationType(String revelationType) { this.revelationType = revelationType; }

    public List<Verse> getAyahs() { return ayahs; }
    public void setAyahs(List<Verse> ayahs) { this.ayahs = ayahs; }
}
