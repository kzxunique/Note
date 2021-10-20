package jcx_092217107.xx.note.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jcx_092217107.xx.note.base.event.MessageEvent;
import jcx_092217107.xx.note.base.event.MyEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class XXbaseFragment extends Fragment {


    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setContentView(), container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        //注册通知
        if (isRegisterEventBus()) {
            MyEvent.register(this);
        }
        iniGreate();
        iniView();
        iniClick();
        iniData();
        return view;
    }


    /**
     * 获取设置的布局
     *
     * @return
     */
    protected View getContentView() {
        return view;
    }

    /**
     * 找出对应的控件
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewById(int id) {
        return (T) getContentView().findViewById(id);
    }

    /**
     * 设置布局
     */
    protected abstract int setContentView();

    /**
     * 是否注册事件分发
     */
    protected boolean isRegisterEventBus() {
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            MyEvent.unregister(this);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(MessageEvent event) {

    }

    public void iniGreate() {

    }

    public void iniView() {

    }

    public void iniClick() {

    }

    public void iniData() {

    }

}