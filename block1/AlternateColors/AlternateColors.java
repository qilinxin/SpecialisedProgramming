public class AlternateColors {

  static String getColor(long r, long g, long b, long k) {

    String nextColor = "RED";
    String currentColor = "";
    for (;k > 0; k--) {
      if (r > 0 && "RED".equals(nextColor)) {
        currentColor = "RED";
        r--;
        nextColor = g > 0 ? "GREEN" : b > 0 ? "BLUE" : "RED";
        if (g ==0 && b ==0) {
          break;
        }
      } else if (g > 0 && "GREEN".equals(nextColor)) {
        currentColor = "GREEN";
        g--;
        nextColor = b > 0 ? "BLUE" : r > 0 ? "RED" : "GREEN";
        if (b == 0 && r ==0) {
          break;
        }
      } else if (b > 0 && "BLUE".equals(nextColor)) {
        currentColor = "BLUE";
        b--;
        nextColor = r > 0 ? "RED" : g > 0 ? "GREEN" : "BLUE";
        if (g ==0 && r ==0) {
          break;
        }
      }
    }

    return currentColor;
  }

//  public static void main(String[] args) {
//    long r = 1000000000000l;
//    long g = 1;
//    long b = 1;
//    long k = 10000000000002l;
//
//
//    String res = getColor(r, g, b, k);
//    System.out.println(res);
//  }
}
