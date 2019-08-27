package xyz.db;

/**
 * My File Created by xyz on 2018/4/20.
 */

public final class D {

    public static final String DATABASE_NAME = "notes.db";
    public static final int DATABASE_VERSION = 1;

    public static final class Tables {
        public static final String NOTES = "notes";
    }

    public static final class Notes {
        public static final String KEY_ROW_ID = "_id";
        public static final String FIELD_NOTE_BOOK_ID = "book_id";
        public static final String FIELD_NOTE_NAME = "name";
        public static final String FIELD_NOTE_CONTENT = "content";
        public static final String FIELD_CREATED = "time_created";
    }
}