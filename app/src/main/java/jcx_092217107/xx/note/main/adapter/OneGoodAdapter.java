package jcx_092217107.xx.note.main.adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.utils.GlideEngine;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.List;

import androidx.annotation.Nullable;


public class OneGoodAdapter extends BaseQuickAdapter<GoodBean, BaseViewHolder> {
    public OneGoodAdapter(int layoutResId, @Nullable List<GoodBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodBean item) {
        helper.setText(R.id.re_go_mc, item.Name);
        helper.setText(R.id.re_go_bz, item.Msg);
        helper.setText(R.id.re_go_sl, item.Number);
        helper.setText(R.id.re_go_pf, item.Index);

        ImageView sub_img = helper.getView(R.id.re_go_img);
        if (!TextUtils.isEmpty(item.Uri)) {
            GlideEngine.getInstance().loadPhoto(mContext, Uri.parse(item.Uri), sub_img);
        } else {
            helper.setImageResource(R.id.re_go_img, R.drawable.xc);
        }
    }
}
