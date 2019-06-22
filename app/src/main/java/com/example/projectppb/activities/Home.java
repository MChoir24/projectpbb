package com.example.projectppb.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.projectppb.R;
import com.example.projectppb.adapters.KategoriAdapter;
import com.example.projectppb.adapters.TaskAdapter;
import com.example.projectppb.objects.DataHelper;
import com.example.projectppb.objects.Kategori;
import com.example.projectppb.objects.Task;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KategoriAdapter adapter;
    private TaskAdapter adapter1;
    private RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;

    public static ArrayList<Kategori> kategoris;
    public static ArrayList<Task> tasks;

    public static Home activity;

    protected Cursor cursor;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = this;

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent =  new Intent(getApplicationContext(), Form.class);
                startActivity(intent);
            }
        });


        dbcenter = new DataHelper(this);
        showKategori();

    }

    public void showTask(boolean sortDate) {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        if (sortDate){
            cursor = db.query("kegiatan",null,null,null,null,null,"id_kegiatan ASC");
//            Toast.makeText(getApplicationContext(),"true.",Toast.LENGTH_SHORT).show();
            Log.d("mengecek", "showTask: true");

        }else {
            cursor = db.rawQuery("SELECT * FROM kegiatan ",null);
//            Toast.makeText(getApplicationContext(),"false.",Toast.LENGTH_SHORT).show();
            Log.d("mengecek", "showTask: false");
        }

        tasks = new ArrayList<>();
        Task task;
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            task = new Task(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            tasks.add(task);
        }
        recyclerView = findViewById(R.id.list_home);

        adapter1 = new TaskAdapter(this, tasks);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter1);
    }

    public void showKategori(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kategori",null);
        kategoris =new ArrayList<>();
        Kategori kategori;
        cursor.moveToFirst();
        for (int i = 0 ; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            kategori = new Kategori(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            kategoris.add(kategori);
        }
        recyclerView = findViewById(R.id.list_home);

        adapter = new KategoriAdapter(this,kategoris);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.kategori:
                showKategori();
                return true;
            case R.id.kegiatan:
                showTask(false);
                return true;
            case R.id.jatuhTempo:
                showTask(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
