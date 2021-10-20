package jcx_092217107.xx.note.main.add;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.event.EventCode;
import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.popup.XXBaseCenterPopup;
import jcx_092217107.xx.note.base.utils.NoDoubleClickListener;
import jcx_092217107.xx.note.base.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

public class AddBoxPopup extends XXBaseCenterPopup {

    EditText pop_edt;
    TextView pop_qx, pop_qd, pop_edt_tit;

    public AddBoxPopup(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        return View.inflate(mContext, R.layout.pop_add_bos, null);
    }

    @Override
    protected void iniView(Activity mContext) {
        pop_edt = findViewById(R.id.pop_edt);
        pop_qx = findViewById(R.id.pop_qx);
        pop_qd = findViewById(R.id.pop_qd);
        pop_edt_tit = findViewById(R.id.pop_edt_tit);
        iniClick();
    }

    private void iniClick() {
        pop_qd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                String edt = pop_edt.getText().toString();
                if (TextUtils.isEmpty(edt)) {
                    ToastUtils.show("请输入收纳盒子名称");
                    return;
                }
                EventBus.getDefault().post(new MessageEvent(EventCode.UPADDPOP, edt));
                dismiss();
            }
        });

        pop_qx.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                dismiss();
            }
        });
    }

    public void setXG(String name) {
        pop_edt_tit.setText("修改收纳盒子");
        pop_edt.setText(name);
    }


}
