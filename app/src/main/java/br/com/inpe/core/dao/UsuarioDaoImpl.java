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

        Cursor cursor;
        String [] colums = {Usuario.COL_ID};
        String[] arguments = {" WHERE " + Usuario.COL_ID + " = " + id};
        Usuario usuario;

        try {

            db = banco.getReadableDatabase();
            usuario = new Usuario();

            if(db != null){
                cursor = db.rawQuery("SELECT * FROM " + Usuario.TABLE_NAME, arguments);

                if(cursor != null && cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    usuario.setId(cursor.getLong(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setLogin(cursor.getString(2));
                    usuario.setPassword(cursor.getString(3));
                    usuario.setEmail(cursor.getString(4));
                    usuario.setMunicipio(cursor.getString(5));
                    usuario.setEndereco(cursor.getString(6));
                    usuario.setTelefone(cursor.getString(7));
                    usuario.setCelular(cursor.getString(8));
                    usuario.setNivel(cursor.getInt(9));
                }
                db.close();
            }
            return usuario;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;

        }

    }

    @Override
    public Usuario findByIdLoginAndPassword(String login, String password) {
        return null;
    }
}
