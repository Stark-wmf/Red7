package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.AGREE;
import team.redrock.messageBoard.service.IfAgreeService;
import team.redrock.messageBoard.service.MessageBoardService;
import team.redrock.messageBoard.service.impl.IfAgreeServiceImpl;
import team.redrock.messageBoard.service.impl.MessageBoardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
@WebServlet("/agree")
//点赞
public class AgreeServlet extends HttpServlet {
    //失败
    private static final String ERROR="{\"status\":\"10001\"}";
    //成功
    private static final String OK="{\"status\":\"10000\"}";
    //service的单例
    private IfAgreeService ifAgreeService;

    /**
     * 重写初始化方法 为service赋值
     */
    @Override
    public void init() {
        ifAgreeService = IfAgreeServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username =(String) session.getAttribute("username");
        String textid = req.getParameter("textid");
        String click = req.getParameter("click");
        AGREE agree = new AGREE(username,textid,click);
        String res = ERROR;
        if ( ifAgreeService.insertContent(agree)) {
            res = OK;
        }

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream()
                )
        );

        writer.write(res);
        writer.flush();
        writer.close();
    }
}
