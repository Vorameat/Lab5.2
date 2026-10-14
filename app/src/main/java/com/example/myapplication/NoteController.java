package com.example.myapplication;

import java.util.List;

public class NoteController {
    private User currentUser;

    public NoteController(User user) {
        this.currentUser = user;
    }

    public void addTextNote(String title, String content) {
        TextNote note = new TextNote(title, content);
        note.setUser(currentUser);
        currentUser.addNote(note);
    }

    public void addCheckListNote(String title, String itemsText) {
        CheckListNote note = new CheckListNote(title, itemsText, currentUser);
        currentUser.addNote(note);
    }

    public List<Note> getUserNotes() {
        return currentUser.getNotes();
    }

    public String getUserInfo() {
        return "Owner: " + currentUser.getName() + " (" + currentUser.getEmail() + ")";
    }
}