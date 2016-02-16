package br.com.inpe.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.inpe.R;
import br.com.inpe.api.model.Usuario;
import br.com.inpe.api.model.Validador;
import br.com.inpe.core.dao.UsuarioDaoImpl;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        // NOT NULL CPF, LOGIN, PASSWORD, EMAIL, NIVEL

        final AlertDialog alerta = new AlertDialog.Builder(this).create();;
        final Usuario usuario = new Usuario();
        final UsuarioDaoImpl usuarioImpl = new UsuarioDaoImpl(this);
        final Validador validador = new Validador();

        Button salvarB = (Button) findViewById(R.id.salvarButton);
        final EditText nomeE = (EditText) findViewById(R.id.nomeEditText);
        final EditText cpfE = (EditText) findViewById(R.id.cpfEditText);
        final EditText loginE = (EditText) findViewById(R.id.loginEditText);
        final EditText passwordE = (EditText) findViewById(R.id.passwordEditText);
        final EditText passwordE2 = (EditText) findViewById(R.id.passwordEditText2);
        final EditText emailE = (EditText) findViewById(R.id.emailEditText);
        final EditText municipioE = (EditText) findViewById(R.id.municipioEditText);
        final EditText enderecoE = (EditText) findViewById(R.id.enderecoEditText);
        final EditText telefoneE = (EditText) findViewById(R.id.telefoneEditText);
        final EditText celularE = (EditText) findViewById(R.id.celularEditText);
        final EditText nivelE = (EditText) findViewById(R.id.nivelEditText);

        final TextView nomeReport = (TextView) findViewById(R.id.nomeReportView);
        final TextView loginReport = (TextView) findViewById(R.id.loginReportView);
        final TextView passwordReport = (TextView) findViewById(R.id.passwordReportView);
        final TextView passwordReport2 = (TextView) findViewById(R.id.passwordReportView2);
        final TextView cpfReport = (TextView) findViewById(R.id.cpfReportView);
        final TextView emailReport = (TextView) findViewById(R.id.emailReportView);
        final TextView nivelReport = (TextView) findViewById(R.id.nivelReportView);

        nomeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(String.valueOf(nomeE.getText()).equals("")){
                        nomeReport.setText("*Campo Obrigatório");
                        nomeReport.setVisibility(View.VISIBLE);
                    }else{
                        nomeReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        loginE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(loginE.getText()).equals("")) {
                        loginReport.setText("*Campo Obrigatório");
                        loginReport.setVisibility(View.VISIBLE);

                    }else if(usuarioImpl.findByLogin(loginE.getText().toString()) == true){
                        loginReport.setText("*Login já existe");
                        loginReport.setVisibility(View.VISIBLE);

                    }else{
                        loginReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        passwordE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(passwordE.getText()).equals("")) {
                        passwordReport.setText("*Campo Obrigatório");
                        passwordReport.setVisibility(View.VISIBLE);
                    }else if(passwordE.getText().toString().length() < 5){
                        passwordReport.setText("*Sua senha deve conter 5 ou mais caracteres");
                        passwordReport.setVisibility(View.VISIBLE);
                    }else{
                        passwordReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        passwordE2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!(passwordE.getText().toString().equals(passwordE2.getText().toString()))){
                        passwordReport2.setText("*Senhas não coincidem");
                        passwordReport2.setVisibility(View.VISIBLE);
                    }else{
                        passwordReport2.setVisibility(View.GONE);
                    }
                }
            }
        });

        cpfE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(cpfE.getText()).equals("")) {
                        cpfReport.setText("*Campo obrigatório");
                        cpfReport.setVisibility(View.VISIBLE);

                    }else if(validador.validarCfp(cpfE.getText().toString()) == false){
                        cpfReport.setText("*CPF Inválido");
                        cpfReport.setVisibility(View.VISIBLE);

                    }else{
                        cpfReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        emailE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (emailE.getText().toString().equals("")) {
                        emailReport.setText("*Campo obrigatório");
                        emailReport.setVisibility(View.VISIBLE);

                    }else if(validador.validarEmail(emailE.getText().toString())== false){
                        emailReport.setText("*Email Inválido");
                        emailReport.setVisibility(View.VISIBLE);

                    }else{
                        emailReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        nivelE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(nivelE.getText()).equals("")) {
                        nivelReport.setText("*Campo obrigatório");
                        nivelReport.setVisibility(View.VISIBLE);

                    }else if(!(nivelE.getText().toString().equals("1")) &&
                            !((nivelE.getText().toString().equals("2")))){
                        nivelReport.setText("*Você só pode ser do nível 1 ou 2");
                        nivelReport.setVisibility(View.VISIBLE);

                    }else{
                        nivelReport.setVisibility(View.GONE);
                    }
                }
            }
        });

        salvarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(String.valueOf(nomeE.getText()).equals("")){
                    nomeReport.setText("*Campo Obrigatório");
                    nomeReport.setVisibility(View.VISIBLE);
                }else{
                    nomeReport.setVisibility(View.GONE);

                }
                if(String.valueOf(cpfE.getText()).equals("")){
                    cpfReport.setText("*Campo Obrigatório");
                    cpfReport.setVisibility(View.VISIBLE);
                }else{
                    cpfReport.setVisibility(View.GONE);

                }
                if(String.valueOf(loginE.getText()).equals("")){
                    loginReport.setText("*Campo Obrigatório");
                    loginReport.setVisibility(View.VISIBLE);
                }else {
                    loginReport.setVisibility(View.GONE);

                }
                if(String.valueOf(passwordE.getText()).equals("")){
                    passwordReport.setText("*Campo Obrigatório");
                    passwordReport.setVisibility(View.VISIBLE);
                }else{
                    passwordReport.setVisibility(View.GONE);

                }
                if(String.valueOf(passwordE2.getText()).equals("")){
                    passwordReport2.setText("*Campo Obrigatório");
                    passwordReport2.setVisibility(View.VISIBLE);
                }else{
                    passwordReport2.setVisibility(View.GONE);

                }
                if(String.valueOf(emailE.getText()).equals("")){
                    emailReport.setText("*Campo Obrigatório");
                    emailReport.setVisibility(View.VISIBLE);
                }else{
                    emailReport.setVisibility(View.GONE);

                }
                if(String.valueOf(nivelE.getText()).equals("")){
                    nivelReport.setText("*Campo Obrigatório");
                    nivelReport.setVisibility(View.VISIBLE);
                }else {
                    nivelReport.setVisibility(View.GONE);

                }

                if (!(nomeReport.getVisibility() == View.GONE && loginReport.getVisibility() == View.GONE
                        && passwordReport.getVisibility() == View.GONE && passwordReport2.getVisibility() == View.GONE
                        && cpfReport.getVisibility() == View.GONE && emailReport.getVisibility() == View.GONE
                        && nivelReport.getVisibility() == View.GONE)) {


                    alerta.setTitle("Alguns campos obrigatórios precisam ser preechidos!");
                    alerta.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                } else {

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
                        alerta.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    chamarActivity(Class.forName("br.com.inpe.Activitys.MainActivity"));

                                } catch (Exception e) {
                                    Log.d("ERRO NO CADASTRO", e.getMessage());
                                }
                            }
                        });
                        alerta.show();

                    } catch (Exception e) {
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
}