package jcx_092217107.xx.note.base.popup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import razerdp.basepopup.BasePopupWindow;

/**
 * 底部滑出的popup
 */

public abstract class XXBasePopup extends BasePopupWindow implements View.OnClickListener {

    public Activity mContext;

    public XXBasePopup(Context context) {
        super(context);
        mContext = (Activity) context;
        iniGreate(mContext);
        iniView(mContext);
    }

    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    protected abstract void iniGreate(Activity mContext);

    protected abstract void iniView(Activity mContext);


    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0f, 300);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0f, 1f, 300);
    }


}
