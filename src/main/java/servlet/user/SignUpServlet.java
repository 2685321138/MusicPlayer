package servlet.user;

import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String response = "";
        String account = req.getParameter("account");
        String pwd = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.insertUser(account, pwd);
        if (flag) {
            response = "{'result':'注册成功！'}";
        } else {
            response = "{'result':'注册失败！'}";
        }
        resp.getWriter().append(response).flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
