package jcx_092217107.xx.note.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;


import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.event.EventCode;
import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.utils.NoDoubleClickListener;

import org.greenrobot.eventbus.EventBus;

import razerdp.basepopup.BasePopupWindow;

/**
 * 熊猫先生
 * 2020/5/31:16:59
 */
public class Popup extends BasePopupWindow {

    private Activity mContext;
    private TextView pop_gy, pop_hbj, pop_yy;

    public Popup(Context context) {
        super(context);
        setBackgroundColor(Color.TRANSPARENT);
        mContext = (Activity) context;
        iniView(mContext);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.tv_pop);
    }

    private void iniView(Activity mContext) {
        pop_gy = findViewById(R.id.pop_gy);
        pop_hbj = findViewById(R.id.pop_hbj);
        pop_yy = findViewById(R.id.pop_yy);

        pop_gy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                EventBus.getDefault().post(new MessageEvent(EventCode.BASE_POP, "pop_gy"));
                dismiss();
            }
        });
        pop_hbj.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                EventBus.getDefault().post(new MessageEvent(EventCode.BASE_POP, "pop_hbj"));
                dismiss();
            }
        });
        pop_yy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                EventBus.getDefault().post(new MessageEvent(EventCode.BASE_POP, "pop_yy"));
                dismiss();
            }
        });
    }


}
