package br.com.inpe.Activitys;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import br.com.inpe.R;
import br.com.inpe.api.model.Usuario;
import br.com.inpe.core.dao.UsuarioDaoImpl;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        final UsuarioDaoImpl usuarioImpl = new UsuarioDaoImpl(this);
        ImageButton imageButton =  (ImageButton) findViewById(R.id.imageButton);
        Button button = (Button) findViewById(R.id.logarButton);
        final EditText loginE = (EditText) findViewById(R.id.loginEditText);
        final EditText passwordE = (EditText) findViewById(R.id.passwordEditText);
        final TextView report =  (TextView) findViewById(R.id.report);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (usuarioImpl.findByIdLoginAndPassword(loginE.getText().toString(),
                            passwordE.getText().toString()) == true) {
                        report.setVisibility(View.INVISIBLE);
                        loginE.setText("");
                        passwordE.setText("");
                        chamarActivity(Class.forName("br.com.inpe.Activitys.TelaInicialActivity"));

                    } else {
                        report.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    Log.d("ERRO CHAMADA TELA", e.getMessage());
                }
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("br.com.inpe.Activitys.CadastroActivity"));
                    report.setVisibility(View.INVISIBLE);

                } catch (ClassNotFoundException e) {
                    Log.d("ERRO CHAMADA CADASTRO", e.getMessage());
                }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public void chamarActivity(Class novaActivity) {

        Intent abrirActivity = new Intent(this, novaActivity);
        startActivity(abrirActivity);

    }

}
