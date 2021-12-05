package service.music;

import dao.BaseDao;
import dao.music.MusicDao;
import dao.music.MusicDaoImpl;
import pojo.Music;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MusicServiceImpl implements MusicService {
   private MusicDao musicDao;
    public MusicServiceImpl() {
         musicDao = new MusicDaoImpl();
    }

    @Override
    // 获取歌曲列表
    public List<Music> getMusicList() {
        List<Music> list = new ArrayList<>();
        Connection connection =null;
        try{
            connection = BaseDao.getConnection();
            list = musicDao.getMusicList(connection);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return list;
    }
}
