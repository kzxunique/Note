package jcx_092217107.xx.note.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import jcx_092217107.xx.note.R;
import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.event.MyEvent;
import jcx_092217107.xx.note.base.utils.AppManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;


public abstract class XXbaseActivity extends AppCompatActivity implements View.OnClickListener {

    public XXToolBarView mToolBar;
    public FrameLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_activity_my_base);
        mToolBar = findViewById(R.id.context_toolbar);
        mView = findViewById(R.id.context_view);
        if (setContentView() != null) {
            mView.addView(setContentView());
        }
        if (isRegisterEventBus()) {
            MyEvent.register(this);
        }
        AppManager.getInstance().addActivity(this);
        iniGreate(savedInstanceState);
        iniView();
        iniClick();
        iniData();
    }

    /**
     * 是否注册事件分发
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    public abstract View setContentView();


    @Override
    public void onBackPressed() {
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            MyEvent.unregister(this);
        }
    }

    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }


    /**
     * 隐藏软键盘
     */
    public void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    public void showInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    /**
     * 设置输入框不能换行
     */
    protected void setEditTextSingleLine(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setLines(1);
            editText.setSingleLine(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(MessageEvent event) {

    }

    @Override
    public void onClick(View v) {

    }

    public void iniGreate(Bundle savedInstanceState) {

    }

    public void iniView() {

    }

    public void iniClick() {

    }

    public void iniData() {

    }
}