package com.ruiwenliu.kotlin.moudle

object  ApiConfig{
    var HTTP_BANNER="banner/json"//banner 图
    var HTTP_MENU_LIST="project/tree/json"//项目菜单
    var HTTP_PROJECT_LIST="project/list/1/json?cid=294";//项目列表
    var HTTP_LOGIN="user/login";

    /////===========开源中国api===============
    ///https://www.oschina.net/openapi/oauth/authorize?client_id=8altuT6uUlUrJfK7dG7g&response_type=code&redirect_uri=http://www.jianshu.com/users/auth/qq_connect/callback
    var API_CATEGORIES="xiandu/categories "//获取闲读的主分类
    var API_CATEGORY="xiandu/category/"//获取闲读的子分类



    var OSCHINA_NEWS_LIST="/action/openapi/news_list";
    var OSCHINA_TOKEN="/action/openapi/token";
    var OSCHINA_AUTHORIZE="/action/oauth2/authorize";
}