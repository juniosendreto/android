package br.com.inpe.api.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by junio on 05/02/16.
 */
public class Validador {


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

                    for(int j = 0; j < 9; j++){
                        calculo = calculo + Integer.parseInt(String.valueOf(cpf.charAt(j))) * numeroMultiplicacao;

                        numeroMultiplicacao--;
                    }

                    calculoAux = 11 - (calculo % 11);

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
