package jcx_092217107.xx.note.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.utils.NoDoubleClickListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class XXToolBarView extends XXBaseViewGroup {

    public LinearLayout view_toolbar_back, view_toolbar;
    public TextView view_toolbar_text;
    public FrameLayout view_toolbar_right;

    public XXToolBarView(@NonNull Context context) {
        super(context);
    }

    public XXToolBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XXToolBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public XXToolBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onCreate(Context context) {
        LayoutInflater.from(context).inflate(R.layout.default_toolbar_layout, this);
        view_toolbar = findViewById(R.id.view_toolbar);
        view_toolbar_back = findViewById(R.id.view_toolbar_back);
        view_toolbar_text = findViewById(R.id.view_toolbar_text);
        view_toolbar_right = findViewById(R.id.view_toolbar_right);
        view_toolbar_right.setVisibility(GONE);
        iniClick(context);
    }

    private void iniClick(final Context context) {
        view_toolbar_back.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ((Activity) context).onBackPressed();
            }
        });
    }

    /**
     * 设置toolber右边布局
     */
    public void setToolbarRightView(View Right) {
        if (Right != null) {
            view_toolbar_right.setVisibility(VISIBLE);
            view_toolbar_right.removeAllViews();
            view_toolbar_right.addView(Right);
        }
    }

    /**
     * 是否显示toolbar 默认显示出来
     */
    public void isToolBar(boolean Visibility) {
        if (Visibility) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
    }

    /**
     * ToolBar是否显示
     */
    public boolean getIsBar() {
        if (getVisibility() == VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置Toolbar文字
     */
    public void setToolbarStr(String text) {
        view_toolbar_text.setText(text);
    }
    /**
     * 设置Toolbar文字
     */
    /**
     * 是否显示toolbar 默认显示出来
     */
    public void isBack(boolean Visibility) {
        if (Visibility) {
            view_toolbar_back.setVisibility(VISIBLE);
        } else {
            view_toolbar_back.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置Toolbar颜色
     */
    public void setToolbarColor(int color) {
        view_toolbar.setBackgroundColor(color);
    }

    /**
     * 设置Toolbar文字颜色
     */
    public void setToolbarStrColor(int color) {
        view_toolbar_text.setTextColor(color);
    }


}
