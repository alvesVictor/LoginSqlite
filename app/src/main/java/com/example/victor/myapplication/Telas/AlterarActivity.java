package com.example.victor.myapplication.Telas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.myapplication.Bean.Usuario;
import com.example.victor.myapplication.Dao.DatabaseHelper;
import com.example.victor.myapplication.Dao.UsuarioDao;
import com.example.victor.myapplication.R;

public class AlterarActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private EditText senha,nome,status,tipo;
    private TextView login;
    private String user;
    private UsuarioDao usuarioDao;
    private Usuario usu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        usuarioDao = new UsuarioDao(getBaseContext());

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
        usu = new Usuario(login.getText().toString(),senha.getText().toString(),nome.getText().toString(),tipo.getText().toString(),status.getText().toString());
        if (usuarioDao.alterarUsuario(usu)){
            Toast.makeText(getApplication(), getString(R.string.sucessoAlterar), Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
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
