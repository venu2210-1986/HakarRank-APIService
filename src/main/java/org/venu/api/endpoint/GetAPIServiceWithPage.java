package org.venu.api.endpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetAPIServiceWithPage {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nation = br.readLine();
        int minValuation = Integer.parseInt(br.readLine().trim());
        int minTitlesWon = Integer.parseInt(br.readLine().trim());
        List<String> result = Result.eliteClubs(nation,minValuation,minTitlesWon);
        System.out.print(result);
        br.close();



    }

}

class Result {

    public static List<String> eliteClubs(String nation , int minValuation, int minTitlesWon) throws Exception {
        //Base page Iterate
        String endpointURL ="https://jsonmock.hackerrank.com/api/football_teams?";
        int page =1;
        List<JSONObject> clubs = new ArrayList<>();
        List<String> clubName = new ArrayList<>();
        while(true){
            String uri =endpointURL+"nation="+nation+"&page="+page;
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
                JSONObject club= array.getJSONObject(i);
                int valuation = club.getInt("estimated_value_numeric");
                int titlesWon = club.getInt("number_of_league_titles_won");
                if(valuation>=minValuation && titlesWon>=minTitlesWon){
                    clubs.add(club);
                }
            }
            //calculate page
            int totalPage = object.getInt("total_pages");
              if(page>=totalPage){
                  break;
              }
            page++;
        }

        for(JSONObject club:clubs){
            clubName.add(club.getString("name"));
        }

        //Here actual test cases need to add


        return clubName;
    }
}
