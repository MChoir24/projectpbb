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

import com.example.projectppb.R;
import com.example.projectppb.objects.DataHelper;
import com.example.projectppb.objects.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class editTask extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra";
    protected Cursor cursor;
    TextView teksatas,teksKat,teksJudul,teksDue;
    private Spinner sp_kategori;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    String[] daftar;
    DataHelper dbHelper;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        task = getIntent().getParcelableExtra(EXTRA_PERSON);

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

        teksJudul.setText(task.getNama());
        teksDue.setText(task.getTgl());

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

    public void editId(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d("tambah: ", String.valueOf(sp_kategori.getSelectedItemPosition())  );
        db.execSQL("update kegiatan set nama = '"+teksJudul.getText().toString()+"'," +
                "tgl = '"+teksDue.getText().toString()+"'," +
                "id_kategori = '"+sp_kategori.getSelectedItemPosition()+1+"'" +
                "where id_kegiatan = "+ task.getId());
        ViewTask.activity.setT();
        finish();
    }
}
