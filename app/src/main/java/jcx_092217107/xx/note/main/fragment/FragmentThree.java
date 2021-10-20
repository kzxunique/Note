package jcx_092217107.xx.note.main.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.XXApplication;
import jcx_092217107.xx.note.base.XXbaseFragment;
import jcx_092217107.xx.note.base.popup.XXPopup;
import jcx_092217107.xx.note.main.MainActivity;
import jcx_092217107.xx.note.main.adapter.GoodAdapter;
import jcx_092217107.xx.note.main.add.AddActivity;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FragmentThree extends XXbaseFragment {

    private RecyclerView good_re;
    private List<GoodBean> goodBeanList;
    private GoodAdapter adapter;

    private LinearLayout three_lyout;

    @Override
    protected int setContentView() {
        return R.layout.fragment_three;
    }

    @Override
    public void iniView() {
        super.iniView();
        good_re = findViewById(R.id.good_re);
        three_lyout = findViewById(R.id.three_lyout);

        goodBeanList = new ArrayList<>();
        adapter = new GoodAdapter(R.layout.re_good_item, goodBeanList);
        good_re.setAdapter(adapter);
        good_re.setLayoutManager(new LinearLayoutManager(getContext()));
        UpData();
    }

    @Override
    public void iniClick() {
        super.iniClick();
        good_re.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(final BaseQuickAdapter adapter, View view, final int position) {
                new XXPopup(getContext(), "确定删除该项吗?", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goodBeanList.remove(position);
                        XXApplication.getMyApp().setShaGoodBean(goodBeanList);
                        ((MainActivity) getActivity()).up();
                    }
                }).showPopupWindow();
            }
        });
        good_re.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.re_go_xg:
                        startActivityForResult(AddActivity.getIntent(getContext(), goodBeanList.get(position)), 100);
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case 100:
                    ((MainActivity) getActivity()).up();
                    break;
            }
        }
    }

    /**
     * 更新数据
     */
    public void UpData() {
        goodBeanList = XXApplication.getMyApp().getShaGoodBean();
        adapter.setNewData(goodBeanList);
    }

    public void setColor(int color) {
        three_lyout.setBackgroundColor(color);
    }
}
