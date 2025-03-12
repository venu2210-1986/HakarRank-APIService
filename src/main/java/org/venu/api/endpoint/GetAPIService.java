package org.venu.api.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAPIService {

    /**
     * Basic Http connection without SSL enable
     */
    public static String getAPIResponse(String endpointUrl){
        try {
            URL url = new URL(endpointUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.addRequestProperty("content-type","application/json");
            int statusCode= httpURLConnection.getResponseCode();
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line ;
            StringBuffer stringbuffer = new StringBuffer();
            while((line=bufferRead.readLine())!=null){
                stringbuffer.append(line);
            }
            if(bufferRead!=null){
                    bufferRead.close();
            }
            return stringbuffer.toString();

        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
}


