package dao.user;

import dao.BaseDao;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoImpl implements UserDao{
    @Override
    public User getUser(Connection connection, String account) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        if(connection!=null){
            String sql = "select * from user where account = ?";
            Object[] params = {account};
            resultSet = BaseDao.execute(connection,sql,params,resultSet,preparedStatement);
            if(resultSet.next()){
                user.setAccount(account);
                user.setMusicId(resultSet.getInt("music_id"));
                user.setPassoword(resultSet.getString("password"));
                user.setPattern(resultSet.getInt("pattern"));
                user.setCreateTime(resultSet.getDate("create_time"));
                user.setChangeTime(resultSet.getDate("change_time"));
                user.setRemark(resultSet.getString("remark"));
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public int insertUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        int insertrow = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        if(connection!=null){
            String sql = "insert into user(account,password,create_time) values(?,?,?)";
            Object[] params = {user.getAccount(),user.getPassoword(),simpleDateFormat.format(new Date())};
            insertrow = BaseDao.execute(connection, sql, params, preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return insertrow;
    }

    @Override
    public int changePassword(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        int updaterow = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        if (connection != null) {
            String sql = "update user set password=?, change_time=? where account = ?";
            Object[] params = {user.getPassoword(),simpleDateFormat.format(new Date()),user.getAccount()};
            updaterow = BaseDao.execute(connection,sql,params,preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return updaterow;
    }

    @Override
    //App退出时候保存的数据
    public int saveMusic(Connection connection, String account, int musicId, int pattern) throws SQLException {
        PreparedStatement preparedStatement = null;
        int updaterow = 0;
        if (connection != null) {
            String sql = "update user set music_id=?, pattern=? where account = ?";
            Object[] params = {musicId,pattern,account};
            updaterow = BaseDao.execute(connection,sql,params,preparedStatement);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return updaterow;
    }
}
