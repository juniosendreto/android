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

import br.com.inpe.R;
import br.com.inpe.api.model.Usuario;
import br.com.inpe.api.model.Validador;
import br.com.inpe.core.dao.UsuarioDaoImpl;

public class CadastroActivity extends AppCompatActivity {

    private String camposIncorretos = "";
    //private Validador vilidador;

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

        nomeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(String.valueOf(nomeE.getText()).equals("")){
                        camposIncorretos = camposIncorretos + "\n- Nome não poder ser um campo vazio ";
                    }
                }
            }
        });

        cpfE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Validador validador = new Validador();
                if (!hasFocus) {
                    if (String.valueOf(cpfE.getText()).equals("")) {
                        camposIncorretos = camposIncorretos + "\n- CPF não pode ser um campo vazio";
                    }else if(validador.validarCfp(cpfE.getText().toString()) == false){
                        camposIncorretos = camposIncorretos + "\n CPF está inválido";
                        //colocar visibilidade
                    }
                }
            }
        });

        loginE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(loginE.getText()).equals("")) {
                        camposIncorretos = camposIncorretos + "\n- Login Não pode ser um campo vazio ";
                    }else if(usuarioImpl.findByLogin(loginE.getText().toString()) == false){
                        camposIncorretos = camposIncorretos + "\n- Login já existe";
                        //colocar visibilidade
                    }
                }
            }
        });


        passwordE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(passwordE.getText()).equals("")) {
                        camposIncorretos = camposIncorretos + "\n- Password não pode ser um campo vazio ";
                        //colocar visibilidade
                    }else if(passwordE.getText().toString().length() < 5){
                        camposIncorretos = camposIncorretos + "\n A Senha deve conter no mínimo 5 caracteres";
                        //colocar visibilidade
                    }else if(!(passwordE.getText().toString().equals(passwordE2.getText().toString()))){
                        //colocar visibilidade
                    }
                }
            }
        });

        emailE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (emailE.getText().toString().equals("")) {
                        camposIncorretos = camposIncorretos + "\n- E-mail não pode ser um campo vazio ";
                    }else if(validador.validarEmail(emailE.getText().toString())== false){
                        //colocar visibilidade
                        camposIncorretos = camposIncorretos + "\n E-mail inválido";
                    }
                }
            }
        });

        nivelE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (String.valueOf(nivelE.getText()).equals("")) {
                        camposIncorretos = camposIncorretos + "\n- Nivel ";
                    }else if(!(nivelE.getText().toString().equals("1") ||
                            !(nivelE.getText().toString().equals("2")))){
                        //colocar visibilidade
                        camposIncorretos = camposIncorretos + "\n- Você só poder ser do nível 1 ou 2";

                    }
                }
            }
        });


        salvarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(camposIncorretos.equals(""))) {
                    alerta.setTitle("Erro cadastro: ");
                    alerta.setMessage(camposIncorretos);
                    alerta.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                    camposIncorretos = "";
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
                        alerta.setMessage(camposIncorretos);
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