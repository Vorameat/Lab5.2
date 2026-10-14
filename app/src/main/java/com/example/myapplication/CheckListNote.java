package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckListNote extends Note {
    private List<String> items;

    public CheckListNote() {
        super();
        this.items = new ArrayList<>();
    }

    public CheckListNote(String title, List<String> items) {
        super(title, "", null); // แก้จาก super(title) เป็น super(title, "", null) เพื่อให้ตรงกับ Note
        this.items = items;
    }

    public CheckListNote(String title, String itemsText, User user) {
        super(title, "", user);
        if (itemsText != null && !itemsText.trim().isEmpty()) {
            this.items = Arrays.asList(itemsText.split(","));
        } else {
            this.items = new ArrayList<>();
        }
    }

    @Override
    public String getSummary() {
        String itemsText = (items != null) ? String.join(", ", items) : "";
        return "Title: " + getTitle() + "\nItems: " + itemsText;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}