import java.util.Arrays;

public class CatGame {

  // Method signature as required
  public static int getNumber(int[] coordinates, int X) {

    // sort
    Arrays.sort(coordinates);

    int n = coordinates.length;
    long best = Long.MAX_VALUE;

    for (int k = 0; k <= n; k++) {
      long mn = Long.MAX_VALUE;
      long mx = Long.MIN_VALUE;

      for (int i = 0; i < n; i++) {
        long pos = (i < k) ? (long)coordinates[i] + X : (long)coordinates[i] - X;
        if (pos < mn) mn = pos;
        if (pos > mx) mx = pos;
      }
      long span = mx - mn;
      if (span < best) best = span;
    }
    return (int) best;
  }

  public static void main(String[] args) {
    int[] coordinates = {-3, 0, 1};
    int X = 3;
    int res = getNumber(coordinates, X);
    System.out.println("res ====== "+res);
  }
}
