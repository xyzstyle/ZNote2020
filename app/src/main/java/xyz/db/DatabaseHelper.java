package xyz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * My File Created by xyz on 2018/10/25.
 */
class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "xyz:DatabaseHelper";

    DatabaseHelper(Context context) {
        super(context, D.DATABASE_NAME, null, D.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableNotes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTableNotes(db);
        createTableNotes(db);
    }

    private void createTableNotes(SQLiteDatabase db) {
        String createTableNotes = "create table " + D.Tables.NOTES + "("
                + D.Notes.KEY_ROW_ID + " INTEGER PRIMARY KEY,"
                + D.Notes.FIELD_NOTE_NAME + " TEXT,"
                + D.Notes.FIELD_NOTE_CONTENT + " TEXT,"
                + D.Notes.FIELD_CREATED + "  DATETIME" + ");";
        db.execSQL(createTableNotes);


        Log.d(TAG, " CreateDB SUCCESS, SQL=" + createTableNotes);
    }

    private void dropTableNotes(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + D.Tables.NOTES);
    }
}
