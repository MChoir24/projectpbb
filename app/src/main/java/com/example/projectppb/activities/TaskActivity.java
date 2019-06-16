package com.example.projectppb.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.projectppb.R;
import com.example.projectppb.adapters.TaskAdapter;
import com.example.projectppb.objects.DataHelper;
import com.example.projectppb.objects.Kategori;
import com.example.projectppb.objects.Task;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra";

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static ArrayList<Task> tasks;
    protected Cursor cursor;
    DataHelper dbcenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        dbcenter = new DataHelper(this);

        Kategori kategori = getIntent().getParcelableExtra(EXTRA_PERSON);
        showTask(kategori.getId());
    }

    private void showTask(int idKategori) {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kegiatan where id_kategori = "+idKategori,null);
        tasks = new ArrayList<>();
        Task task;
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            task = new Task(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            tasks.add(task);
        }
        recyclerView = findViewById(R.id.listTask);

        adapter = new TaskAdapter(this, tasks);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
