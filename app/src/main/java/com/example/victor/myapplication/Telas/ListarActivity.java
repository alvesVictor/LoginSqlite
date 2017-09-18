package com.example.victor.myapplication.Telas;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.victor.myapplication.Dao.UsuarioDao;
import com.example.victor.myapplication.R;

public class ListarActivity extends ListActivity {

    private UsuarioDao usuarioDao;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioDao = new UsuarioDao(getBaseContext());
        String[] origem ={"_id_login","nome","status", "tipo"};
        int[] destino = {R.id.login,R.id.nome, R.id.status, R.id.tipo};

        adapter = new SimpleAdapter(this,usuarioDao.listarUsuarios(),R.layout.activity_listar,origem,destino);

        setListAdapter(adapter);
    }
}
