package servlet.music;

import pojo.Music;
import service.music.MusicService;
import service.music.MusicServiceImpl;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetMusicListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        String response = "";
        MusicService musicService = new MusicServiceImpl();
        List<Music> list = musicService.getMusicList();
        if(list.size()>0){
            JsonUtil jsonUtil = new JsonUtil();
            response = jsonUtil.getJsonArray(list);
           // System.out.println(list.get(0).getAuthor());
            System.out.println(response);

        }
        else{
            response = "{'result':'获取歌曲列表失败！'}";
        }
        resp.getWriter().append(response).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
