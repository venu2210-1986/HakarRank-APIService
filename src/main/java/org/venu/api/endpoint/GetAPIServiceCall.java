package org.venu.api.endpoint;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetAPIServiceCall {

    public static String getAPIServiceResponse(String endpointURL) throws Exception {
        try {
            URL url = new URL(endpointURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.addRequestProperty("content-type","application/json");
            int responsecode=httpURLConnection.getResponseCode();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line =null;
            StringBuffer stringBuffer = new StringBuffer();
            if((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            return stringBuffer.toString();

        }catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
