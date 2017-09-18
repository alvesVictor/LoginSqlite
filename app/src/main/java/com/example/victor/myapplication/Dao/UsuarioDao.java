package com.example.victor.myapplication.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.victor.myapplication.Bean.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 14/09/2017.
 */

public class UsuarioDao {
    private DatabaseHelper helper;
    private List<Map<String,Object>> usuarios;

    public UsuarioDao(Context context){
        helper = new DatabaseHelper(context);
    }

    public boolean inserirUsuario(Usuario usuario){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("_id_login",usuario.getUsuario());
        values.put("senha",usuario.getSenha());
        values.put("nome",usuario.getNome());
        values.put("status",usuario.getStatus());
        values.put("tipo",usuario.getTipo());

        long resul = db.insert("usuario",null,values);
        db.close();
        if(resul != -1 ){
            return true;
        }
        return false;
    }

    public boolean validarUsuario(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id_login,senha FROM usuario WHERE _id_login = ? AND senha = ?", new String[]{usuario.getUsuario(),usuario.getSenha()});
        if(cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }

    public boolean alterarUsuario(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("senha",usuario.getSenha());
        values.put("nome",usuario.getNome());
        values.put("status",usuario.getStatus());
        values.put("tipo",usuario.getTipo());
        long resul = db.update("usuario",values,"_id_login = ?",new String[]{usuario.getUsuario()});
        db.close();
        if(resul != -1 ){
            return true;
        }
        return false;
    }

    public boolean excluirUsuario(Usuario usuario){
    SQLiteDatabase db = helper.getWritableDatabase();
        long resul = db.delete("usuario","_id_login = ?",new String[]{usuario.getUsuario()});
        db.close();
        if(resul != 0){
            return true;
        }
        return false;
    }

    public List<Map<String, Object>> listarUsuarios(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id_login, nome, status, tipo FROM usuario",null);
        cursor.moveToFirst();
        usuarios = new ArrayList<>();

        for(int i=0;i<cursor.getCount();i++){
            Map<String,Object> item = new HashMap<>();
            String _id_login = cursor.getString(0);
            String nome = cursor.getString(1);
            String status = cursor.getString(2);
            String tipo = cursor.getString(3);

            item.put("_id_login","Login: "+_id_login);
            item.put("nome", "Nome: "+nome);
            item.put("status", "Status: "+status);
            item.put("tipo", "Tipo: "+tipo);

            usuarios.add(item);

            cursor.moveToNext();
        }
        db.close();
        return usuarios;
    }


}
