package co.edu.ude.compumovilgr02_20181;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = DbHelper.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                insert();
                break;
            case R.id.button2:
                //search(); no funciona
                break;

        }


    }


    public void insert(){
        DbHelper dbHelper = new DbHelper(this); //Instancia de DbHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues(); //Creamos el contenedor

        values.put(DataBase.Column.ID, "1"); //Se pasan pares clave-valor
        values.put(DataBase.Column.USER_NAME, "student");
        values.put(DataBase.Column.USER_PASSWORD, "stefaydani");
        values.put(DataBase.Column.USER_EMAIL, "stefidinda@yahoo.net");

        db.insertWithOnConflict(DataBase.TABLE_USER, null, values,
                SQLiteDatabase.CONFLICT_IGNORE); //Se guarda la fila en la base de datos

        Log.d(TAG, "se inserto con exito ");

    }

    public void search(){
        DbHelper dbHelper = new DbHelper(this); //Instancia de DbHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Creamos el String de la consulta SQL
        String consultaSQL = "select * from" + DataBase.TABLE_USER;

        //Hacemos la consulta a la BD y guardamos los resultados en el cursor
        Cursor cursor = db.rawQuery(consultaSQL, null); //Hacemos la consulta

        //Recorremos los datos en el cursor
        while (cursor.moveToNext()){


            //Obtenemos cada dato del cursor
            Log.d(TAG,"ID: "+cursor.getString(cursor.getColumnIndex(DataBase.Column.ID)));
            Log.d(TAG, "User: "+cursor.getString(cursor.getColumnIndex(DataBase.Column.USER_NAME)));
            Log.d(TAG, "Message:  "+cursor.getString(cursor.getColumnIndex(DataBase.Column.USER_EMAIL)));
            Log.d(TAG, "Created At: "+cursor.getString(cursor.getColumnIndex(DataBase.Column.USER_PASSWORD)));
        }

    }
}
