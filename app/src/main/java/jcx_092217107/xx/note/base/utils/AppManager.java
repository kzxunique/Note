package jcx_092217107.xx.note.base.utils;

import android.app.Activity;
import android.app.Service;

import java.util.LinkedList;

/**
 * 应用程序Activity管理类：用于Activity,Service管理和应用程序退出
 */
public class AppManager {

    private static LinkedList<Activity> activityStack = null;
    private static LinkedList<Service> serviceStack = null;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null)
            activityStack = new LinkedList<Activity>();
        activityStack.add(activity);
    }

    public void delActivity(Activity activity) {
        if (activityStack == null)
            return;
        activityStack.remove(activity);
    }

    /**
     * 删除除栈顶的所有activity
     */
    public void delActivity2() {
        if (activityStack == null)
            return;
        for (int i = 0; i < activityStack.size() - 1; i++) {
            activityStack.get(i).finish();
        }
    }

    /**
     * 添加Service到堆栈
     */
    public void addService(Service svc) {

        if (serviceStack == null)
            serviceStack = new LinkedList<Service>();
        serviceStack.add(svc);
    }

    public void delService(Service svc) {
        if (serviceStack == null)
            return;
        serviceStack.remove(svc);
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    @Deprecated
    public Activity currentActivity() {
        if (activityStack == null)
            return null;
        Activity activity = activityStack.getLast();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.getLast();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (serviceStack != null) {
            for (int i = 0; i < serviceStack.size(); i++) {
                if (null != serviceStack.get(i)) {
                    serviceStack.get(i).stopSelf();
                }
            }
            serviceStack.clear();
        }
        if (activityStack != null) {
            for (int i = 0; i < activityStack.size(); i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 存在就关闭
     */
    public boolean isExistActivity(Class<?> cls) {
        boolean isExist = false;
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(cls)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    /**
     * 结束除指定Activity之外的
     */
    public void finishAllActivity2(Class<?> cls) {
        for (int i = activityStack.size() - 1; i > 0; i--) {
            if (!activityStack.get(i).getClass().equals(cls)) {
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            //只退出应用不再结束进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }
}