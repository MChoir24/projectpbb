package com.example.projectppb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private String[] list = {"-Kategori-", "Penting Mendesak", "Penting Tidak Mendesak", "Tidak Penting Mendesak", "Tidak Penting Tidak Mendesak"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        teksatas = (TextView) findViewById(R.id.txt_tambah_list);
        teksJudul= (TextView) findViewById(R.id.txt_judul);
        teksDue = (TextView) findViewById(R.id.txt_due);
        teksKat = (TextView) findViewById(R.id.txt_spin);

        sp_kategori = (Spinner) findViewById(R.id.spinner);
        sp_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teksKat.setText(list[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                teksKat.setText("");
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_kategori.setAdapter(adapter);

    }

    public void buttonPesan(View view) {
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        //db.execSQL("insert into note(judul, kategori, due) values('" +
                //teksJudul.getText().toString() + "','" +
                //teksDue.getText().toString() + "','" +
                //sp_kategori.getSelectedItemId() + "')");
        //Toast.makeText(getApplicationContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
        finish();
    }
}
