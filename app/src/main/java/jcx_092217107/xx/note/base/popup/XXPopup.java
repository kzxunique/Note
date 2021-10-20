package jcx_092217107.xx.note.base.popup;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import jcx_092217107.xx.note.R;


/**
 * 熊猫先生
 * 2019/5/7:11:45
 */

public class XXPopup extends XXBaseCenterPopup {

    private TextView xxp_title, xxp_context;
    private TextView xxp_ok, xxp_no;

    public String titlet, contextt, okt, not;
    public View.OnClickListener mLinstenerOK, mLinstenerNO;

    /**
     * 标题加内容提示
     */
    public XXPopup(Context context, String titlet, String contextt) {
        super(context);
        this.titlet = titlet;
        this.contextt = contextt;
        yz();
    }

    /**
     * 单内容提示
     */
    public XXPopup(Context context, String contextt) {
        super(context);
        this.contextt = contextt;
        yz();
    }

    /**
     * 单内容提示+1按钮
     */
    public XXPopup(Context context, String contextt, View.OnClickListener mLinstenerOK) {
        super(context);
        this.contextt = contextt;
        this.mLinstenerOK = mLinstenerOK;
        yz();
    }

    /**
     * 单内容提示+1按钮文字+1按钮
     */
    public XXPopup(Context context, String contextt, String okt, View.OnClickListener mLinstenerOK) {
        super(context);
        this.okt = okt;
        this.contextt = contextt;
        this.mLinstenerOK = mLinstenerOK;
        yz();
    }

    /**
     * 单内容提示+2按钮
     */
    public XXPopup(Context context, String contextt, View.OnClickListener mLinstenerOK, View.OnClickListener mLinstenerNO) {
        super(context);
        this.contextt = contextt;
        this.mLinstenerOK = mLinstenerOK;
        this.mLinstenerNO = mLinstenerNO;
        yz();
    }

    /**
     * 单内容提示+2按钮文字+2按钮
     */
    public XXPopup(Context context, String contextt, String okt, String not, View.OnClickListener mLinstenerOK, View.OnClickListener mLinstenerNO) {
        super(context);
        this.okt = okt;
        this.not = not;
        this.contextt = contextt;
        this.mLinstenerOK = mLinstenerOK;
        this.mLinstenerNO = mLinstenerNO;
        yz();
    }


    public interface OnClickListener {
        void onClick(View v);
    }

    @Override
    public View setContentView() {
        return View.inflate(getContext(), R.layout.xx_popup, null);
    }

    @Override
    protected void iniView(Activity mContext) {
        xxp_title = findViewById(R.id.xxp_title);
        xxp_context = findViewById(R.id.xxp_context);
        xxp_ok = findViewById(R.id.xxp_ok);
        xxp_no = findViewById(R.id.xxp_no);
        iniClick();
    }

    private void iniClick() {
        xxp_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinstenerOK != null) {
                    dismiss();
                    mLinstenerOK.onClick(v);
                } else {
                    dismiss();
                }
            }
        });
        xxp_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinstenerNO != null) {
                    dismiss();
                    mLinstenerNO.onClick(v);
                } else {
                    dismiss();
                }
            }
        });
    }

    private void yz() {
        if (TextUtils.isEmpty(titlet)) {
            xxp_title.setVisibility(View.GONE);
        } else {
            xxp_title.setVisibility(View.VISIBLE);
            xxp_title.setText(titlet);
        }
        if (!TextUtils.isEmpty(contextt)) {
            xxp_context.setText(contextt);
        }
        if (!TextUtils.isEmpty(not)) {
            xxp_no.setText(not);
        }
        if (!TextUtils.isEmpty(okt)) {
            xxp_ok.setText(okt);
        }
        if (mLinstenerNO == null) {
            xxp_no.setVisibility(View.GONE);
        } else {
            xxp_no.setVisibility(View.VISIBLE);
        }
    }


}
