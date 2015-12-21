package br.com.inpe.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import br.com.inpe.R;
import br.com.inpe.api.model.Usuario;
import br.com.inpe.core.dao.UsuarioDaoImpl;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ImageButton imageButton =  (ImageButton) findViewById(R.id.imageButton);
        Button button = (Button) findViewById(R.id.LogarButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("br.com.inpe.Activitys.CadastroActivity"));
                } catch (ClassNotFoundException e) {
                    Log.d("ERRO CHAMADA CADASTRO", e.getMessage());
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(Class.forName("br.com.inpe.Activitys.TelaInicialActivity"));
                } catch (ClassNotFoundException e) {
                    Log.d("ERRO CHAMADA TELA", e.getMessage());
                }
            }
        });

        validarCfp("39761555860");
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

    public void validarCfp(String password){

        Integer numeroMultiplicacao;
        String digito = "";

        for(int i = 0; i < 2; i++){
            Integer calculo = 0;
            Integer calculoAux = 0;

            if(i == 0){
                numeroMultiplicacao = 10;
                // 1 2 3 4 5 6 7 8 9 | 10 11
                for(int j = 0; j < 9; j++){
                    calculo = calculo + Integer.parseInt(String.valueOf(password.charAt(j))) * numeroMultiplicacao;
                    Log.d("total", "------" + password.charAt(j) + " " + numeroMultiplicacao + " " + calculo);

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
                    calculo = calculo + (Integer.parseInt(String.valueOf(password.charAt(j))) * numeroMultiplicacao);
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

        Log.d("Digito", "--------" + digito);

    }

}
