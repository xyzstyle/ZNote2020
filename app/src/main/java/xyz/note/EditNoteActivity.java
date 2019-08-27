package xyz.note;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import xyz.db.NotesDao;


/**
 * Created by xyz on 2019/8/21.
 * Project Name:ZNote2020
 */
public class EditNoteActivity extends AppCompatActivity {

    private EditText mNameEdt;
    private EditText mContentEdt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_edit_note);
        findViews();
        if (intent != null) {
            mNameEdt.setText(intent.getStringExtra("name"));
            mContentEdt.setText(intent.getStringExtra("content"));
        }
        Toolbar toolbar = findViewById(R.id.toolbar_edit_note);
        toolbar.inflateMenu(R.menu.toolbar_edit_note);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_menu_cancel_edit:
                        EditNoteActivity.this.finish();
                        break;
                    case R.id.action_menu_save_note:
                        saveNote();
                        break;

                    default:
                }


                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                EditNoteActivity.this.finish();
            }
        });
    }

    private void findViews() {
        mNameEdt = findViewById(R.id.edt_note_name);
        mContentEdt = findViewById(R.id.edt_note_content);
    }

    private void saveNote() {
        NotesDao notesDao = new NotesDao(EditNoteActivity.this);
        notesDao.open();
        notesDao.insertNote(mNameEdt.getText().toString(), mContentEdt.getText().toString());
        notesDao.close();
    }
}

