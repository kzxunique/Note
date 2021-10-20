package jcx_092217107.xx.note.base.popup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import jcx_092217107.xx.note.R;


/**
 * 底部滑出的popup
 */

public abstract class XXBasebottomPopup extends XXBasePopup {

    private FrameLayout default_popup_context;

    public XXBasebottomPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.default_base_buttom_popup);
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
