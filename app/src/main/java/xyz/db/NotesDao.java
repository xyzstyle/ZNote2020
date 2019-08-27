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
     * @param noteName  笔记名
     * @param noteContent  笔记内容
     * @return  long
     */
    public long insertNote(String noteName, String noteContent) {

        Date dateNow = new Date();
        ContentValues args = new ContentValues();
        args.put(D.Notes.FIELD_NOTE_NAME, noteName);
        args.put(D.Notes.FIELD_NOTE_CONTENT, noteContent);
        args.put(D.Notes.FIELD_CREATED, dateNow.getTime() / 1000);
        return mSQLiteDatabase.insert(D.Tables.NOTES, null, args);
    }




    /**
     * 获取特定笔记
     *
     * @param id  笔记的id
     * @return Cursor
     */
    public Cursor getNote(long id) {
        Cursor mCursor = mSQLiteDatabase.query(D.Tables.NOTES, new String[]{D.Notes.KEY_ROW_ID,
                D.Notes.FIELD_NOTE_NAME, D.Notes.FIELD_CREATED, D.Notes.FIELD_NOTE_CONTENT}, D.Notes.KEY_ROW_ID + "="
                + id, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
