package xyz.note;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import xyz.fragment.NoteBookListFragment;
import xyz.fragment.NoteListFragment;
import xyz.fragment.TagListFragment;

public class MainActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private NoteFragmentPagerAdapter mNoteFragmentPagerAdapter;
    private Fragment[] mFragments;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragments = new Fragment[3];
        mFragments[0] = new NoteListFragment();
        mFragments[1] = new NoteBookListFragment();
        mFragments[2] = new TagListFragment();
        mViewPager = findViewById(R.id.view_pager);
        mNoteFragmentPagerAdapter = new NoteFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mNoteFragmentPagerAdapter);
        mTabLayout = findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        toolbar = findViewById(R.id.toolbar_main);
        toolbar.inflateMenu(R.menu.toolbar_main_notes);



        DrawerArrowDrawable drawerArrowDrawable = new DrawerArrowDrawable(this);
        drawerArrowDrawable.setColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIcon(drawerArrowDrawable);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        toolbar.inflateMenu(R.menu.toolbar_main_notes);
                        break;
                    case 1:
                        toolbar.inflateMenu(R.menu.toolbar_show_note);
                        break;
                    default:
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private class NoteFragmentPagerAdapter extends FragmentPagerAdapter {
        private final CharSequence  tabTitles[] = new CharSequence[]{getText(R.string.notes), getText(R.string.note_books), getText(R.string.tags)};

        NoteFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_show_note,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
