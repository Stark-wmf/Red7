package team.redrock.messageBoard.dao.impl;

import team.redrock.messageBoard.been.AGREE;
import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.dao.IfAgreeDao;
import team.redrock.messageBoard.dao.MessageBoardDao;

import java.sql.*;

public class IfAgreeDaoImpl implements IfAgreeDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/red6?characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "191513";

    private static IfAgreeDao instance = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static IfAgreeDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个message_boardDao的实例
        if (instance == null) {
            synchronized (MessageBoardDao.class) {
                if (instance == null) {
                    instance = new IfAgreeDaoImpl();
                }
            }
        }
        return instance;
    }
    private Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void InsertAgree(AGREE agree) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO ifagree(username,textid,click) VALUE(?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, agree.getUsername());
            pstmt.setString(2, agree.getTextid());
            pstmt.setString(3, agree.getClick());
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
