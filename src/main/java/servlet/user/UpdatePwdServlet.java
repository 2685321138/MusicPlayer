package servlet.user;

import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdatePwdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        resp.setContentType("text/html;charset=utf-8");
        String account = req.getParameter("account");
        String pwd = req.getParameter("newPassword");
        UserService userService = new UserServiceImpl();
        User user = userService.Login(account);
        if(user!=null){
            boolean flag = userService.updatePwd(account,pwd);
            if(flag){
                response = "{'result':'修改成功！'}";
            }else
                response = "{'result':'修改失败！'}";
        }
        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
