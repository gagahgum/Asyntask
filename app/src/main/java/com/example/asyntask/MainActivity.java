package com.example.asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txtProgress;
    Button btnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        txtProgress = findViewById(R.id.txtProgress);
        btnProgress = findViewById(R.id.btnProgress);
        btnProgress.setText("START");
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                new MyTask().execute(15);
            }
        });
    }
    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            int count = params[0];
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                    publishProgress((int) (((i + 1) / (float) count) * 100));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            txtProgress.setText(result);
            btnProgress.setText("Restart");
        }
        @Override
        protected void onPreExecute() {
            txtProgress.setText("Task Starting...");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            txtProgress.setText("Running..."+ values[0]);
            progressBar.setProgress(values[0]);
        }
    }
}
