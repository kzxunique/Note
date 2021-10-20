package jcx_092217107.xx.note.base.utils;

import jcx_092217107.xx.note.base.XXApplication;

import es.dmoral.toasty.Toasty;

public class ToastUtils {
    public static void show(String Msg) {
        Toasty.normal(XXApplication.getContext(), Msg).show();
    }
}
