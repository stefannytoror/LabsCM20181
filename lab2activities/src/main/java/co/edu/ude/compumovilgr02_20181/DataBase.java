package co.edu.ude.compumovilgr02_20181;

import android.provider.BaseColumns;



public class DataBase {
    public static final String DB_RESTAURANT = "timeline.db"; //DB name
    public static final int DB_VERSION = 1; //DB version
    public static final String TABLE_USER = "users";//Name of users table
    /*public static final String TABLE_PLATES = "plates"; //Name of plates table
    public static final String TABLE_DRINKS = "drinks";//Name of drinks table*/

    public class Column { //Columnas de la tabla
        public static final String ID = BaseColumns._ID; //El ID se suele definir así por convención
        public static final String USER_NAME = "user_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_PASSWORD = "user_password";
    }




}

