package team.redrock.messageBoard.servlet;

import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.service.MessageBoardService;
import team.redrock.messageBoard.service.impl.MessageBoardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
@WebServlet("/hideinfo")
//这个加了过滤器HideFilter来实现
public class HideInfoServlet extends HttpServlet {
    //service的单例
    MessageBoardService messageBoardService;


    /**
     * 重写初始化的方法 为service赋值
     */
    @Override
    public void init() {
        messageBoardService = MessageBoardServiceImpl.getInstance();
    }

    /**
     * info是一个get请求 一般只获得信息 不对服务器的数据做改动的使用get请求
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//
//        List<Message> messageList = messageBoardService.findAllMessages();
//        String res = messageBoardService.messagesToJson(messageList);
//        List<Message> messageList1 = secretMessageBoardService.findAllSecretMessages();
//        String res1 = secretMessageBoardService.secretmessagesToJson(messageList1);
        List<Message> messageList = messageBoardService.findAllHideMessages();
        String res = messageBoardService.messagesToJson(messageList);

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
