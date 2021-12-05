package servlet.user;

import dao.BaseDao;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String response = "";
        String account  = req.getParameter("account");
        String pwd = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user = userService.Login(account);

        if(user!=null){
            JsonUtil jsonUtil = new JsonUtil();
            response = jsonUtil.getUserJson(user);
        }
        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
