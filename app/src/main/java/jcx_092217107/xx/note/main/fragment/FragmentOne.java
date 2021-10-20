package jcx_092217107.xx.note.main.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.XXApplication;
import jcx_092217107.xx.note.base.XXbaseFragment;
import jcx_092217107.xx.note.base.utils.ListUtils;
import jcx_092217107.xx.note.main.adapter.AddBoxAdapter;
import jcx_092217107.xx.note.main.adapter.OneGoodAdapter;
import jcx_092217107.xx.note.main.bean.BoxBean;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 季晨昕
 * 2020/6/9:9:55
 */
public class FragmentOne extends XXbaseFragment {

    private RecyclerView one_box_re;
    private List<BoxBean> boxBeans;
    private AddBoxAdapter box_adapter;

    private RecyclerView one_good_re;
    private List<GoodBean> goodBeanList;
    private OneGoodAdapter good_adapter;

    private LinearLayout lyout,one_lyout;

    @Override
    protected int setContentView() {
        return R.layout.fragment_one;
    }

    @Override
    public void iniView() {
        super.iniView();
        lyout = findViewById(R.id.lyout);
        one_lyout = findViewById(R.id.one_lyout);
        one_box_re = findViewById(R.id.one_box_re);
        one_good_re = findViewById(R.id.one_good_re);
        boxBeans = new ArrayList<>();
        goodBeanList = new ArrayList<>();
        box_adapter = new AddBoxAdapter(R.layout.re_box_item, boxBeans);
        good_adapter = new OneGoodAdapter(R.layout.re_good2_item, goodBeanList);
        one_box_re.setAdapter(box_adapter);
        one_box_re.setLayoutManager(new GridLayoutManager(getContext(), 5));
        one_box_re.setNestedScrollingEnabled(true);
        one_good_re.setAdapter(good_adapter);
        one_good_re.setLayoutManager(new LinearLayoutManager(getContext()));
        one_good_re.setNestedScrollingEnabled(true);
        UpData(0);

        one_box_re.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                UpData(position);
            }
        });
    }

    /**
     * 更新数据
     */
    public void UpData(int index) {
        boxBeans = XXApplication.getMyApp().getShaBoxBean();
        if (ListUtils.isEmpty(boxBeans)) {
            lyout.setVisibility(View.GONE);
            box_adapter.setNewData(boxBeans);
            return;
        }
        lyout.setVisibility(View.VISIBLE);
        for (int i = 0; i < boxBeans.size(); i++) {
            boxBeans.get(i).isClick = false;
        }
        boxBeans.get(index).isClick = true;
        box_adapter.setNewData(boxBeans);

        goodBeanList.clear();
        List<GoodBean> beans = XXApplication.getMyApp().getShaGoodBean();
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).Box.equals(boxBeans.get(index).BoxName)) {
                goodBeanList.add(beans.get(i));
            }
        }
        good_adapter.setNewData(goodBeanList);
    }

    public void setColor(int color) {
        one_lyout.setBackgroundColor(color);
    }
}
