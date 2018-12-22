package team.redrock.messageBoard.dao;

import team.redrock.messageBoard.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
    private static Connection con = null;
    private static PreparedStatement ps =null;
    private static ResultSet rs = null;

    public static int checkHide(int uid) {//检查登录，这里传入的两个参数分别是从jsp传过来的账号和密码
        con = DBHelper.getConnection();
        int pwd = 0;
        String sql ="select * from message_board where uid=?AND type=3";//查询语句
        try {
            if (con == null){
                System.out.println("con为空");
            }else
                ps = con.prepareStatement(sql);
            ps.setInt(1, uid);//赋值
            rs = ps.executeQuery();//执行查询语句
            if (rs.next()) {

                pwd = rs.getInt("hidetouid");//找到数据类里面user所对应的passwrod

            }else{
                return 0;
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } finally { //这里是一些操作数据库之后的一些关闭操作
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ps = null;
            }
        }
        return pwd;
    }


    public static boolean checkLogin(int uid, String password) {//检查登录，这里传入的两个参数分别是从jsp传过来的账号和密码
        con = DBHelper.getConnection();
        String sql ="select * from user7 where uid=?";//查询语句
        try {
            if (con == null){
                System.out.println("con为空");
            }else
                ps = con.prepareStatement(sql);
            ps.setInt(1, uid);//赋值
            rs = ps.executeQuery();//执行查询语句
            if (rs.next()) {

                String pwd = rs.getString("password");//找到数据类里面user所对应的passwrod
                if (pwd.equals(password)) {//把我们从数据库中找出来的password和从jsp中传过来的passwrod比较
                    return true; //ture代表验证成功
                }else{
                    return false;//false代表验证失败
                }
            }else{
                return false;
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } finally { //这里是一些操作数据库之后的一些关闭操作
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ps = null;
            }
        }
        return false;
    }
    public static void registe(int uid,String username,String password){//向数据库注册一个新的用户
        con=DBHelper.getConnection();//通过DBHelper得到Connection
        String sql="insert into user7 values (?,?,?)";//这个语句是向表单插入一个user,username和password先设置为？,后面赋值
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, uid);//给username赋值
            ps.setString(2, username);//给password赋值
            ps.setString(3, password);//给password赋值
            int b=ps.executeUpdate();//执行插入语句，并返回一个int值，大于0表示添加成功，小于0表示添加失败.
            if(b>0){
                System.out.println("数据添加成功");
            }
            else{
                System.out.println("数据添加失败");
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }finally {  //这里是一些操作数据库之后的一些关闭操作
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ps = null;
            }
        }
    }
}
