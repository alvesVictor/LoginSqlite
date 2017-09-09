package com.example.victor.myapplication;


/**
 * Created by Victor on 31/08/2017.
 */

public class Usuario {
    private String usuario;
    private String senha;
    private String nome;
    private String tipo;
    private String status;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getStatus() {
        return status;
    }

    public Usuario(){

    };

    public Usuario(String usuario,String senha){
        this.setUsuario(usuario);
        this.setSenha(senha);
    }

    public Usuario(String usuario,String senha,String nome,String tipo,String status){
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setNome(nome);
        this.setTipo(tipo);
        this.setStatus(status);
    }

}





