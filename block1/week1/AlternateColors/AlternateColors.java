public class AlternateColors {

  static String getColor(long r, long g, long b, long k) {

    String nextColor = "RED";
    String currentColor = "";
    while (k > 0) {
      if (r > 0 && "RED".equals(nextColor)) {
        currentColor = "RED";
        r--;
        nextColor = g > 0 ? "GREEN" : b > 0 ? "BLUE" : "RED";
      } else if (g > 0 && "GREEN".equals(nextColor)) {
        currentColor = "GREEN";
        g--;
        nextColor = b > 0 ? "BLUE" : r > 0 ? "RED" : "GREEN";
      } else if (b > 0 && "BLUE".equals(nextColor)) {
        currentColor = "BLUE";
        b--;
        nextColor = r > 0 ? "RED" : g > 0 ? "GREEN" : "BLUE";
      }
      k--;
    }

    return currentColor;
  }

//  public static void main(String[] args) {
//    long r = 653;
//    long g = 32;
//    long b = 1230;
//    long k = 556;
//
//
//    String res = getColor(r, g, b, k);
//    System.out.println(res);
//  }
}
