package org.venu.api.endpoint;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import java.net.*;

import org.json.JSONObject;
import org.json.JSONArray;

class Result1 {

    /*
     * Complete the 'topArticles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER limit as parameter.
     * base url for copy/paste:
     * https://jsonmock.hackerrank.com/api/articles?page=<pageNumber>
     */

    public static List<String> topArticles(int limit)  {
        //Base page Iterate
        int totalpges =1;
        int page =1;
        List<Article> atrList=new ArrayList<>();
        List<String> articleList = new ArrayList<>();
        try{
            while(page<=totalpges){
                String endpointURL ="https://jsonmock.hackerrank.com/api/articles?";

                String uri =endpointURL+"page="+page;
                URL url = new URL(uri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                if((line=reader.readLine())!=null){
                    sb.append(line);
                }
                JSONObject object = new JSONObject(sb.toString());
                JSONArray array = object.getJSONArray("data");
                for(int i=0;i<array.length();i++){
                    JSONObject article= array.getJSONObject(i);
                    String title = article.optString("title","");
                    int comments = article.optInt("num_comments",0);
                    String sortyTitle = article.optString("story_title","");
                    if(!title.isEmpty()){
                        atrList.add(new Article(title, comments));
                        System.out.println("title::"+title+"---->comments:::"+comments);
                    }else if(!sortyTitle.isEmpty()){
                        atrList.add(new Article(sortyTitle, comments));
                        System.out.println("title::"+title+"---->comments:::"+comments);

                    }
                }
                totalpges=object.getInt("total_pages");
                page++;


            }
        }catch(Exception e){
            e.printStackTrace();
        }
       //Based on comment it sorted values in- first as asending and reversed the order - it will display the changes on decresing order
     //   atrList=atrList.stream().sorted(Comparator.comparing(Article::getCommentCount).reversed().thenComparing(Article::getTitle).reversed()).collect(Collectors.toList());
       //first check the below changes


        System.out.println("=========================");
        for(Article article:atrList){
            System.out.println("title::"+article.title+"---->comments:::"+article.commentCount);

        }
        //Sort article
        Collections.sort(atrList,new Comparator<Article>() {
            @Override
            public int compare(Article a1, Article a2) {
                if (a2.commentCount!= a1.commentCount) {
                    return a2.commentCount-a1.commentCount; //Decreasing order

                } else {
                    return a2.title.compareTo(a1.title);  //Decresing order

                }
            }
        });



        //Here actual test cases need to add
        for(int i=0; i<Math.min(limit, atrList.size());i++){
            articleList.add(atrList.get(i).title);
            System.out.println("final title::"+atrList.get(i).title+"---->comments:::"+atrList.get(i).commentCount);


        }
        return articleList;
    }



}

class Article{
    public String getTitle() {
        return title;
    }

    public int getCommentCount() {
        return commentCount;
    }

    String title ;
    int commentCount;
    Article(String title,int commentCount){
        this.commentCount=commentCount;
        this.title=title;
    }


}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int limit = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> result = Result1.topArticles(limit);
        System.out.print(result);

     /*   bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );*/

        bufferedReader.close();
       // bufferedWriter.close();
    }
}
