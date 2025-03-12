package org.venu.api.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

public class UniversittBest {

    public static String APIRUL ="https://jsonmock.hackerrank.com/api/universities";

    public static void main(String[] args){
        String jsonResponse = GetAPIService.getAPIResponse(APIRUL);
        JsonObject jsonObject= new Gson().fromJson(jsonResponse, JsonObject.class);
                    JsonArray jsonArray=jsonObject.getAsJsonArray("data");
             Map<String, TreeMap<String,String>> mapCistyWiseUniversityStudents=new TreeMap<>();
              for(JsonElement element:jsonArray) {
                String university= String.valueOf(element.getAsJsonObject().get("university"));
                String internatioalStudents = String.valueOf(element.getAsJsonObject().get("international_students"));
                internatioalStudents=internatioalStudents.replace(",","");
                  JsonObject jsonobjLocation= (JsonObject) element.getAsJsonObject().get("location");
                  String city = String.valueOf(jsonobjLocation.get("city"));
                  TreeMap<String,String> lisyUniversityWiseStudents =mapCistyWiseUniversityStudents.getOrDefault(city,new TreeMap<>());
                  lisyUniversityWiseStudents.put(university,internatioalStudents);
                  mapCistyWiseUniversityStudents.put(city,lisyUniversityWiseStudents);

              }
        System.out.println(mapCistyWiseUniversityStudents);
        mapCistyWiseUniversityStudents.forEach((key,value) -> {
            String city = key;
            TreeMap<String,String> universitydata=value;
            //Based on city get highest rank of university
            Map.Entry<String, String> entry = universitydata.lastEntry();
            String largestKey = entry.getKey();
            String largestValue = entry.getValue();
            // Printing the entry with the largest key
            System.out.println("The entry with the largest key is: [ Quantity: " + largestKey + ", Price: " + largestValue + "]");
        });

    }
}
