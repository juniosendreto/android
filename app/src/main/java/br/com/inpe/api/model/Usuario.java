package br.com.inpe.api.model;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by junio on 04/11/15.
 */
public class Usuario extends BaseModel{

    public static final String COL_LOGIN = "LOGIN";

    public static final String COL_PASSWORD = "PASSWORD";

    public static final String COL_NOME = "NOME";

    public static final String COL_CPF = "CPF";

    public static final String COL_EMAIL = "EMAIL";

    public static final String COL_MUNICIPIO = "MUNICIPIO";

    public static final String COL_ENDERECO = "ENDERECO";

    public static final String COL_TELEFONE = "TELEFONE";

    public static final String COL_CELULAR = "CELULAR";

    public static final String COL_NIVEL = "NIVEL";

    public static final String TABLE_NAME = "USUARIO";

    public static final String  DATABASE_NAME = "project_db";

    private String login;

    private String password;

    private String nome;

    private String cpf;

    private String email;

    private String municipio;

    private String endereco;

    private String telefone;

    private String celular;

    private Integer nivel;


    /*
        - Criar Resttrições CPF, Senha, Email(OK), '''''telefone e celular'''''.

    */
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() >= 5){
            this.password = password;

        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(validarCfp(cpf) == true){
            this.cpf = cpf;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(validarEmail(email) == true) {
            this.email = email;
        }
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        if( nivel == 1 || nivel == 2){
            this.nivel = nivel;

        }
    }

    public Usuario(String nome, String cpf, String login, String password, String email, String municipio,
                   String endereco, String telefone, String celular, Integer nivel) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.email = email;
        this.municipio = municipio;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;
        this.nivel = nivel;
    }

    public Usuario(){    }

    //Construtor para update de informações no banco

    public Usuario(String nome, String password, String email, String municipio,
                   String endereco, String telefone, String celular) {

        this.nome = nome;
        this.password = password;
        this.email = email;
        this.municipio = municipio;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;

    }

    public Boolean validarCfp(String cpf){

        Integer numeroMultiplicacao;
        String digito = "";
        String cpfAux = "";

        for(int i = 0; i < cpf.length(); i++){
            if(Character.isDigit(cpf.charAt(i))){
                cpfAux = cpfAux + cpf.charAt(i);
            }
        }
        if(cpfAux.length() == 11){

            for(int i = 0; i < 2; i++){
                Integer calculo = 0;
                Integer calculoAux = 0;

                if(i == 0){
                    numeroMultiplicacao = 10;
                    // 1 2 3 4 5 6 7 8 9 | 10 11
                    for(int j = 0; j < 9; j++){
                        calculo = calculo + Integer.parseInt(String.valueOf(cpf.charAt(j))) * numeroMultiplicacao;
                        //Log.d("total", "------" + cpf.charAt(j) + " " + numeroMultiplicacao + " " + calculo);

                        numeroMultiplicacao--;
                    }
                    //Log.d("total", "------" + calculo);
                    calculoAux = 11 - (calculo % 11);
                    //Log.d("total", "------" + calculoAux);


                    if(calculoAux == 10 || calculoAux == 11) {
                        digito = "0";
                    }else{
                        digito = Integer.toString(calculoAux);
                    }

                }else if(i == 1){
                    numeroMultiplicacao = 11;

                    for(int j = 0; j < 10; j++){
                        calculo = calculo + (Integer.parseInt(String.valueOf(cpf.charAt(j))) * numeroMultiplicacao);
                        numeroMultiplicacao--;

                    }
                    calculoAux = 11 - (calculo % 11);
                    //Log.d("total", "------" + calculoAux);


                    if(calculoAux == 10 || calculoAux == 11) {
                        digito = digito + "0";
                    }else{
                        digito = digito + Integer.toString(calculoAux);
                    }

                }
            }


        }

        if(digito.charAt(0) == cpfAux.charAt(9) && digito.charAt(1) == cpfAux.charAt(10)){
            return true;
        }else{
            return false;
        }

    }

    public boolean validarEmail(String email){
        boolean emailValido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            }
        }
        return emailValido;
    }

}
