import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ChangingString {

  public static int distance(String A, String B, int K) {
//    int  distance = 0;
    List<Integer> differences = new ArrayList<>();
    for (int i = 0; i < A.length(); i++) {
      differences.add(abs(A.charAt(i) - B.charAt(i)));
    }
    differences.sort(Integer::compare);

    for (;K > 0; K--) {
      if (!differences.isEmpty() && differences.getLast() != 0) {
        differences.removeLast();
      } else {
        break;
      }
    }
    return differences.stream().mapToInt(Integer::intValue).sum() + K;
  }

//  public static void main(String[] args) {
////    int  distance = distance("ab", "ba", 2);
////    int  distance = distance("aa", "aa", 2);
////    int  distance = distance("aaa", "baz", 1);
//    int  distance = distance("fdfdfdfdfdsfabasd", "jhlakfjdklsakdjfk", 8);
////    int  distance = distance("aa", "bb", 2);
////    int  distance = distance("ab", "ba", 2);
//    System.out.println(distance);
//  }
}


