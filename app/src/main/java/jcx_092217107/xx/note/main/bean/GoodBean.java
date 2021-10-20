package jcx_092217107.xx.note.main.bean;


import java.io.Serializable;

public class GoodBean implements Serializable {
    public String Name;//名字
    public String Box;//类别名
    public String Number;//数量
    public String Uri;//图
    public String Msg;//备注
    public String Index;//重要分数

    public GoodBean(String name, String box, String number, String uri, String msg, String index) {
        Name = name;
        Box = box;
        Number = number;
        Uri = uri;
        Msg = msg;
        Index = index;
    }
}
