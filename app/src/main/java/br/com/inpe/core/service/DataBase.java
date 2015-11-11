package br.com.inpe.core.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.inpe.api.model.Usuario;

/**
 * Created by junio on 06/11/15.
 */
public class DataBase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context){

        super(context, Usuario.DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*
            CREATE TABLE USUARIO(

                ID INTEGER PRIMARY KEY,
                NOME TEXT,
                LOGIN TEXT NOT NULL,
                PASSWORD TEXT NOT NULL,
                EMAIL TEXT NOT NULL,
                MUNICIPIO TEXT,
                ENDERECO TEXT,
                TELEFONE TEXT,
                CELULAR TEXT,
                NIVEL INTEGER NOT NULL

            );

         */
        try {


            String CREATE_USUARIO_TABLE = "CREATE TABLE " + Usuario.TABLE_NAME + "(" + Usuario.COL_ID +
                    " INTEGER PRIMARY KEY, " + Usuario.COL_NOME + " TEXT NOT NULL, " + Usuario.COL_LOGIN +
                    " TEXT NOT NULL," + Usuario.COL_PASSWORD + " TEXT NOT NULL, " + Usuario.COL_EMAIL +
                    " TEXT NOT NULL, " + Usuario.COL_MUNICIPIO + " TEXT, " + Usuario.COL_ENDERECO + " TEXT, " +
                    Usuario.COL_TELEFONE + " TEXT, " + Usuario.COL_CELULAR + " TEXT, " + Usuario.COL_NIVEL +
                    " INTEGER NOT NULL " + ");";
            System.out.println(CREATE_USUARIO_TABLE);

            db.execSQL(CREATE_USUARIO_TABLE);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Usuario.TABLE_NAME);

        onCreate(db);
    }
}
