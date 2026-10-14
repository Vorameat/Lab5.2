package com.example.myapplication;

public class TextNote extends Note {
    private String content;

    public TextNote() {
        super();
        this.content = "";
    }

    public TextNote(String title, String content) {
        super(title, "", null);
        this.content = content;
    }

    public TextNote(String title, String createdDate, String content, User user) {
        super(title, createdDate, user);
        this.content = content;
    }

    @Override
    public String getSummary() {
        return "Title: " + getTitle() + "\nContent: " + content + "\nDate: " + getCreatedDate();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}