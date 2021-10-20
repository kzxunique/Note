package jcx_092217107.xx.note.base.http;

import java.io.Serializable;


public class xxResponse<T> implements Serializable {
    public String showapi_res_code;
    public String showapi_res_error;
    public String showapi_res_id;
    public T showapi_res_body;
}
