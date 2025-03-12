package org.venu.api.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class CapitalCityAPI {

    private static String APIURL = "https://jsonmock.hackerrank.com/api/countries";

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String country= input.next();
       // input.close();
        String capital = getCapital(country);
        System.out.println(capital);
    }
    public static String getCapital(String countryName){
        String endpointURL = APIURL+"?name="+countryName;
        String apiResponse = GetAPIService.getAPIResponse(endpointURL);
        System.out.println(apiResponse);
        JsonObject jsonresponse = new Gson().fromJson(apiResponse, JsonObject.class);
        JsonArray jsonResponseArray= jsonresponse.getAsJsonArray("data");
        StringBuffer capital=new StringBuffer();
        for(JsonElement element: jsonResponseArray){
            capital.append(element.getAsJsonObject().get("capital"));
            capital.append(",");
        }
        return capital.toString();
    }
}
