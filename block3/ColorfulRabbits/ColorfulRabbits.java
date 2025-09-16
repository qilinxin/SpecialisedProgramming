import java.util.HashMap;
import java.util.Map;

public class ColorfulRabbits {
  public static int getMinimum(int[] replies) {
    Map<String, Integer> map = new HashMap<>();
    for (Integer value : replies) {
      String valString = Integer.toString(value);
      if (map.containsKey(valString)) {
        map.put(valString, map.get(valString) + 1);
      } else {
        map.put(valString, 1);
      }
    }
    int count = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      int value = entry.getValue();
      int keyValue = Integer.parseInt(key);
      if (keyValue == 0) {
        continue;
      }
      int realKeyValue = keyValue + 1;
      if (value  % realKeyValue == 0) {
        count += realKeyValue * value / realKeyValue;
      } else {
        count += realKeyValue * ( value / realKeyValue + 1);
      }

    }

    return count;
  }

  public static void main(String[] args) {
    int[] a = {2, 2, 44, 2, 2, 2, 444, 2, 2};
    int res = getMinimum(a);
    System.out.println(res);
  }
}
