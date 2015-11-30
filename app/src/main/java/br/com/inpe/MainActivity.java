package br.com.inpe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.com.inpe.api.model.Usuario;
import br.com.inpe.core.dao.UsuarioDaoImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario u =  new Usuario(67L, "pedro", "elias", "elias", "elias", "elias", "elias", "elias", "elias", 1);
        Usuario usu;

        UsuarioDaoImpl usuarioImp = new UsuarioDaoImpl(this);

        //usu = usuarioImp.save(u);

        /*

        System.out.print(usu.getId());
        System.out.print(usu.getLogin());
        System.out.print(usu.getPassword());
        System.out.print(usu.getEmail());
        System.out.print(usu.getCelular());
        System.out.print(usu.getEndereco());
        System.out.print(usu.getMunicipio());
        System.out.print(usu.getNome());
        System.out.print(usu.getTelefone());
        System.out.print(usu.getNivel());

        */

        //usuarioImp.delete(u);


        List<Usuario> usuarios;

        usuarios = usuarioImp.listAll();

        System.out.print(usuarios.isEmpty());
/*
        for(Usuario uu: usuarios){
            System.out.print(uu.getId());
            System.out.print(uu.getNome());
            System.out.print(uu.getPassword());
            System.out.print(uu.getEmail());
            System.out.print(uu.getLogin());
            System.out.print(uu.getNivel());
            System.out.print(uu.getTelefone());
            System.out.print(uu.getCelular());
            System.out.print(uu.getMunicipio());
            System.out.print(uu.getEndereco());

        }
*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
