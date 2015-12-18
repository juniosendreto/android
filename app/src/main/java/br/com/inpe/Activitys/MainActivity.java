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
                    chamarActivity(v, Class.forName("br.com.inpe.Activitys.CadastroActivity"));
                } catch (ClassNotFoundException e) {
                    Log.d("ERRO CHAMADA CADASTRO", e.getMessage());
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chamarActivity(v, Class.forName("br.com.inpe.Activitys.TelaInicialActivity"));
                } catch (ClassNotFoundException e) {
                    Log.d("ERRO CHAMADA TELA", e.getMessage());
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

    public void chamarActivity(View view, Class c) {

        Intent secondActivity = new Intent(this, c);
        startActivity(secondActivity);
    }

}
