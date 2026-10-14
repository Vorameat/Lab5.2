package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrowseNoteActivity extends AppCompatActivity {
    private EditText edtSearch;
    private Button btnSearch;
    private ProgressBar progressBar;
    private TextView txtSearchResult;
    private NoteController noteController;

    private ExecutorService executorService;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_note);

        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        progressBar = findViewById(R.id.progressBarBrowse);
        txtSearchResult = findViewById(R.id.txtSearchResult);

        User currentUser = new User("Vorameat", "vorameat@email.com");
        noteController = new NoteController(currentUser);

        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        btnSearch.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        progressBar.setVisibility(View.VISIBLE);
        txtSearchResult.setText("");
        btnSearch.setEnabled(false);

        executorService.execute(() -> {
            try {
                // ดีเลย์ 2 วินาที (Multitasking Threading)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainHandler.post(() -> {
                progressBar.setVisibility(View.GONE);
                btnSearch.setEnabled(true);
                txtSearchResult.setText("ไม่พบข้อมูล");
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}