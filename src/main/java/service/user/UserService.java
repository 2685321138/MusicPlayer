package service.user;

import pojo.User;

public interface UserService {
    public User Login(String account);

    public boolean updatePwd(String account, String password);

    public boolean insertUser(String account,String password);

    public boolean updateMusic(String account,int musicId,int parttern);
}
