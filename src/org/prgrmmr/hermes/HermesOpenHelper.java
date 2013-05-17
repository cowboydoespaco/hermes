package org.prgrmmr.hermes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HermesOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "HermesDatabase.db";
    private static final String DATABASE_NAME = "hermes";

    public static final String TABLE_MESSAGE = "message";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_DATE = "date";

    private SQLiteDatabase mDatabase;

    private static final String MESSAGE_TABLE_CREATE =
            " CREATE TABLE " + TABLE_MESSAGE +
            " ( "  + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CONTENT + " text, "
            + COLUMN_DATE + " integer);";


    HermesOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MESSAGE_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);
        onCreate(db);

    }

}
