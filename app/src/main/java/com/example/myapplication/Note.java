package com.example.myapplication;

public abstract class Note {
    protected String title;
    protected String createdDate;
    protected User user;

    public Note() {
        this.title = "";
        this.createdDate = "";
    }

    public Note(String title, String createdDate, User user) {
        this.title = title;
        this.createdDate = createdDate;
        this.user = user;
    }

    // เปลี่ยน void เป็น String บรรทัดนี้ครับ
    public abstract String getSummary();

    // Getter & Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}