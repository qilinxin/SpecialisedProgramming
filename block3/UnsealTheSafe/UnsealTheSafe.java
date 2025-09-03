import java.math.BigInteger;
import java.util.Arrays;

public class UnsealTheSafe {
  static final int[][] ADJACENT = {
      {7},       // 0
      {2,4},     // 1
      {1,3,5},   // 2
      {2,6},     // 3
      {1,5,7},   // 4
      {2,4,6,8}, // 5
      {3,5,9},   // 6
      {4,8,0},   // 7
      {5,7,9},   // 8
      {6,8}      // 9
  };

  public static long countPasswords(int N) {
    long res = 0;
    long[] finalRes = new long[10];
    Arrays.fill(finalRes, 1L);
    for (int length = 2; length <= N; length++) {
      long[] currentRes = new long[10];
      for (int num = 0; num <= 9; num++) {
        int[] currentNumAdj = ADJACENT[num];
        for (int i : currentNumAdj) {
          currentRes[num] += finalRes[i];
        }
      }
      finalRes = currentRes;
    }
    for (long singleRes : finalRes) {
      res += singleRes;
    }
    return res;
  }

  public static void main(String[] args) {
    long res = countPasswords(3);
    System.out.println(res);
  }
}
