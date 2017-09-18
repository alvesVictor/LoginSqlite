package com.example.victor.myapplication.Telas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.myapplication.Bean.Usuario;
import com.example.victor.myapplication.Dao.UsuarioDao;
import com.example.victor.myapplication.R;

public class LogadoActivity extends AppCompatActivity {
    private Usuario user;
    private TextView u;
    private AlertDialog alerta;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        usuarioDao = new UsuarioDao(getBaseContext());

        Intent intent = getIntent();
        user = new Usuario();
        user.setUsuario(intent.getStringExtra("Usuario"));
        u = (TextView) findViewById(R.id.userLogado);
        u.setText(user.getUsuario());
    }

    public void sairButton(View v){
        finish();
    }

    public void inserirLButton(View v){
        Intent intent = new Intent(this, InserirActivity.class);
        startActivity(intent);
    }

    public void alterarButton(View v){
        Intent it = new Intent(this,AlterarActivity.class);
        it.putExtra("Usuario",user.getUsuario());
        startActivity(it);
    }

    public void listarButton(View v){
        Intent intent = new Intent(this, ListarActivity.class);
        startActivity(intent);
    }

    public void excluirButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Usuário");
        builder.setMessage("Deseja excluir esse usuário?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                if( usuarioDao.excluirUsuario(user)){
                    Toast.makeText(getApplication(), getString(R.string.deleteSucesso), Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplication(), getString(R.string.erroExcluir), Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplication(), getString(R.string.deleteCancela), Toast.LENGTH_SHORT).show();
            }
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

}
