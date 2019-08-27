package xyz.note;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import xyz.db.D;
import xyz.db.NotesDao;

/**
 * Created by xyz on 2019/8/22.
 * Project Name:ZNote2020
 */
public class ShowNoteActivity extends AppCompatActivity {


    private TextView mNoteNameTv;
    private TextView mNoteContentTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        findViews();
        Toolbar toolbar = findViewById(R.id.toolbar_show_note);
        toolbar.inflateMenu(R.menu.toolbar_show_note);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_edit_note:
                        Intent intent = new Intent(ShowNoteActivity.this, EditNoteActivity.class);
                        intent.putExtra("name", mNoteNameTv.getText().toString());
                        intent.putExtra("content", mNoteContentTv.getText().toString());
                        startActivity(intent);
                        break;
                    case R.id.action_remind:
                        break;
                    default:

                }
                return false;
            }
        });

        setNoteData();
    }

    private void setNoteData() {
        NotesDao notesDao = new NotesDao(ShowNoteActivity.this);
        notesDao.open();
        Cursor cursor = notesDao.getNote(2);
        mNoteNameTv.setText(cursor.getString(cursor.getColumnIndex(D.Notes.FIELD_NOTE_NAME)));
        mNoteContentTv.setText(cursor.getString(cursor.getColumnIndex(D.Notes.FIELD_NOTE_CONTENT)));
        notesDao.close();
    }

    private void findViews() {
        mNoteNameTv = findViewById(R.id.tv_note_name);
        mNoteContentTv = findViewById(R.id.tv_note_content);

    }
}
