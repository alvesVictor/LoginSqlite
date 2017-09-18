package com.example.victor.myapplication.Telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.myapplication.Bean.Usuario;
import com.example.victor.myapplication.Dao.UsuarioDao;
import com.example.victor.myapplication.R;

public class InserirActivity extends AppCompatActivity {
    private EditText login,senha,nome,status,tipo;
    private Usuario usu;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        usuarioDao = new UsuarioDao(getBaseContext());

        login = (EditText) findViewById(R.id.inserirUsu);
        senha = (EditText) findViewById(R.id.inserirSenha);
        nome = (EditText) findViewById(R.id.inserirNome);
        status = (EditText) findViewById(R.id.inserirStatus);
        tipo = (EditText) findViewById(R.id.inserirTipo);
    }

    public void cancelarButton(View v){
        finish();
    }

    public void cadastrarButton(View v){
        if (login.getText().length() == 0 || senha.getText().length() == 0) {
            Toast.makeText(getApplication(), getString(R.string.camposObrigatorios), Toast.LENGTH_SHORT).show();
        }
        else{
            usu = new Usuario(login.getText().toString(),senha.getText().toString(),nome.getText().toString(),tipo.getText().toString(),status.getText().toString());
            if(usuarioDao.inserirUsuario(usu)){
                Toast.makeText(getApplication(), getString(R.string.sucessoCadastro), Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Toast.makeText(getApplication(), getString(R.string.erroCadastro), Toast.LENGTH_LONG).show();
            }
        }
    }
}
