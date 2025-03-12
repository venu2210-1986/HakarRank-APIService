import java.util.HashMap;
import java.util.Map;

public class Nonrepeatchar {

    public static void main(String[] args){

         Map<Character ,Integer > repeatMap = new HashMap<>();
        String value = "ababefba";
        for(char c : value.toCharArray()){
            repeatMap.put(c,repeatMap.getOrDefault(c,0)+1);
        }
        for(Map){
           repeatMap.get()
        }
    }
}
