package team.redrock.messageBoard.dao.impl;

import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.dao.MessageBoardDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 留言的dao
 * @Author 余歌
 * @Date 2018/12/16
 **/
public class MessageBoardDaoImpl implements MessageBoardDao {

    //数据库连接的一些配置
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/message_board?characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "191513";

    //这里用了一个单例模式 这是message_boardDao的单例
    private static MessageBoardDao instance = null;

    //static 加载这个类的时候加载数据库连接的驱动
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到dao层的单例
     *
     * @return dao层的单例
     */
    public static MessageBoardDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个message_boardDao的实例
        if (instance == null) {
            synchronized (MessageBoardDao.class) {
                if (instance == null) {
                    instance = new MessageBoardDaoImpl();
                }
            }
        }
        return instance;
    }

    /**
     * 得到数据库连接
     *
     * @return 数据库连接
     */
    private Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 插入一条留言
     *
     * @param message 留言
     */
    public void insertMessage(Message message) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO message_board(uid,text,pid,type,hidetouid) VALUE(?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, message.getUid());
            pstmt.setString(2, message.getText());
            pstmt.setInt(3, message.getPid());
            pstmt.setInt(4, message.getType());
            pstmt.setInt(5, message.getHidetouid());
            pstmt.execute();
        } catch (SQLException e) {
            try {
                pstmt.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    public List<Message> findHideMessagesByPid(int pid, int type) {
        String sql = "SELECT * FROM message_board WHERE pid = ?AND type=?";
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Message> list = new ArrayList<Message>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pid);
            res = pstmt.executeQuery();
            while (res.next()) {
                Message message = new Message();
                message.setHidetouid(res.getInt("hidetouid"));
                message.setPid(res.getInt("pid"));
                message.setText(res.getString("text"));
                message.setUid(res.getInt("uid"));
                message.setType(res.getInt("type"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(res, pstmt, con);
        }
        return list;
    }

    /**
     * 查出父节点为pid的留言的集合
     *
     * @param pid 留言的父节点
     * @return 留言的集合
     */
    public List<Message> findMessagesByPid(int pid,int type) {
        String sql = "SELECT * FROM message_board WHERE pid = ?AND type=?";
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Message> list = new ArrayList<Message>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pid);
            res = pstmt.executeQuery();
            while (res.next()) {
                Message message = new Message();
                message.setHidetouid(res.getInt("hidetouid"));
                message.setPid(res.getInt("pid"));
                message.setText(res.getString("text"));
                message.setUid(res.getInt("uid"));
                message.setType(res.getInt("type"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(res, pstmt, con);
        }
        return list;
    }

    public List<Message> findSecretMessagesByPid(int pid, int type) {
        String sql = "SELECT * FROM message_board WHERE pid = ?AND type=?";
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Message> list = new ArrayList<Message>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pid);
            res = pstmt.executeQuery();
            while (res.next()) {
                Message message = new Message();
                message.setHidetouid(res.getInt("hidetouid"));
                message.setPid(res.getInt("pid"));
                message.setText(res.getString("text"));
                message.setUid(res.getInt("uid"));
                message.setType(res.getInt("type"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(res, pstmt, con);
        }
        return list;
    }

    public void insertSecretMessage(Message message) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO message_board(uid,text,pid,type,hidetouid) VALUE(?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, message.getUid());
            pstmt.setString(2, message.getText());
            pstmt.setInt(3, message.getPid());
            pstmt.setInt(4, message.getType());
            pstmt.setInt(5, 0);
            pstmt.execute();
        } catch (SQLException e) {
            try {
                pstmt.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void close(ResultSet res, PreparedStatement pstmt, Connection con) {
        try {
            res.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
