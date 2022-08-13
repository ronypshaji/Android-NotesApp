package com.example.test_2;

public class notes_model {
    private String category;

    public notes_model(String category) {
        this.category = category;

    }

    public notes_model( String note, int id) {
        this.note = note;
        this.id = id;
    }

    private String note;
    private int id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "notes_model{" +
                "category='" + category + '\'' +
                ", note='" + note + '\'' +
                ", id=" + id +
                '}';
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
