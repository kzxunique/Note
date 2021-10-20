package jcx_092217107.xx.note.main.search;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.XXApplication;
import jcx_092217107.xx.note.base.XXbaseActivity;
import jcx_092217107.xx.note.base.utils.ListUtils;
import jcx_092217107.xx.note.base.utils.NoDoubleClickListener;
import jcx_092217107.xx.note.main.adapter.OneGoodAdapter;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends XXbaseActivity {

    private RecyclerView sear_re;
    private OneGoodAdapter adapter;
    private List<GoodBean> goodBeanList;

    private EditText sear_edt;
    private TextView sear_edt_ok;

    public static Intent getIntent(Context context) {
        Intent i = new Intent(context, SearchActivity.class);
        return i;
    }

    @Override
    public View setContentView() {
        return View.inflate(this, R.layout.activity_search, null);
    }

    @Override
    public void iniGreate(Bundle savedInstanceState) {
        super.iniGreate(savedInstanceState);
        mToolBar.setToolbarStr("模糊查询");
    }

    @Override
    public void iniView() {
        super.iniView();
        sear_re = findViewById(R.id.sear_re);
        sear_edt_ok = findViewById(R.id.sear_edt_ok);
        sear_edt = findViewById(R.id.sear_edt);
        goodBeanList = new ArrayList<>();
        adapter = new OneGoodAdapter(R.layout.re_good2_item, goodBeanList);
        sear_re.setAdapter(adapter);
        sear_re.setLayoutManager(new LinearLayoutManager(this));
        setUp();
    }

    @Override
    public void iniClick() {
        super.iniClick();
        sear_edt_ok.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                setUp();
            }
        });
    }

    private void setUp() {
        String text = sear_edt.getText().toString();
        if (ListUtils.isEmpty(XXApplication.getMyApp().getShaGoodBean())) {
            return;
        }
        if (TextUtils.isEmpty(text)) {
            goodBeanList = XXApplication.getMyApp().getShaGoodBean();
        } else {
            goodBeanList.clear();
            List<GoodBean> list = XXApplication.getMyApp().getShaGoodBean();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).Box.contains(text) ||
                        list.get(i).Index.contains(text) ||
                        list.get(i).Msg.contains(text) ||
                        list.get(i).Name.contains(text) ||
                        list.get(i).Number.contains(text)) {
                    goodBeanList.add(list.get(i));
                }
            }
        }
        adapter.setNewData(goodBeanList);


    }
}
