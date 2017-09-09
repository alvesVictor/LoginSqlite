package com.example.victor.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InserirActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private EditText login,senha,nome,status,tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        login = (EditText) findViewById(R.id.inserirUsu);
        senha = (EditText) findViewById(R.id.inserirSenha);
        nome = (EditText) findViewById(R.id.inserirNome);
        status = (EditText) findViewById(R.id.inserirStatus);
        tipo = (EditText) findViewById(R.id.inserirTipo);

        helper = new DatabaseHelper(this);
    }

    public void cancelarButton(View v){
        finish();
    }

    public void cadastrarButton(View v){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("_id_login",login.getText().toString());
        values.put("senha",senha.getText().toString());
        values.put("nome",nome.getText().toString());
        values.put("status",status.getText().toString());
        values.put("tipo",tipo.getText().toString());

        long resul = db.insert("usuario",null,values);
        if(resul != -1 ){
            Toast.makeText(getApplication(), getString(R.string.sucessoCadastro), Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(getApplication(), getString(R.string.erroCadastro), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }

}
