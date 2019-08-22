package xyz.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
/**
 * Created by xyz on 2019/8/22.
 * Project Name:ZNote2020
 */
public class ShowNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Toolbar toolbar = findViewById(R.id.toolbar_show_note);
        toolbar.inflateMenu(R.menu.toolbar_show_note);
    }
}
