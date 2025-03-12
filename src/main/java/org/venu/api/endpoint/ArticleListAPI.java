package org.venu.api.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAPI {
    public static final String API_URL="https://jsonmock.hackerrank.com/api/";

    public static void getArticles(String author,int page){

    }
    public static void main(String[] args){
        getArticleUser("epaga");
    }

    public static void getArticleUser(String authorName){
        String articleUserEndpoint = API_URL+"article_users?username="+authorName;
        String apiResponse= GetAPIService.getAPIResponse(articleUserEndpoint);
        Gson gson = new Gson();
        JsonObject responseJsonObject= gson.fromJson(apiResponse, JsonObject.class);
        JsonArray responseJsonArray = responseJsonObject.getAsJsonArray("data");
        System.out.println(responseJsonArray);
        List<String> authourAbout= new ArrayList<>();
        for(JsonElement jsonData : responseJsonArray){
           String about= String.valueOf(jsonData.getAsJsonObject().get("about"));
           authourAbout.add(about);
        }
        System.out.println(authourAbout);
    }
}
