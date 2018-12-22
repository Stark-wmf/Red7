package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        if(user==null){
            resp.getWriter().write("fail，please login");
        }else{
            resp.getWriter().write("welcome"+user.getUsername());
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

}


