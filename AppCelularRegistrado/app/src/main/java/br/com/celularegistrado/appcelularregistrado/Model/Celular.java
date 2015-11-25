package br.com.celularegistrado.appcelularregistrado.Model;

import java.io.Serializable;

public class Celular implements Serializable {


    private Integer alerta_celular;
    private String nome_modelo;
    private String nome_operadora;
    private String nome_fabricante;
    private String nome_usuario;
    private String email_usuario;
    private String imei_celular;
    private String imagem_usuario;


    public Celular(Integer alerta_celular, String nome_modelo, String nome_operadora, String nome_fabricante,
    String email_usuario, String nome_usuario, String imei_celular, String imagem_usuario ) {

        this.alerta_celular = alerta_celular;
        this.nome_modelo = nome_modelo;
        this.nome_operadora = nome_operadora;
        this.nome_fabricante = nome_fabricante;
        this.email_usuario = email_usuario;
        this.nome_usuario = nome_usuario;
        this.imei_celular = imei_celular;
        this.imagem_usuario = imagem_usuario;

    }

    public Celular(){

    }

    public String getImagem_usuario() {
        return imagem_usuario;
    }

    public void setImagem_usuario(String imagem_usuario) {
        this.imagem_usuario = imagem_usuario;
    }

    public String getImei_celular() {
        return imei_celular;
    }

    public void setImei_celular(String imei_celular) {
        this.imei_celular = imei_celular;
    }

    public Integer getAlerta_celular() {
        return alerta_celular;
    }

    public void setAlerta_celular(Integer alerta_celular) {
        this.alerta_celular = alerta_celular;
    }

    public String getNome_modelo() {
        return nome_modelo;
    }

    public void setNome_modelo(String nome_modelo) {
        this.nome_modelo = nome_modelo;
    }

    public String getNome_operadora() {
        return nome_operadora;
    }

    public void setNome_operadora(String nome_operadora) {
        this.nome_operadora = nome_operadora;
    }

    public String getNome_fabricante() {
        return nome_fabricante;
    }

    public void setNome_fabricante(String nome_fabricante) {
        this.nome_fabricante = nome_fabricante;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }
}


