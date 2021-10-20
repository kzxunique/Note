package jcx_092217107.xx.note.base.http;

import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;


public abstract class Callback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    public Callback() {
    }

    public Callback(Type type) {
        this.type = type;
    }

    public Callback(Class<T> clazz) {
        this.clazz = clazz;
    }

}
