public class AlternateColors {

  static String getColor(long r, long g, long b, long k) {
    long total = r + g + b;
    if (k < 1 || k > total) throw new IllegalArgumentException("k out of range");

    long wholeLoopCount = Math.min(r, Math.min(g, b));
    if (k <= 3L * wholeLoopCount) {
      long module = k % 3;
      return module == 1 ? "RED" : (module == 2 ? "GREEN" : "BLUE");
    }
    k = k - 3L * wholeLoopCount;
    r -= wholeLoopCount;
    g -= wholeLoopCount;
    b -= wholeLoopCount;
    if (r == 0 && g == 0) {
      return "BLUE";
    }
    if (g == 0 && b == 0) {
      return "RED";
    }
    if (r == 0 && b == 0) {
      return "GREEN";
    }
    if (r == 0) {
      return k % 2 == 0 ? "BLUE" : "GREEN";
    }
    if (g == 0) {
      return k % 2 == 0 ? "BLUE" : "RED";
    }
    return k % 2 == 0 ? "GREEN" : "RED";
  }

//  public static void main(String[] args) {
//    long r = 653;
//    long g = 32;
//    long b = 1230;
//    long k = 556;
//
//    String res = getColor(r, g, b, k);
//    System.out.println(res);
//  }
}
