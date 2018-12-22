package team.redrock.messageBoard.been;

//import lombok.Data;

import java.util.List;

/**
 * @Description 留言的实体
 * @Author 余歌
 * @Date 2018/12/16
**/
//@Data
public class Message {

    //这条留言的id
    private int id;

    //父节点的id
    private int pid;

    //留言属性 normal 为 1 ， secrte 为 2
    private int type;
    private static final  int Normal =1;
    private static final  int Secret =2;
    private static final  int  HIDE=3;

    //用户名
    private String username;

    //留言的内容
    private String text;
    public int uid;
    public int textid;
    public  int hidetouid;

    //子节点
    private List<Message> childContent;

    //自己实现了一个构造方法后编译器不提供默认的无参构造器，这里补上
    public Message() {
    }

    //构造方法
    public Message(int uid, String text, int pid, int type,int hidetouid) {
        this.textid=textid;
        this.uid=uid;
        this.text = text;
        this.pid = pid;
        this.type = type;
        this.hidetouid=hidetouid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTextid(int textid) {
        this.textid = textid;
    }

    public int getTextid() {
        return textid;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {

        return text;
    }

    public int getPid() {
        return pid;
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setPid(int pid) {
        this.pid=pid;
    }

    public void setText(String text) {
        this.text=text;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public void setChildContent(List<Message> childList) {
        this.childContent=childList;
    }

    public int getId() {
        return id;
    }

    public List<Message> getChildContent() {
        return childContent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHidetouid() {
        return hidetouid;
    }

    public void setHidetouid(int hidetouid) {
        this.hidetouid = hidetouid;
    }
}
