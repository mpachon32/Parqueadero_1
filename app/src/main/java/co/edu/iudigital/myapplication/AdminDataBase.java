package co.edu.iudigital.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDataBase extends SQLiteOpenHelper {
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "FeedReader.db";

    //private static final String sqlDrop = "drop table if exists estudiante";

    //private static final String SQL_CREATE = "create table estudiante (doc integer primary key, placa text, entrada text)";
    private static final String SQL_CREATE = "create table parking (doc integer primary key, placa text, entrada text, salida text)";

    public AdminDataBase(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("sqlDrop");
        db.execSQL("drop table if exists parking");
        db.execSQL(SQL_CREATE);
    }
}
