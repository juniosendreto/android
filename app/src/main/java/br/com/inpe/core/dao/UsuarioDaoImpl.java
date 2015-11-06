package br.com.inpe.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.inpe.api.dao.UsuarioDao;
import br.com.inpe.api.model.Usuario;
import br.com.inpe.core.service.DataBase;

/**
 * Created by junio on 06/11/15.
 */
public class UsuarioDaoImpl implements UsuarioDao{

    private SQLiteDatabase db;
    private DataBase banco;

    public UsuarioDaoImpl(Context context){

        banco = new DataBase(context);

    }

    @Override
    public Usuario save(Usuario usuario) {
        try {
            db = banco.getWritableDatabase();

            ContentValues values =  new ContentValues();
            values.put(Usuario.COL_ID, usuario.getId());
            values.put(Usuario.COL_LOGIN, usuario.getLogin());
            values.put(Usuario.COL_PASSWORD, usuario.getPassword());
            values.put(Usuario.COL_NOME, usuario.getNome());
            values.put(Usuario.COL_EMAIL, usuario.getEmail());
            values.put(Usuario.COL_MUNICIPIO, usuario.getMunicipio());
            values.put(Usuario.COL_ENDERECO, usuario.getEndereco());
            values.put(Usuario.COL_TELEFONE, usuario.getTelefone());
            values.put(Usuario.COL_CELULAR, usuario.getCelular());
            values.put(Usuario.COL_NIVEL, usuario.getNivel());

            db.insert(Usuario.TABLE_NAME, null, values);

            return this.findById(usuario.getId());

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario upgrade(Usuario usuario) {
        return null;
    }

    @Override
    public Boolean delete(Usuario usuario) {
        return null;
    }

    @Override
    public List<Usuario> listAll() {
        return null;
    }

    @Override
    public Usuario findById(Long id) {

        db = banco.getReadableDatabase();

        return null;
    }

    @Override
    public Usuario findByIdLoginAndPassword(String login, String password) {
        return null;
    }
}
