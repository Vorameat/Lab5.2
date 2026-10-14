package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Button btnAddNote;
    private Button btnBrowseNote; // เพิ่ม Attribute
    private ProgressBar progressBar; // เพิ่ม Attribute
    private TextView tvUserInfo, tvNoteList;
    private NoteController noteController;

    private ExecutorService executorService;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNote = findViewById(R.id.btnAddNote);
        btnBrowseNote = findViewById(R.id.btnBrowseNote);
        progressBar = findViewById(R.id.progressBarMain);
        tvUserInfo = findViewById(R.id.tvUserInfo);
        tvNoteList = findViewById(R.id.tvNoteList);

        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        User currentUser = new User("Vorameat", "vorameat@email.com");
        noteController = new NoteController(currentUser);

        tvUserInfo.setText(noteController.getUserInfo());

        btnAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        btnBrowseNote.setOnClickListener(v -> loadBrowseNotePage());
    }

    private void loadBrowseNotePage() { // เพิ่ม Method
        progressBar.setVisibility(View.VISIBLE);
        btnBrowseNote.setEnabled(false);

        executorService.execute(() -> {
            try {
                // ดีเลย์ 2 วินาที (Multitasking Threading)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainHandler.post(() -> {
                progressBar.setVisibility(View.GONE);
                btnBrowseNote.setEnabled(true);

                Intent intent = new Intent(MainActivity.this, BrowseNoteActivity.class);
                startActivity(intent);
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNotes();
    }

    private void displayNotes() {
        StringBuilder builder = new StringBuilder();
        for (Note note : noteController.getUserNotes()) {
            builder.append("- ").append(note.getTitle()).append("\n");
        }

        if (builder.length() == 0) {
            tvNoteList.setText("ยังไม่มีรายการโน้ต");
        } else {
            tvNoteList.setText(builder.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}