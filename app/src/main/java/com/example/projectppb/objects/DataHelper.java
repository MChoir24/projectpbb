package com.example.projectppb.objects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "reminder";
    private static final int DATABASE_VERSION = 1;

//    String sql;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE `kegiatan` (`id_kegiatan` int(11) PRIMARY KEY NOT NULL,`nama` varchar(45) NOT NULL,`tgl` datetime NOT NULL,`id_kategori` int(11) NOT NULL);" ;
        db.execSQL(sql);

        String sql1 = "CREATE TABLE `kategori` (  `id_kategori` int(11) PRIMARY KEY NOT NULL,  `nama` varchar(45) NOT NULL,  `prioritas` int(11) NOT NULL,FOREIGN KEY (id_kategori) REFERENCES kategori(id_kategori));";
        db.execSQL(sql1);

        String sql2 = "INSERT INTO `kategori`(`id_kategori`, `nama`, `prioritas`) VALUES (1,'penting dan mendesak',1);" ;
        String sql3 = "INSERT INTO `kategori`(`id_kategori`, `nama`, `prioritas`) VALUES (2,'penting dan tidak mendesak',2);" ;
        String sql4 = "INSERT INTO `kategori`(`id_kategori`, `nama`, `prioritas`) VALUES (3,'tidak penting dan mendesak',3);" ;
        String sql5 = "INSERT INTO `kategori`(`id_kategori`, `nama`, `prioritas`) VALUES (4,'tidak penting dan tidak mendesak',4);";
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);

        String sql6 = "INSERT INTO `kegiatan`(`id_kegiatan`,`nama`, `tgl`, `id_kategori`) VALUES(4,'buang air','2019-5-5 15:06:00', 1);" ;
        String sql7 = "INSERT INTO `kegiatan`(`id_kegiatan`,`nama`, `tgl`, `id_kategori`) VALUES(2,'makan','2019-5-5 15:06:00', 2);";
        String sql8 = "INSERT INTO `kegiatan`(`id_kegiatan`,`nama`, `tgl`, `id_kategori`) VALUES(3,'deadline','2019-5-5 15:06:00', 3);" ;
        String sql9 = "INSERT INTO `kegiatan`(`id_kegiatan`,`nama`, `tgl`, `id_kategori`) VALUES(1,'mandi','2019-1-5 15:06:00', 4);";
        db.execSQL(sql6);
        db.execSQL(sql7);
        db.execSQL(sql8);
        db.execSQL(sql9);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
