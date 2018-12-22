package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.dao.Dao;
import team.redrock.messageBoard.service.MessageBoardService;
import team.redrock.messageBoard.service.impl.MessageBoardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private MessageBoardService messageBoardService;

    @Override
    public void init() {
        messageBoardService = MessageBoardServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码格式为utf-8
        String username = req.getParameter("username");
        int uid = Integer.parseInt(req.getParameter("uid"));
        if(username == null){
            System.out.println("username null");
        }
        String password = req.getParameter("password");//从jsp中获取password
        if (Dao.checkLogin(uid, password)) { //dao层中判断，如果为true，跳转到欢迎界面
            HttpSession session=req.getSession();
             session.setAttribute("username", username);
            session.setAttribute("uid", uid);
             session.setAttribute("password", password);
            resp.getWriter().write("sucess");
        }else{   //如果为false，跳转到登录界面，并返回错误信息.
            resp.getWriter().write("fail");

        }


    }
    }

