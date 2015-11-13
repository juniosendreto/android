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
    private Cursor cursor;
    private ContentValues values;
    private Usuario usuario;


    public UsuarioDaoImpl(Context context){

        banco = new DataBase(context);

    }

    @Override
    public Usuario save(Usuario usuario) {
        try {
            db = banco.getWritableDatabase();

            ContentValues values =  new ContentValues();
            values.put(usuario.COL_ID, usuario.getId());
            values.put(usuario.COL_LOGIN, usuario.getLogin());
            values.put(usuario.COL_PASSWORD, usuario.getPassword());
            values.put(usuario.COL_NOME, usuario.getNome());
            values.put(usuario.COL_EMAIL, usuario.getEmail());
            values.put(usuario.COL_MUNICIPIO, usuario.getMunicipio());
            values.put(usuario.COL_ENDERECO, usuario.getEndereco());
            values.put(usuario.COL_TELEFONE, usuario.getTelefone());
            values.put(usuario.COL_CELULAR, usuario.getCelular());
            values.put(usuario.COL_NIVEL, usuario.getNivel());

            db.insert(Usuario.TABLE_NAME, null, values);

            db.close();

            //return this.findById(usuario.getId());
            return usuario;
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Usuario upgrade(Usuario usuario) {

        try{
            db = banco.getReadableDatabase();
            if(db != null) {

                values = new ContentValues();

                //values.put(Usuario.COL_ID, usuario.getId());
                //values.put(Usuario.COL_LOGIN, usuario.getLogin());
                values.put(usuario.COL_PASSWORD, usuario.getPassword());
                // values.put(Usuario.COL_NOME, usuario.getNome());
                values.put(usuario.COL_EMAIL, usuario.getEmail());
                values.put(usuario.COL_MUNICIPIO, usuario.getMunicipio());
                values.put(usuario.COL_ENDERECO, usuario.getEndereco());
                values.put(usuario.COL_TELEFONE, usuario.getTelefone());
                values.put(usuario.COL_CELULAR, usuario.getCelular());
                //values.put(Usuario.COL_NIVEL, usuario.getNivel());

                db.update(Usuario.TABLE_NAME, values, String.valueOf(usuario.getId()), null);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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

        String[] arguments = {String.valueOf(id)};

        try {

            db = banco.getReadableDatabase();
            usuario = new Usuario();

            if(db != null){
                cursor = db.rawQuery("SELECT * FROM " + Usuario.TABLE_NAME + " WHERE ID = ?", arguments);

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

    public void testQuery(){

        db = banco.getWritableDatabase();

        String query = "SELECT * FROM USUARIO";

        Cursor c =  db.rawQuery(query, new String[]{});

        while (c.moveToNext()){
            System.out.println(c.getLong(0));
        }
        db.close();
    }

    @Override
    public Usuario findByIdLoginAndPassword(String login, String password) {


        String[] arguments = {login, password};

        try {

            db = banco.getReadableDatabase();
            usuario = new Usuario();

            if(db != null){
                cursor = db.rawQuery("SELECT * FROM " + Usuario.TABLE_NAME + " WHERE LOGIN = ? AND PASSWORD = ?", arguments);

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

}
