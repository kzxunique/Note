package jcx_092217107.xx.note.base.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.util.Map;

/**
 * Http请求二次包装 方便更换
 */

public class OKHttp {

    public static <T> void post(Map map, String URL, StringCallback<T> Callback) {
        if (map == null) {
            return;
        }
        OkGo.getInstance().cancelTag(URL);
        HttpParams params = new HttpParams();
        for (Object key : map.keySet()) {
            params.put(key.toString(), map.get(key).toString());
        }
        OkGo.<T>post(URL)
                .tag(URL)
                .params(params)
                .execute(Callback);
    }

    public static <T> void post(String Json, String URL, StringCallback<T> Callback) {
        if (Json == null) {
            return;
        }
        OkGo.getInstance().cancelTag(URL);
        OkGo.<T>post(URL)
                .tag(URL)
                .upJson(Json)
                .execute(Callback);
    }


}