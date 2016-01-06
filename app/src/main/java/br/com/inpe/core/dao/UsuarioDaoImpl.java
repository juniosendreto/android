package br.com.inpe.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
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

            if(db != null){

                ContentValues values =  new ContentValues();
                values.put(usuario.COL_ID, usuario.getId());
                values.put(usuario.COL_NOME, usuario.getNome());
                values.put(usuario.COL_CPF, usuario.getCpf());
                values.put(usuario.COL_LOGIN, usuario.getLogin());
                values.put(usuario.COL_PASSWORD, usuario.getPassword());
                values.put(usuario.COL_EMAIL, usuario.getEmail());
                values.put(usuario.COL_MUNICIPIO, usuario.getMunicipio());
                values.put(usuario.COL_ENDERECO, usuario.getEndereco());
                values.put(usuario.COL_TELEFONE, usuario.getTelefone());
                values.put(usuario.COL_CELULAR, usuario.getCelular());
                values.put(usuario.COL_NIVEL, usuario.getNivel());

                if(db.insert(Usuario.TABLE_NAME, null, values) != -1){
                    db.close();
                    return usuario;
                }
            }
            return null;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    } //OK

    @Override
    public Usuario upgrade(Usuario usuario) {

        String where = "ID = ?";
        String []id = {String.valueOf(usuario.getId())};

        try{
            db = banco.getReadableDatabase();
            if(db != null) {
                values = new ContentValues();

                values.put(usuario.COL_PASSWORD, usuario.getPassword());
                values.put(Usuario.COL_NOME, usuario.getNome());
                values.put(usuario.COL_EMAIL, usuario.getEmail());
                values.put(usuario.COL_MUNICIPIO, usuario.getMunicipio());
                values.put(usuario.COL_ENDERECO, usuario.getEndereco());
                values.put(usuario.COL_TELEFONE, usuario.getTelefone());
                values.put(usuario.COL_CELULAR, usuario.getCelular());

                db.update(Usuario.TABLE_NAME, values, where, id);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return usuario;
    }

    @Override
    public Boolean delete(Usuario usuario) {


        String where = "NOME = ?";

        try{
            db = banco.getReadableDatabase();
            if(db != null){
                int rows = db.delete(Usuario.TABLE_NAME, where, new String[]{usuario.getNome()});
                if(rows != 0){
                    return true;
                }else{
                    return false;
                }
            }

        }catch (Exception e){
            System.out.print(e.getMessage());
            return false;
        }
        return false;

    } //OK

    @Override
    public List<Usuario> listAll() {

        List<Usuario> usuarios = new ArrayList<Usuario>();
        String query = "SELECT * FROM USUARIO";

        try{
            db =  banco.getReadableDatabase();
            if(db != null) {
                cursor = db.rawQuery(query, new String[]{});

                if(cursor != null && cursor.getCount() != 0){
                    while(cursor.moveToNext()) {
                        //cursor.moveToFirst();
                        usuario =  new Usuario();

                        usuario.setId(cursor.getLong(0));
                        usuario.setNome(cursor.getString(1));
                        usuario.setCpf(cursor.getString(2));
                        usuario.setLogin(cursor.getString(3));
                        usuario.setPassword(cursor.getString(4));
                        usuario.setEmail(cursor.getString(5));
                        usuario.setMunicipio(cursor.getString(6));
                        usuario.setEndereco(cursor.getString(7));
                        usuario.setTelefone(cursor.getString(8));
                        usuario.setCelular(cursor.getString(9));
                        usuario.setNivel(cursor.getInt(10));


                        usuarios.add(usuario);

                    }

                }
                db.close();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }

        return usuarios;
    } //OK

    @Override
    public Usuario findById(Long id) {

        String[] arguments = {String.valueOf(id)};
        String query = "SELECT * FROM " + Usuario.TABLE_NAME + " WHERE ID = ?";

        try {

            db = banco.getReadableDatabase();
            usuario = new Usuario();

            if(db != null){
                cursor = db.rawQuery(query, arguments);

                if(cursor != null && cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    usuario.setId(cursor.getLong(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setCpf(cursor.getString(2));
                    usuario.setLogin(cursor.getString(3));
                    usuario.setPassword(cursor.getString(4));
                    usuario.setEmail(cursor.getString(5));
                    usuario.setMunicipio(cursor.getString(6));
                    usuario.setEndereco(cursor.getString(7));
                    usuario.setTelefone(cursor.getString(8));
                    usuario.setCelular(cursor.getString(9));
                    usuario.setNivel(cursor.getInt(10));

                }
                db.close();
            }
            return usuario;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;

        }
    } //OK

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
        String query = "SELECT * FROM " + Usuario.TABLE_NAME + " WHERE LOGIN = ? AND PASSWORD = ?";

        try {

            db = banco.getReadableDatabase();
            if(db != null){
                cursor = db.rawQuery(query, arguments);

                if(cursor != null && cursor.getCount() != 0) {
                    usuario = new Usuario();

                    cursor.moveToFirst();
                    usuario.setId(cursor.getLong(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setCpf(cursor.getString(2));
                    usuario.setLogin(cursor.getString(3));
                    usuario.setPassword(cursor.getString(4));
                    usuario.setEmail(cursor.getString(5));
                    usuario.setMunicipio(cursor.getString(6));
                    usuario.setEndereco(cursor.getString(7));
                    usuario.setTelefone(cursor.getString(8));
                    usuario.setCelular(cursor.getString(9));
                    usuario.setNivel(cursor.getInt(10));

                }

                db.close();
            }
            return usuario;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;

        }
    } //OK


    public Boolean findByLogin(String login) {

        String[] arguments = {String.valueOf(login)};
        String query = "SELECT * FROM " + Usuario.TABLE_NAME + " WHERE ID = ?";

        try {

            db = banco.getReadableDatabase();

            if(db != null){
                cursor = db.rawQuery(query, arguments);

                if(cursor != null && cursor.getCount() != 0) {
                    return true;
                }
                db.close();
            }
            return false;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;

        }
    }

}
