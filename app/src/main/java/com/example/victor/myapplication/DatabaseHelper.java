package com.example.victor.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Victor on 06/09/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BANCO_DADOS = "Projeto";
        private static int VERSAO = 1;
        public DatabaseHelper(Context context) {
            super(context, BANCO_DADOS, null, VERSAO);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE usuario (_id_login TEXT PRIMARY KEY,senha TEXT,nome TEXT,status TEXT,tipo TEXT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {

        }

}
