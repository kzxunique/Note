package jcx_092217107.xx.note.main.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.XXApplication;
import jcx_092217107.xx.note.base.XXbaseFragment;
import jcx_092217107.xx.note.base.event.EventCode;
import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.popup.XXPopup;
import jcx_092217107.xx.note.main.MainActivity;
import jcx_092217107.xx.note.main.adapter.BoxAdapter;
import jcx_092217107.xx.note.main.add.AddBoxPopup;
import jcx_092217107.xx.note.main.bean.BoxBean;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 熊猫先生
 * 2020/6/9:9:55
 */
public class FragmentTwo extends XXbaseFragment {

    private RecyclerView box_re;
    private List<BoxBean> boxBeans;
    private BoxAdapter adapter;

    private LinearLayout two_lyout;

    private int index;

    @Override
    protected int setContentView() {
        return R.layout.fragment_two;
    }

    @Override
    public void iniView() {
        super.iniView();
        two_lyout = findViewById(R.id.two_lyout);
        box_re = findViewById(R.id.box_re);

        boxBeans = new ArrayList<>();
        adapter = new BoxAdapter(R.layout.re_box2_item, boxBeans);
        box_re.setAdapter(adapter);
        box_re.setLayoutManager(new GridLayoutManager(getContext(), 3));
        UpData();
    }

    @Override
    public void iniClick() {
        super.iniClick();
        box_re.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(final BaseQuickAdapter adapter, View view, final int position) {
                new XXPopup(getContext(), "确定删除该项吗\n注意: 删除盒子也会删除盒子内的数据!!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String y_box = boxBeans.get(position).BoxName;
                        boxBeans.remove(position);
                        XXApplication.getMyApp().setShaBoxBean(boxBeans);
                        //修改数据
                        List<GoodBean> goodBeanList = XXApplication.getMyApp().getShaGoodBean();
                        Iterator<GoodBean> iterator = goodBeanList.iterator();
                        while (iterator.hasNext()) {
                            GoodBean student = iterator.next();
                            if (y_box.equals(student.Box)) {
                                iterator.remove();//使用迭代器的删除方法删除
                            }
                        }
                        XXApplication.getMyApp().setShaGoodBean(goodBeanList);
                        LogUtils.e(goodBeanList);
                        ((MainActivity) getActivity()).up();
                    }
                }).showPopupWindow();
            }
        });
        box_re.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.re_box_xg:
                        index = position;
                        AddBoxPopup popup = new AddBoxPopup(getActivity());
                        popup.setXG(boxBeans.get(position).BoxName);
                        popup.showPopupWindow();
                        break;
                }
            }
        });
    }

    /**
     * 更新数据
     */
    public void UpData() {
        boxBeans = XXApplication.getMyApp().getShaBoxBean();
        adapter.setNewData(boxBeans);
    }

    @Override
    public void onEventBusCome(MessageEvent event) {
        super.onEventBusCome(event);
        if (event.action == EventCode.UPADDPOP) {
            List<BoxBean> beanList = XXApplication.getMyApp().getShaBoxBean();
            String y_box = beanList.get(index).BoxName;
            String box = (String) event.o[0];
            //修改盒子
            beanList.remove(index);
            beanList.add(new BoxBean(box));
            XXApplication.getMyApp().setShaBoxBean(beanList);

            //修改数据
            List<GoodBean> goodBeanList = XXApplication.getMyApp().getShaGoodBean();
            for (int i = 0; i < goodBeanList.size(); i++) {
                if (y_box.equals(goodBeanList.get(i).Box)) {
                    goodBeanList.get(i).Box = box;
                }
            }
            XXApplication.getMyApp().setShaGoodBean(goodBeanList);
            ((MainActivity) getActivity()).up();
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    public void setColor(int color) {
        two_lyout.setBackgroundColor(color);
    }
}
