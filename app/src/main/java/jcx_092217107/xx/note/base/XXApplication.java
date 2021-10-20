package jcx_092217107.xx.note.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import jcx_092217107.xx.note.base.utils.JSONTools;
import jcx_092217107.xx.note.main.bean.BoxBean;
import jcx_092217107.xx.note.main.bean.GoodBean;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


/**
 * 熊猫先生
 */
public class XXApplication extends Application {
    private static XXApplication xxApplication;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        xxApplication = this;
        mContext = getApplicationContext();
        //tost配置
        Toasty.Config.getInstance()
                .setTextSize(14)
                .apply();


    }

    public List<GoodBean> getShaGoodBean() {
        SharedPreferences sharedPreferences = getSharedPreferences("Goods", Context.MODE_PRIVATE);
        String bean = sharedPreferences.getString("GoodBean", "");
        List<GoodBean> list = new ArrayList<>();
        if (!TextUtils.isEmpty(bean)) {
            list = JSONTools.fromJson(bean, new TypeToken<ArrayList<GoodBean>>() {
            }.getType());
        }
        return list;
    }

    public void setShaGoodBean(List<GoodBean> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("Goods", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        String jsonString = JSONTools.toJson(list);
        mEditor.putString("GoodBean", jsonString);
        mEditor.apply();
    }

    public List<BoxBean> getShaBoxBean() {
        SharedPreferences sharedPreferences = getSharedPreferences("Box", Context.MODE_PRIVATE);
        String bean = sharedPreferences.getString("BoxBean", "");
        List<BoxBean> list = new ArrayList<>();
        if (!TextUtils.isEmpty(bean)) {
            list = JSONTools.fromJson(bean, new TypeToken<ArrayList<BoxBean>>() {
            }.getType());
        }
        return list;
    }

    public void setShaBoxBean(List<BoxBean> list) {
        SharedPreferences sharedPreferences = getSharedPreferences("Box", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        String jsonString = JSONTools.toJson(list);
        mEditor.putString("BoxBean", jsonString);
        mEditor.apply();
    }

    public static XXApplication getMyApp() {
        if (xxApplication == null) {
            synchronized (XXApplication.class) {
                if (xxApplication == null) {
                    xxApplication = new XXApplication();
                }
            }
        }
        return xxApplication;
    }

    public static Context getContext() {
        return mContext;
    }

}

