public class MatchNumbersEasy {
  public static String maxNumber(int[] cost, int n) {
    int m = cost.length;
    if (m == 1) return "0";

    int minAll = Integer.MAX_VALUE, digMinAll = -1;
    for (int d = 0; d < m; d++) if (cost[d] < minAll) { minAll = cost[d]; digMinAll = d; }

    int minFirst = Integer.MAX_VALUE, digMinFirst = -1;
    for (int d = 1; d < m; d++) if (cost[d] < minFirst) { minFirst = cost[d]; digMinFirst = d; }

    if (n < minFirst) return cost[0] <= n ? "0" : ""; // 至少能放单个0

    int L = 1 + (n - minFirst) / minAll;

    int[] digits = new int[L];
    digits[0] = digMinFirst;
    for (int i = 1; i < L; i++) digits[i] = digMinAll;
    int used = minFirst + (L - 1) * minAll;

    for (int i = 0; i < L; i++) {
      for (int d = m - 1; d >= 0; d--) {
        if (i == 0 && L > 1 && d == 0) continue;
        int extra = cost[d] - cost[digits[i]];
        if (extra >= 0 && used + extra <= n) {
          digits[i] = d; used += extra; break;
        }
      }
    }

    StringBuilder sb = new StringBuilder(L);
    for (int d : digits) sb.append((char)('0' + d));
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(maxNumber(new int[]{5,23,24}, 30));              // 20
    System.out.println(maxNumber(new int[]{4,23,24}, 30));              // 20
    System.out.println(maxNumber(new int[]{6,2,5,5,4,5,6,3,7,6}, 5));   // 71
    System.out.println(maxNumber(new int[]{2}, 2));                     // 0
  }
}
