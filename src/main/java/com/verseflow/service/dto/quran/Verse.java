package com.verseflow.service.dto.quran;

public class Verse {
    private int number;
    private String text;
    private int numberInSurah;
    private int juz;
    private int manzil;
    private int page;
    private int ruku;
    private int hizbQuarter;

    // Getters and Setters
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getNumberInSurah() { return numberInSurah; }
    public void setNumberInSurah(int numberInSurah) { this.numberInSurah = numberInSurah; }

    public int getJuz() { return juz; }
    public void setJuz(int juz) { this.juz = juz; }

    public int getManzil() { return manzil; }
    public void setManzil(int manzil) { this.manzil = manzil; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getRuku() { return ruku; }
    public void setRuku(int ruku) { this.ruku = ruku; }

    public int getHizbQuarter() { return hizbQuarter; }
    public void setHizbQuarter(int hizbQuarter) { this.hizbQuarter = hizbQuarter; }
}
