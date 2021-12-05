package service.user;

import dao.BaseDao;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import pojo.User;

import java.sql.Connection;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }


    @Override
    public User Login(String account) {
        Connection connection =null;
        User user = null;
        try{
            connection = BaseDao.getConnection();
            user = userDao.getUser(connection,account);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    @Override
    public boolean updatePwd(String account, String password) {
        boolean flag = false;
        Connection connection = null;
        User user = new User();
        user.setAccount(account);
        user.setPassoword(password);
        try {
            connection = BaseDao.getConnection();
            if(userDao.changePassword(connection,user)>0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public boolean insertUser(String account, String password) {
        boolean flag = false;
        Connection connection = null;
        User user = new User();
        user.setAccount(account);
        user.setPassoword(password);
        try{
            connection = BaseDao.getConnection();
            if(userDao.insertUser(connection,user)>0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public boolean updateMusic(String account, int musicId, int parttern) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = BaseDao.getConnection();
            if(userDao.saveMusic(connection,account,musicId,parttern)>0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }
}
