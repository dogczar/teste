package br.com.celularegistrado.appcelularregistrado.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import br.com.celularegistrado.appcelularregistrado.Fragment.Tutorial1Fragment;
import br.com.celularegistrado.appcelularregistrado.Fragment.Tutorial2Fragment;
import br.com.celularegistrado.appcelularregistrado.Fragment.Tutorial3Fragment;
import br.com.celularegistrado.appcelularregistrado.R;

public class InfoActivity extends AppCompatActivity {

    ViewPager pager;
    ImageView ball1;
    ImageView ball2;
    ImageView ball3;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);

        ball1 = (ImageView) findViewById(R.id.ball1);
        ball2 = (ImageView) findViewById(R.id.ball2);
        ball3 = (ImageView) findViewById(R.id.ball3);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public class MyPagerAdapter extends FragmentPagerAdapter{

        private final String[] TITLES = {"Tab 1", "Tab 2", "Tab 3"};

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
        public long getItemId(int position) {
            if(position==0){
                ball1.setImageResource(R.mipmap.circlew);
                ball2.setImageResource(R.mipmap.circlen);
                ball3.setImageResource(R.mipmap.circlen);
            }else if(position==1) {
                ball1.setImageResource(R.mipmap.circlen);
                ball2.setImageResource(R.mipmap.circlew);
                ball3.setImageResource(R.mipmap.circlen);
            }else{
                ball1.setImageResource(R.mipmap.circlen);
                ball2.setImageResource(R.mipmap.circlen);
                ball3.setImageResource(R.mipmap.circlew);
            }
            return position;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                ball1.setImageResource(R.mipmap.circlew);
                ball2.setImageResource(R.mipmap.circlen);
                ball3.setImageResource(R.mipmap.circlen);
                return Tutorial1Fragment.newInstance("QRCODE", "QRCODE");
            }else if(position==1) {
                ball1.setImageResource(R.mipmap.circlen);
                ball2.setImageResource(R.mipmap.circlew);
                ball3.setImageResource(R.mipmap.circlen);
                return Tutorial2Fragment.newInstance("QRCODE", "QRCODE");
            }else{
                ball1.setImageResource(R.mipmap.circlen);
                ball2.setImageResource(R.mipmap.circlen);
                ball3.setImageResource(R.mipmap.circlew);
                return Tutorial3Fragment.newInstance("QRCODE", "QRCODE");
            }
        }
    }
}
