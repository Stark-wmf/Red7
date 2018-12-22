package team.redrock.messageBoard.filter;

import team.redrock.messageBoard.dao.Dao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName="HideFilter",urlPatterns="/hideinfo")
public class HideFilter implements Filter {
    public void destroy(){

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = ((HttpServletRequest) req).getSession();

        int uid = (Integer) session.getAttribute("uid");
        int hidetouid = Dao.checkHide(uid);
       if(uid==hidetouid){

            chain.doFilter(request, response);
        }else{
            resp.getWriter().write("no acess");
        }
    }
}