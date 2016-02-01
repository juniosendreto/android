package br.com.inpe.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        //final AlertDialog alertaNulo = new AlertDialog.Builder(this).create();
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

        salvarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setCpf(String.valueOf(cpfE.getText()));
                    usuario.setLogin(String.valueOf(loginE.getText()));
                    usuario.setPassword(String.valueOf(passwordE.getText()));
                    usuario.setEmail(String.valueOf(emailE.getText()));
                    usuario.setMunicipio(String.valueOf(municipioE.getText()));
                    usuario.setEndereco(String.valueOf(enderecoE.getText()));
                    usuario.setTelefone(String.valueOf(telefoneE.getText()));
                    usuario.setCelular(String.valueOf(celularE.getText()));
                    usuario.setNivel(Integer.valueOf(nivelE.getText().toString()));

                    usuarioImpl.save(usuario);

                }catch (Exception e){
                    Log.d("-------------------", e.getMessage());
                }
                /*
                if(String.valueOf(nomeE.getText()).equals("")){
                    camposNulos = camposNulos + "Nome ";
                }
                if(String.valueOf(cpfE.getText()).equals("")){
                    camposNulos = camposNulos + "CPF ";
                }
                if(String.valueOf(loginE.getText()).equals("")){
                    camposNulos = camposNulos + "Login ";
                }
                if(String.valueOf(passwordE.getText()).equals("")){
                    camposNulos = camposNulos + "Password ";
                }
                if(String.valueOf(emailE.getText()).equals("")){
                    camposNulos = camposNulos + "Email ";
                }
                if(String.valueOf(nivelE.getText()).equals("")){
                    camposNulos = camposNulos + "Nivel ";
                }
                if(!(camposNulos.equals(""))){
                    alertaNulo.setTitle("Você não pode deixar de preecher esses campos: ");
                    alertaNulo.setMessage(camposNulos);
                    alertaNulo.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertaNulo.show();
                    camposNulos = "";
                }else{

                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setNome(String.valueOf(nomeE.getText()));
                    usuario.setNome(String.valueOf(nomeE.getText()));

                }*/

            }
        });

    }
}
