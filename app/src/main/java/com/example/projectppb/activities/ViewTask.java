package com.example.projectppb.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectppb.R;
import com.example.projectppb.objects.DataHelper;
import com.example.projectppb.objects.Task;

public class ViewTask extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra";
    TextView tv_judul;
    TextView tv_due;
    TextView tv_kategori;

    protected Cursor cursor;
    protected Cursor cursor1;
    DataHelper dbcenter;
    Task task;
    public static ViewTask activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        dbcenter = new DataHelper(this);
        task = getIntent().getParcelableExtra(EXTRA_PERSON);

        activity = this;

        SQLiteDatabase db = dbcenter.getReadableDatabase();

        tv_judul = findViewById(R.id.tv_judul);
        tv_due = findViewById(R.id.tv_tgl);
        tv_kategori = findViewById(R.id.tv_Kategori);

        setT();
    }
    public void setT(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kegiatan where id_kegiatan = "+task.getId(),null);
        cursor.moveToFirst();
        cursor1 = db.rawQuery("SELECT nama FROM kategori where id_kategori = "+cursor.getInt(3),null);
        cursor1.moveToFirst();

        tv_judul.setText(cursor.getString(1));
        tv_due.setText(cursor.getString(2));
        tv_kategori.setText(cursor1.getInt(0));
    }

    public void deleteId(int id){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        db.execSQL("delete FROM kegiatan where id_kegiatan = '"+id+"';");
        Toast.makeText(getApplicationContext(),"Berhasil  dihapus.",Toast.LENGTH_SHORT).show();
        TaskActivity.activity.showTask(task.getKategori());
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_viewtask, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete:
                deleteId(task.getId());
                return true;
            case R.id.edit:
                Intent intent = new Intent(this, editTask.class);
                intent.putExtra(editTask.EXTRA_PERSON, task);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
