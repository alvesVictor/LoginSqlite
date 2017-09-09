package com.example.victor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogadoActivity extends AppCompatActivity {
    private String user;
    private TextView u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        Intent intent = getIntent();
        user = intent.getStringExtra("Usuario");
        u = (TextView) findViewById(R.id.userLogado);
        u.setText(user);
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
        it.putExtra("Usuario",user);
        startActivity(it);
    }

}
