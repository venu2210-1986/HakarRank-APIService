package org.venu.api.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.*;

public class MaxTransferAPI {

    public static String APIURL = "https://jsonmock.hackerrank.com/api/transactions";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println(name);

        String city = sc.nextLine();
        sc.close();
        String jsonResponse = GetAPIServiceCall.getAPIServiceResponse(APIURL);
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        System.out.println(jsonArray);
        System.out.println(name);
        TreeMap<String, BigDecimal> credittreeMap = new TreeMap<>();
        TreeMap<String, BigDecimal> debittreeMap = new TreeMap<>();
        if (name != null) {
            for (JsonElement element : jsonArray) {
                String userName = String.valueOf(element.getAsJsonObject().get("userName"));
                String filterUserName = userName.replace("\"", "");

                if (name.equalsIgnoreCase(filterUserName)) {
                    JsonObject locationJsonObject = (JsonObject) element.getAsJsonObject().get("location");

                    String cityName = String.valueOf(locationJsonObject.get("city"));
                    String filterCityName = cityName.replace("\"", "");

                    String id = String.valueOf(element.getAsJsonObject().get("id")).replace("\"", "");
                    ;

                     if(city.equalsIgnoreCase(filterCityName)){
                    String txnType = String.valueOf(element.getAsJsonObject().get("txnType"));
                    String filtertxnType = txnType.replace("\"", "");
                    String amount = String.valueOf(element.getAsJsonObject().get("amount"));
                    String filterAmont = amount.replace("\"", "");

                    if (filtertxnType.equalsIgnoreCase("debit")) {
                        String debitamount = filterAmont.replace("$", "").replace(",", "");
                        debittreeMap.put(filtertxnType + "#" + filterAmont, new BigDecimal(debitamount));
                    }
                        if (filtertxnType.equalsIgnoreCase("credit")) {
                            String creditAmount = filterAmont.replace("$", "").replace(",", "");
                            credittreeMap.put(filtertxnType + "#" + filterAmont, new BigDecimal(creditAmount));
                        }
                    }
                }}
                Optional<Map.Entry<String, BigDecimal>> debitMaxEntry = debittreeMap.entrySet()
                        .stream()
                        .max((Map.Entry<String, BigDecimal> e1, Map.Entry<String, BigDecimal> e2) -> e1.getValue()
                                .compareTo(e2.getValue())
                        );
            Optional<Map.Entry<String, BigDecimal>> crditMaxEntry = credittreeMap.entrySet()
                    .stream()
                    .max((Map.Entry<String, BigDecimal> e1, Map.Entry<String, BigDecimal> e2) -> e1.getValue()
                            .compareTo(e2.getValue())
                    );

                /*System.out.print(debitMaxEntry.get().getValue());
                System.out.print(crditMaxEntry.get().getValue());*/
                List<String> filterData = new ArrayList<>();
               if(debitMaxEntry.isPresent()){
                   filterData.add(debitMaxEntry.get().getKey());

               } if(crditMaxEntry.isPresent()) {
                    filterData.add(crditMaxEntry.get().getKey());
                }
                filterData.stream().forEach(iter ->
                {
                    if (iter.contains("debit")) {
                        String[] debitData = iter.split("#");
                        System.out.print(debitData[1]);
                    } else {
                        String[] credit = iter.split("#");
                        System.out.print(credit[1]);

                    }
                });
            }

                System.out.println(credittreeMap);
                System.out.println(debittreeMap);


            }


        }


