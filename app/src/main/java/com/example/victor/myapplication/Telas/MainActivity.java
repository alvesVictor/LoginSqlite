package com.example.victor.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.myapplication.Bean.Usuario;
import com.example.victor.myapplication.Dao.UsuarioDao;
import com.example.victor.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private EditText user,senha;
    private Usuario usu;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarioDao = new UsuarioDao(getBaseContext());

        user = (EditText) findViewById(R.id.user);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void loginB(View v) {
        if (user.getText().length() == 0 || senha.getText().length()== 0) {
            Toast.makeText(getApplication(), getString(R.string.camposVazios), Toast.LENGTH_SHORT).show();
        }
        else{
            usu = new Usuario(user.getText().toString(),senha.getText().toString());
            if(usuarioDao.validarUsuario(usu)){
                Toast.makeText(getApplication(), getString(R.string.sucessoLogin), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LogadoActivity.class);
                intent.putExtra("Usuario", usu.getUsuario());
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplication(), getString(R.string.erroLogin), Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void addB(View v){
        Intent intent = new Intent(this, InserirActivity.class);
        startActivity(intent);
    }

}
