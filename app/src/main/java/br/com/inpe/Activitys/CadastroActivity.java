package br.com.inpe.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.inpe.R;
import br.com.inpe.api.model.Usuario;
import br.com.inpe.core.dao.UsuarioDaoImpl;

public class CadastroActivity extends AppCompatActivity {

    String camposNulos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // NOT NULL CPF, LOGIN, PASSWORD, EMAIL, NIVEL

        final AlertDialog alerta = new AlertDialog.Builder(this).create();;
        final Usuario usuario = new Usuario();
        final UsuarioDaoImpl usuarioImpl = new UsuarioDaoImpl(this);

        Button salvarB = (Button) findViewById(R.id.salvarButton);
        final EditText nomeE = (EditText) findViewById(R.id.nomeEditText);
        final EditText cpfE = (EditText) findViewById(R.id.cpfEditText);
        final EditText loginE = (EditText) findViewById(R.id.loginEditText);
        final EditText passwordE = (EditText) findViewById(R.id.passwordEditText);
        final EditText emailE = (EditText) findViewById(R.id.emailEditText);
        final EditText municipioE = (EditText) findViewById(R.id.municipioEditText);
        final EditText enderecoE = (EditText) findViewById(R.id.enderecoEditText);
        final EditText telefoneE = (EditText) findViewById(R.id.telefoneEditText);
        final EditText celularE = (EditText) findViewById(R.id.celularEditText);
        final EditText nivelE = (EditText) findViewById(R.id.nivelEditText);

        nomeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                } else {
                    nomeE.setBackgroundResource(R.color.vermelho);
                    Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        });

        salvarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(String.valueOf(nomeE.getText()).equals("")){
                    camposNulos = camposNulos + "\n- Nome ";
                }
                if(String.valueOf(cpfE.getText()).equals("")){
                    camposNulos = camposNulos + "\n- CPF ";
                }
                if(String.valueOf(loginE.getText()).equals("")){
                    camposNulos = camposNulos + "\n- Login ";
                }
                if(String.valueOf(passwordE.getText()).equals("")){
                    camposNulos = camposNulos + "\n- Password ";
                }
                if(String.valueOf(emailE.getText()).equals("")){
                    camposNulos = camposNulos + "\n- Email ";
                }
                if(String.valueOf(nivelE.getText()).equals("")){
                    camposNulos = camposNulos + "\n- Nivel ";
                }
                if(!(camposNulos.equals(""))){
                    alerta.setTitle("Você não pode deixar de preecher esses campos: ");
                    alerta.setMessage(camposNulos);
                    alerta.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                    camposNulos = "";
                }else{

                    try {
                        usuario.setNome(nomeE.getText().toString());
                        usuario.setCpf(cpfE.getText().toString());
                        usuario.setLogin(loginE.getText().toString());
                        usuario.setPassword(passwordE.getText().toString());
                        usuario.setEmail(emailE.getText().toString());
                        usuario.setMunicipio(municipioE.getText().toString());
                        usuario.setEndereco(enderecoE.getText().toString());
                        usuario.setTelefone(telefoneE.getText().toString());
                        usuario.setCelular(celularE.getText().toString());
                        usuario.setNivel(Integer.valueOf(nivelE.getText().toString()));

                        usuarioImpl.save(usuario);

                        alerta.setTitle("Usuário criado com sucesso!");
                        alerta.setMessage(camposNulos);
                        alerta.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    chamarActivity(Class.forName("br.com.inpe.Activitys.MainActivity"));

                                }catch (Exception e){
                                    Log.d("ERRO NO CADASTRO", e.getMessage());
                                }
                            }
                        });
                        alerta.show();

                    }catch (Exception e){
                        Log.d("-------------------", e.getMessage());
                    }

                }

            }
        });
    }

    public void chamarActivity(Class novaActivity) {

        Intent abrirActivity = new Intent(this, novaActivity);
        startActivity(abrirActivity);
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
