package jcx_092217107.xx.note.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.main.bean.BoxBean;

import java.util.List;

import androidx.annotation.Nullable;


public class AddBoxAdapter extends BaseQuickAdapter<BoxBean, BaseViewHolder> {
    public AddBoxAdapter(int layoutResId, @Nullable List<BoxBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BoxBean item) {
        helper.setText(R.id.re_add_text, item.BoxName);
        if (item.isClick) {
            helper.setBackgroundRes(R.id.re_add_text, R.drawable.icon_radius_2577e3);
        }else {
            helper.setBackgroundRes(R.id.re_add_text, R.drawable.icon_radius_c4);
        }
    }
}
