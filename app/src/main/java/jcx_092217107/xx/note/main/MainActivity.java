package jcx_092217107.xx.note.main;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.XXApplication;
import jcx_092217107.xx.note.base.XXbaseActivity;
import jcx_092217107.xx.note.base.event.EventCode;
import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.popup.XXPopup;
import jcx_092217107.xx.note.base.utils.ListUtils;
import jcx_092217107.xx.note.base.utils.NoDoubleClickListener;
import jcx_092217107.xx.note.main.add.AddActivity;
import jcx_092217107.xx.note.main.bean.BoxBean;
import jcx_092217107.xx.note.main.fragment.FragmentOne;
import jcx_092217107.xx.note.main.fragment.FragmentThree;
import jcx_092217107.xx.note.main.fragment.FragmentTwo;
import jcx_092217107.xx.note.main.fragment.Popup;
import jcx_092217107.xx.note.main.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import razerdp.basepopup.BasePopupWindow;

public class MainActivity extends XXbaseActivity {


    private LinearLayout main_set, main_exc,main_search;
    private TabLayout main_tab;
    private ViewPager main_pager;

    private FragmentOne one = new FragmentOne();
    private FragmentTwo two = new FragmentTwo();
    private FragmentThree three = new FragmentThree();

    private List<Fragment> fragments;
    private List<String> titles;

    public static Intent getIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        return i;
    }

    @Override
    public View setContentView() {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    public void iniGreate(Bundle savedInstanceState) {
        super.iniGreate(savedInstanceState);
        mToolBar.isToolBar(false);
    }

    @Override
    public void iniView() {
        super.iniView();
        main_search = findViewById(R.id.main_search);
        main_exc = findViewById(R.id.main_exc);
        main_set = findViewById(R.id.main_set);
        main_pager = findViewById(R.id.main_pager);
        main_tab = findViewById(R.id.main_tab);

        titles = new ArrayList<>();
        titles.add("物品总览");
        titles.add("收纳盒表");
        titles.add("物品总表");

        fragments = new ArrayList<>();
        fragments.add(one);
        fragments.add(two);
        fragments.add(three);
        initMediaPlayer();
    }

    @Override
    public void iniClick() {
        super.iniClick();
        main_set.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivityForResult(AddActivity.getIntent(MainActivity.this), 100);
            }
        });
        main_search.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(SearchActivity.getIntent(MainActivity.this));
            }
        });
        main_exc.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(MainActivity.this, notification);
                r.play();
                Popup popup = new Popup(MainActivity.this);
                popup.setPopupGravityMode(BasePopupWindow.GravityMode.ALIGN_TO_ANCHOR_SIDE);
                popup.setPopupGravity(Gravity.LEFT | Gravity.BOTTOM);
                popup.showPopupWindow(R.id.main_exc_img);
            }
        });
        main_pager.setOffscreenPageLimit(fragments.size());
        main_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            //选中的ITem
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            //返回Item个数
            @Override
            public int getCount() {
                return fragments.size();
            }

            //设置标题
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        main_tab.setupWithViewPager(main_pager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    up();
                    break;
            }
        }
    }

    @Override
    public void onEventBusCome(MessageEvent event) {
        super.onEventBusCome(event);
        if (event.action == EventCode.BASE_POP) {
            switch ((String) event.o[0]) {
                case "pop_gy":
                    new XXPopup(this, "姓名: 林腾\n学号: 201811404221\n功能: 物品分类收纳盒\n版本: 初始版").showPopupWindow();
                    break;
                case "pop_hbj":
                    intColor++;
                    setColor();
                    break;
                case "pop_yy":
                    iniPlay();
                    break;
            }
        }
    }

    public void up() {
        one.UpData(0);
        two.UpData();
        three.UpData();
    }

    @Override
    public void iniData() {
        super.iniData();
        if (ListUtils.isEmpty(XXApplication.getMyApp().getShaBoxBean())) {
            List<BoxBean> beanList = new ArrayList<>();
            beanList.add(new BoxBean("电器"));
            beanList.add(new BoxBean("软妹币"));
            beanList.add(new BoxBean("利器"));
            beanList.add(new BoxBean("危险品"));
            beanList.add(new BoxBean("口罩"));
            beanList.add(new BoxBean("螺丝帽"));
            beanList.add(new BoxBean("衣服"));
            XXApplication.getMyApp().setShaBoxBean(beanList);
        }
    }

    private int intColor = 0;

    private void setColor() {
        if (intColor == 4) {
            intColor = 0;
        }
        int[] color = {R.color.C6, R.color.C9, R.color.C8, R.color.C5};
        one.setColor(getColor(color[intColor]));
        two.setColor(getColor(color[intColor]));
        three.setColor(getColor(color[intColor]));
    }

    /*
     * MediaPlayer
     * */
    public MediaPlayer mediaPlayer = new MediaPlayer();

    public void initMediaPlayer() {
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://jcx_092217107.xx.note/" + R.raw.music000));
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniPlay() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            initMediaPlayer();
        } else {
            mediaPlayer.start();
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}
