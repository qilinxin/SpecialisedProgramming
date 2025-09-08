import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class MatchNumbersEasy {
  public static String maxNumber(int[] matches, int n) {
    final int NEG = -1000;
    int[] f = new int[n + 1];
    for (int i = 1; i <= n; i++) f[i] = NEG;
    f[0] = 0;
    for (int x = 1; x <= n; x++) {
      int best = NEG;
      for (int d = 0; d < matches.length; d++) {
        int c = matches[d];
        if (x >= c && f[x - c] != NEG) {
          best = Math.max(best, f[x - c] + 1);
        }
      }
      f[x] = best;
    }
    if (f[n] <= 0) return "";
    int need = f[n];
    int rem = n;
    StringBuilder sb = new StringBuilder();
    for (int pos = 0; pos < need; pos++) {
      for (int d = matches.length - 1; d >= 0; d--) {
        if (pos == 0 && need > 1 && d == 0) continue;
        int c = matches[d];
        if (rem >= c) {
          int left = rem - c;
          // the value of f[left] can still reach  need - pos - 1？
          if (f[left] == need - pos - 1) {
            sb.append((char)('0' + d));
            rem = left;
            break;
          }
        }
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    int[] aa = {6,3,4,5,2,3};
    int  matches = 15;;
    String res = maxNumber(aa, matches);
    System.out.println(res);
  }
}
