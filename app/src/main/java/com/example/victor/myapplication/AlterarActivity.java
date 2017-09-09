package com.example.victor.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlterarActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private EditText senha,nome,status,tipo;
    private TextView login;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        login = (TextView) findViewById(R.id.alterarUsu);

        senha = (EditText) findViewById(R.id.alterarSenha);
        nome = (EditText) findViewById(R.id.alterarNome);
        status = (EditText) findViewById(R.id.alterarStatus);
        tipo = (EditText) findViewById(R.id.alterarTipo);

        Intent intent = getIntent();
        user = intent.getStringExtra("Usuario");
        login.setText(user);

        helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE _id_login = ?", new String[]{user});
        if(cursor != null) {
            cursor.moveToFirst();
            if(cursor.getCount() > 0){
                senha.setText(cursor.getString(1));
                nome.setText(cursor.getString(2));
                status.setText(cursor.getString(3));
                tipo.setText(cursor.getString(4));
                db.close();
                cursor.close();
            }
            else {
                Toast.makeText(getApplication(),"Deu Merda", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void alterarButton(View v){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("senha",senha.getText().toString());
        values.put("nome",nome.getText().toString());
        values.put("status",status.getText().toString());
        values.put("tipo",tipo.getText().toString());
        long resul = db.update("usuario",values,"_id_login = ?",new String[]{user});
        db.close();
        if(resul != -1 ){
            Toast.makeText(getApplication(), getString(R.string.sucessoAlterar), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(getApplication(), getString(R.string.erroAlterar), Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelarButton(View v){
        finish();
    }
    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
