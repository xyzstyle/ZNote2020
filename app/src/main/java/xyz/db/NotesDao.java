package xyz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

public class NotesDao {



    private DatabaseHelper mOpenHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public NotesDao(Context context) {
        mOpenHelper = new DatabaseHelper(context);

    }

    public void open() {
        try {
            mSQLiteDatabase = mOpenHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e("Database", "SQL exception");
            e.printStackTrace();
        }

    }

    public void close() {
        mSQLiteDatabase.close();
        mOpenHelper.close();
    }

    /**
     * 插入一条笔记记录
     *
     * @param noteName
     * @param noteDetail
     * @return
     */
    public long insertNote(String noteName, String noteDetail) {

        Date dateNow = new Date();
        ContentValues args = new ContentValues();
        args.put(D.Notes.FIELD_NOTE, noteName);
        args.put(D.Notes.FIELD_NOTE_DETAIL, noteDetail);
        args.put(D.Notes.FIELD_CREATED, dateNow.getTime() / 1000);
        return mSQLiteDatabase.insert(D.Tables.NOTES, null, args);


    }


    /**
     * 获取所有的笔记记录
     *
     * @return
     */

    public Cursor getNotes() {
        return mSQLiteDatabase.query(D.Tables.NOTES, new String[]{D.Notes.KEY_ROW_ID, D.Notes.FIELD_NOTE,
                D.Notes.FIELD_NOTE_DETAIL, " Date(time_created,'unixepoch')  as time_created"}, null, null, null, null, null);
    }

    public Cursor getNotesOriginal() {
        return mSQLiteDatabase.query(D.Tables.NOTES, new String[]{D.Notes.KEY_ROW_ID, D.Notes.FIELD_NOTE,
                D.Notes.FIELD_NOTE_DETAIL, D.Notes.FIELD_CREATED}, null, null, null, null, null);
    }

    /**
     * 获取特定笔记
     *
     * @param id
     * @return
     */
    public Cursor get(long id) {
        Cursor mCursor = mSQLiteDatabase.query(D.Tables.NOTES, new String[]{D.Notes.KEY_ROW_ID,
                D.Notes.FIELD_NOTE, D.Notes.FIELD_CREATED, D.Notes.FIELD_NOTE_DETAIL}, D.Notes.KEY_ROW_ID + "="
                + id, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public int updateNote(ContentValues cv, String selection, String[] selectionArgs) {

        return mSQLiteDatabase.update(D.Tables.NOTES, cv, selection, selectionArgs);
    }

    public int deleteNote(String selection, String[] selectionArgs) {

        return mSQLiteDatabase.delete(D.Tables.NOTES,selection, selectionArgs);
    }

    public Cursor getLastId(){
        Cursor cursor = mSQLiteDatabase.rawQuery("select last_insert_rowid()", null);
        cursor.moveToFirst();
        return cursor;
    }


}
