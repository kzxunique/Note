package jcx_092217107.xx.note.main.add;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.XXApplication;
import jcx_092217107.xx.note.base.XXbaseActivity;
import jcx_092217107.xx.note.base.event.EventCode;
import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.utils.GlideEngine;
import jcx_092217107.xx.note.base.utils.NoDoubleClickListener;
import jcx_092217107.xx.note.base.utils.ToastUtils;
import jcx_092217107.xx.note.main.adapter.AddBoxAdapter;
import jcx_092217107.xx.note.main.bean.BoxBean;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends XXbaseActivity {

    private EditText sub_ed_xm, sub_ed_xl, sub_ed_xb, sub_ed_dz;
    private TextView sub_ed_ok;
    private ImageView sub_img, sub_ed_addhz;

    private RecyclerView sub_ed_re;
    private AddBoxAdapter adapter;
    private List<BoxBean> beanList;

    private GoodBean goodBean;

    private String BosName;
    private String Uri = "";//图片地址记录
    private static boolean isXG;//修改

    public static Intent getIntent(Context context) {
        Intent i = new Intent(context, AddActivity.class);
        isXG = false;
        return i;
    }

    public static Intent getIntent(Context context, GoodBean goodBean) {
        Intent i = new Intent(context, AddActivity.class);
        i.putExtra("GoodBean", goodBean);
        isXG = true;
        return i;
    }

    @Override
    public View setContentView() {
        return View.inflate(this, R.layout.activity_add, null);
    }

    @Override
    public void iniGreate(Bundle savedInstanceState) {
        super.iniGreate(savedInstanceState);
        mToolBar.setToolbarStr("添加物品");
        if (isXG) {
            goodBean = (GoodBean) getIntent().getSerializableExtra("GoodBean");
        }
    }

    @Override
    public void iniView() {
        super.iniView();
        sub_img = findViewById(R.id.sub_img);
        sub_ed_addhz = findViewById(R.id.sub_ed_addhz);

        sub_ed_xm = findViewById(R.id.sub_ed_xm);
        sub_ed_xl = findViewById(R.id.sub_ed_xl);
        sub_ed_xb = findViewById(R.id.sub_ed_xb);
        sub_ed_dz = findViewById(R.id.sub_ed_dz);
        sub_ed_ok = findViewById(R.id.sub_ed_ok);

        sub_ed_re = findViewById(R.id.sub_ed_re);
        beanList = XXApplication.getMyApp().getShaBoxBean();
        adapter = new AddBoxAdapter(R.layout.re_box_item, beanList);
        sub_ed_re.setAdapter(adapter);
        sub_ed_re.setLayoutManager(new GridLayoutManager(this, 4));
        sub_ed_re.setNestedScrollingEnabled(true);
    }

    @Override
    public void iniClick() {
        super.iniClick();
        sub_ed_re.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter1, View view, int position) {
                for (int i = 0; i < beanList.size(); i++) {
                    beanList.get(i).isClick = false;
                }
                beanList.get(position).isClick = true;
                BosName = beanList.get(position).BoxName;
                adapter.notifyDataSetChanged();
            }
        });
        sub_ed_ok.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                yz();
            }
        });
        sub_ed_addhz.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                AddBoxPopup popup = new AddBoxPopup(AddActivity.this);
                popup.showPopupWindow();
            }
        });
        sub_img.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                EasyPhotos.createAlbum(AddActivity.this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.xx.three.context.submit")
                        .start(110);
            }
        });
    }

    @Override
    public void iniData() {
        super.iniData();
        if (isXG) {
            if (!TextUtils.isEmpty(goodBean.Uri)) {
                Uri = goodBean.Uri;
                GlideEngine.getInstance().loadPhoto(this, android.net.Uri.parse(goodBean.Uri), sub_img);
            }
            sub_ed_xm.setText(goodBean.Name);
            sub_ed_xl.setText(goodBean.Number);
            sub_ed_xb.setText(goodBean.Index);
            sub_ed_dz.setText(goodBean.Msg);

            for (int i = 0; i < beanList.size(); i++) {
                if (beanList.get(i).BoxName.equals(goodBean.Box)) {
                    beanList.get(i).isClick = true;
                    BosName = goodBean.Box;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            //相机或相册回调
            if (requestCode == 110) {
                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                GlideEngine.getInstance().loadPhoto(this, resultPhotos.get(0).uri, sub_img);
                Uri = resultPhotos.get(0).uri.toString();
                return;
            }
        }
    }

    @Override
    public void onEventBusCome(MessageEvent event) {
        super.onEventBusCome(event);
        if (event.action == EventCode.UPADDPOP) {
            beanList.add(new BoxBean((String) event.o[0]));
            XXApplication.getMyApp().setShaBoxBean(beanList);
            adapter.notifyDataSetChanged();
        }
    }

    private void yz() {
        final String mc = sub_ed_xm.getText().toString();
        final String pf = sub_ed_xb.getText().toString();
        final String sl = sub_ed_xl.getText().toString();
        final String dz = sub_ed_dz.getText().toString();

        if (TextUtils.isEmpty(mc)) {
            ToastUtils.show("请填写名称");
            return;
        }
        if (TextUtils.isEmpty(sl)) {
            ToastUtils.show("请填写数量");
            return;
        }
        if (TextUtils.isEmpty(pf)) {
            ToastUtils.show("请填写评分");
            return;
        }
        if (TextUtils.isEmpty(BosName)) {
            ToastUtils.show("请选择存放盒子");
            return;
        }
        if (TextUtils.isEmpty(dz)) {
            ToastUtils.show("请填写存放地址和备注信息");
            return;
        }
        if (isXG) {
            int index = 0;
            List<GoodBean> goodBeanList = XXApplication.getMyApp().getShaGoodBean();
            for (int i = 0; i < goodBeanList.size(); i++) {
                if (goodBeanList.get(i).Name.equals(goodBean.Name)) {
                    index = i;
                    break;
                }
            }
            goodBeanList.remove(index);
            goodBeanList.add(new GoodBean(mc, BosName, sl, Uri, dz, pf));
            XXApplication.getMyApp().setShaGoodBean(goodBeanList);
            ToastUtils.show("修改成功");
        } else {
            List<GoodBean> goodBeanList = XXApplication.getMyApp().getShaGoodBean();
            for (int i = 0; i < goodBeanList.size(); i++) {
                if (goodBeanList.get(i).Name.equals(mc)) {
                    ToastUtils.show("该物品已添加到盒子中");
                    return;
                }
            }
            goodBeanList.add(new GoodBean(mc, BosName, sl, Uri, dz, pf));
            XXApplication.getMyApp().setShaGoodBean(goodBeanList);
            ToastUtils.show("添加成功");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        }, 500);
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}
