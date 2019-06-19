package com.example.projectppb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    protected Cursor cursor;
    TextView teksatas,teksKat,teksJudul,teksDue;
    private Spinner sp_kategori;
   // private String[] list = {"-Kategori-", "Penting Mendesak", "Penting Tidak Mendesak", "Tidak Penting Mendesak", "Tidak Penting Tidak Mendesak"};

    String[] daftar;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kategori",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);
        }

        teksatas = findViewById(R.id.txt_tambah_list);
        teksJudul= findViewById(R.id.txt_judul);
        teksDue = findViewById(R.id.txt_due);
        teksKat = findViewById(R.id.txt_spin);


        sp_kategori = (Spinner) findViewById(R.id.spinner);
        sp_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //teksKat.setText(list[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                teksKat.setText("");
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_kategori.setAdapter(adapter);

    }

    public void tambah(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM kegiatan",null);
        cursor.moveToLast();
        Log.d("tambah: ", String.valueOf(sp_kategori.getSelectedItemPosition())  );
        db.execSQL("insert into `kegiatan`(`id_kegiatan`,`nama`, `tgl`, `id_kategori`) values('" +
                cursor.getInt(0)+1+ "','" +
                teksJudul.getText().toString() + "','" +
                teksDue.getText().toString() + "','" +
                sp_kategori.getSelectedItemPosition()+1 + "')");
        Toast.makeText(getApplicationContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
        finish();
    }
}
