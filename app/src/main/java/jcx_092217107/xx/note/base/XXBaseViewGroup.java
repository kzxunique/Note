package jcx_092217107.xx.note.base;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public abstract class XXBaseViewGroup extends FrameLayout implements View.OnClickListener {

    public XXBaseViewGroup(@NonNull Context context) {
        super(context);
        init(context);
    }

    public XXBaseViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XXBaseViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public XXBaseViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        onCreate(context);
    }


    public <E extends View> E getViewById(@IdRes int id) {
        return (E) super.findViewById(id);
    }

    public void setOnClick(int... ids) {
        for (int id : ids) {
            getViewById(id).setOnClickListener(this);
        }
    }

    public void setOnClick(View... ids) {
        for (View id : ids) {
            id.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    public int dip2px(float dipValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 在avtivity中使用
     */
    protected abstract void onCreate(Context context);
}
