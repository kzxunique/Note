package jcx_092217107.xx.note.base.event;

/**
 * View和adapter通信为100-999
 * view和actavaty通信为1000+
 * 特殊通信必须创建实体
 */
public class MessageEvent {
    /**
     * View和adapter通信为100-999
     * view和actavaty通信为1000+
     * 特殊通信必须创建实体
     */
    public int action;
    /**
     * View和adapter通信为100-999
     * view和actavaty通信为1000+
     * 特殊通信必须创建实体
     */
    public Object[] o;

    /**
     * View和adapter通信为100-999
     * view和actavaty通信为1000+
     * 特殊通信必须创建实体
     */
    public MessageEvent(int action, Object... o) {
        this.action = action;
        this.o = o;
    }

}