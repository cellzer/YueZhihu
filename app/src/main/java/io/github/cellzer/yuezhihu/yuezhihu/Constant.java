package io.github.cellzer.yuezhihu.yuezhihu;

/**
 * Created by walmand_ on 2016/1/29 0029.
 */
public class Constant {
    public static final String BASEURL = "http://news-at.zhihu.com/api/4/";
    public static final String KANZHIHUBASEURL = "http://api.kanzhihu.com/";

    public static final String GETPOSTANSWERS = KANZHIHUBASEURL+"getpostanswers/";
    public static final String TOPUSER = KANZHIHUBASEURL+"topuser/";


    //Splash页面图片
    public static final String START = BASEURL+"start-image/1080*1776";

    public static final String THEMES =BASEURL+ "themes";
    public static final String LATESTNEWS =BASEURL+ "news/latest";
    public static final String BEFORE = BASEURL+"news/before/";
    public static final String THEMENEWS = BASEURL+"theme/";
    public static final String CONTENT = BASEURL+"news/";
    public static final int TOPIC = 131;
    public static final String START_LOCATION = "start_location";
    public static final String CACHE = "cache";
    public static final int LATEST_COLUMN = Integer.MAX_VALUE;
    public static final int BASE_COLUMN = 100000000;
    public static final String[] CHOSEN_TITLE = {"昨日最新","近日热门","历史精华"};
    public static final String[] CHOSEN_URL = {"yesterday","recent","archive"};
    public static final String[] USER_TITLE = {"赞同数","关注数"};
    public static final String[] USER_URL = {"agree","follower"};




    public static final int TYPE_AGREE = 11;
    public static final int TYPE_FOLLOWER = 12;

}
