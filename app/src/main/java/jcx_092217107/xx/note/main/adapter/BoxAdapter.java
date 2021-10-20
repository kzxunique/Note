package jcx_092217107.xx.note.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.main.bean.BoxBean;

import java.util.List;

import androidx.annotation.Nullable;


public class BoxAdapter extends BaseQuickAdapter<BoxBean, BaseViewHolder> {
    public BoxAdapter(int layoutResId, @Nullable List<BoxBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BoxBean item) {
        helper.setText(R.id.re_box_text, item.BoxName);
        helper.addOnClickListener(R.id.re_box_xg);
    }
}
