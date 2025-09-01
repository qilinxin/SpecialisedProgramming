import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LetterBar {
    public static void main(String[] args) {
        System.out.println(
                maxLength("thisisansrmbeforetopcoderopenfinals")
        );
    }

    public static int maxLength(String letters){

//        int maxLen = -1;
        Map<Character, Integer> cnt = new HashMap<>();

        for (int i=0; i<letters.length(); i++){
            Character currentChar = letters.charAt(i);
            for(int j=i+1; j<=letters.length(); j++){
                String now = letters.substring(i, j);
//                System.out.println("i==="+i + ";j=== " + j + ";now ==== " + now);
                Set<Character> set = new HashSet<>();
                int okk = 1;

                for (char c : now.toCharArray()) {
                    if (!set.add(c)) {
                        okk = 0;
                    }
                }
                if(okk == 0){
                    break;
                }

                for(int k=0; k<now.length(); k++){
                    Character nowc = now.charAt(k);
                    cnt.putIfAbsent(nowc, 1);

                    if(currentChar.equals(nowc) && cnt.get(nowc) < now.length()){
                        cnt.put(nowc, now.length());
                    }
                }

            }
        }
        return cnt.values().stream()
                .max(Integer::compare)
                .orElse(0);
    }
}
