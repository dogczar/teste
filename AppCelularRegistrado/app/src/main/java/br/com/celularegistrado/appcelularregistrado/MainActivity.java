package br.com.celularegistrado.appcelularregistrado;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import br.com.celularegistrado.appcelularregistrado.Activity.InfoActivity;
import br.com.celularegistrado.appcelularregistrado.Fragment.ImeiFragment;
import br.com.celularegistrado.appcelularregistrado.Fragment.QRCodeFragment;
import br.com.celularegistrado.appcelularregistrado.Fragment.TagFragment;
import br.com.celularegistrado.appcelularregistrado.Model.Celular;
import br.com.celularegistrado.appcelularregistrado.WS.RestClient;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.pager)
    ViewPager pager;

    ImageButton bt_info;

    private MyPagerAdapter adapter;
    private Drawable oldBackground = null;
    private int currentColor;
    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        // create our manager instance after the content view is set
        mTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        mTintManager.setStatusBarTintEnabled(true);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(1);
        changeColor(getResources().getColor(R.color.colorPrimary));

        bt_info = (ImageButton) findViewById(R.id.bt_info);

        bt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(i);
            }
        });

        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                Toast.makeText(MainActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();


            }
        });

       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentColor", currentColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentColor = savedInstanceState.getInt("currentColor");
        changeColor(currentColor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                //QuickContactFragment.newInstance().show(getSupportFragmentManager(), "QuickContactFragment");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

        private final String[] TITLES = {"Tab 1", "Tab 2", "Tab 3"};

        private final int[] ICONS = {
                R.mipmap.tag,
                R.mipmap.qrcode,
                R.mipmap.celular
        };

        private final int[] ICONS2 = {
                R.mipmap.tag_pressed,
                R.mipmap.qrcode_pressed,
                R.mipmap.celular_pressed
        };


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            if(position==1){
                return QRCodeFragment.newInstance("QRCODE", "QRCODE");
            }else if(position==0) {

                return TagFragment.newInstance("TAG", "TAG");
            }else{
                return ImeiFragment.newInstance("IMEI", "IMEI");
            }
        }


        @Override
        public View getCustomTabView(ViewGroup parent, int position) {
            View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, parent, false);
            ((ImageView) tab.findViewById(R.id.image)).setImageResource(ICONS[position]);
            ((ImageView) tab.findViewById(R.id.image2)).setImageResource(ICONS2[position]);
            return tab;
        }

        @Override
        public void tabSelected(View tab) {
            ((ImageView) tab.findViewById(R.id.image)).setVisibility(View.INVISIBLE);
            ((ImageView) tab.findViewById(R.id.image2)).setVisibility(View.VISIBLE);
        }

        @Override
        public void tabUnselected(View tab) {
            ((ImageView) tab.findViewById(R.id.image)).setVisibility(View.VISIBLE);
            ((ImageView) tab.findViewById(R.id.image2)).setVisibility(View.INVISIBLE);
        }
    }

    private void changeColor(int newColor) {
        tabs.setBackgroundColor(newColor);
        mTintManager.setTintColor(newColor);
        // change ActionBar color just if an ActionBar is available
        Drawable colorDrawable = new ColorDrawable(newColor);
        Drawable bottomDrawable = new ColorDrawable(getResources().getColor(android.R.color.transparent));
        LayerDrawable ld = new LayerDrawable(new Drawable[]{colorDrawable, bottomDrawable});
        oldBackground = ld;
        currentColor = newColor;
    }





}
