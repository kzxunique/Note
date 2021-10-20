package jcx_092217107.xx.note.base.popup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import jcx_092217107.xx.note.R;


/**
 * 中间的popup
 */

public abstract class XXBaseCenterPopup extends XXBasePopup {

    private FrameLayout default_popup_context;

    public XXBaseCenterPopup(Context context) {
        super(context);
//        setBlurBackgroundEnable(true);
//        setPopupWindowFullScreen(true);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.default_base_center_popup);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return null;
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return null;
    }

    @Override
    protected void iniGreate(Activity mContext) {
        default_popup_context = findViewById(R.id.default_popup_context);
        if (setContentView() != null) {
            default_popup_context.addView(setContentView());
        }
    }

    public abstract View setContentView();

}
