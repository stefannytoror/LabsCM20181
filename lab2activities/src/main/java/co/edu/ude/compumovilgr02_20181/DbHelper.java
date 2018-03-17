package co.edu.ude.compumovilgr02_20181;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    public DbHelper(Context context) {
        super(context, DataBase.DB_RESTAURANT, null, DataBase.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String
                .format("create table %s (%s int primary key, %s text, %s text, %s text)",
                        DataBase.TABLE_USER, DataBase.Column.ID,
                        DataBase.Column.USER_NAME,
                        DataBase.Column.USER_EMAIL,
                        DataBase.Column.USER_PASSWORD);
        //Sentence for create table
        Log.d(TAG, "onCreate with SQL: " + sql);
        db.execSQL(sql); // execute sentence
    }


    //this is call every time that the schema changes (new version)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      /* Por simplicidad se borran los datos y se crea la tabla de nuevo.
      Usualmente se haría un ALTER TABLE para cambiar la estructura de la base de datos sin borrar los existentes */
        db.execSQL("drop table if exists " + DataBase.TABLE_USER); //Borrar tabla
        onCreate(db);//create table again
    }

}
