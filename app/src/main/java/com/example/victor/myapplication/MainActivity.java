package com.example.victor.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText user,senha;
    private DatabaseHelper helper;
    private Usuario u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.user);
        senha = (EditText) findViewById(R.id.senha);

        helper = new DatabaseHelper(this);
    }

    public void loginB(View v) {
        if (user.getText().length() == 0 || senha.getText().length() == 0) {
            Toast.makeText(getApplication(), getString(R.string.camposVazios), Toast.LENGTH_SHORT).show();
        }
        else {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT _id_login,senha FROM usuario WHERE _id_login = ? AND senha = ?", new String[]{user.getText().toString(), senha.getText().toString()});
            if(cursor != null) {
                cursor.moveToFirst();
                if(cursor.getCount() > 0){
                    db.close();
                    cursor.close();
                    Toast.makeText(getApplication(), getString(R.string.sucessoLogin), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LogadoActivity.class);
                    intent.putExtra("Usuario", user.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplication(), getString(R.string.erroLogin), Toast.LENGTH_SHORT).show();
                }

            }

        }
    }
    public void addB(View v){
        Intent intent = new Intent(this, InserirActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }


}
