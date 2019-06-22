package com.example.projectppb.activities;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectppb.R;
import com.example.projectppb.objects.DataHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Form extends AppCompatActivity {

    protected Cursor cursor;
    protected Cursor cursor1;
    TextView teksatas,teksKat,teksJudul,teksDue;
    private Spinner sp_kategori;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
   // private String[] list = {"-Kategori-", "Penting Mendesak", "Penting Tidak Mendesak", "Tidak Penting Mendesak", "Tidak Penting Tidak Mendesak"};

    String[] daftar;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

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
        teksJudul= findViewById(R.id.tambah_judul);
        teksDue = findViewById(R.id.tambah_due);
        teksKat = findViewById(R.id.txt_spin);

        teksDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });


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
        Home.activity.showTask(false);
        finish();
    }
    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                teksDue.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
