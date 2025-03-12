import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RepeatChar {

    public static void main(String[] args){
       // Scanner input = new
        String input ="a10b3";
        int count = input.length();
        char[] array =input.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        boolean isrepeated=false;

        for(int i =0 ;i<count; i++){
            char value = array[i];
            boolean flag = isNumeric(value);  //a 10
            if(flag){
                isrepeated=true;
                if(isrepeated){
                    builder.append(value);

                }
            }else{
                isrepeated=false;
                builder = new StringBuilder();

            }

        }

    }
}
