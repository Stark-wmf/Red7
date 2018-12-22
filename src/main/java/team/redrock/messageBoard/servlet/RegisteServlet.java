package team.redrock.messageBoard.servlet;
import team.redrock.messageBoard.dao.Dao;
import team.redrock.messageBoard.service.MessageBoardService;
import team.redrock.messageBoard.service.impl.MessageBoardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registe")
public class RegisteServlet extends HttpServlet {
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
        String username=req.getParameter("username");//从注册界面获得username
        String password=req.getParameter("password");//从注册界面获得password
        int uid = Integer.parseInt(req.getParameter("uid"));
        Dao.registe(uid,username, password);
    }
}