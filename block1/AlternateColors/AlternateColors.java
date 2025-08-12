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
      long m = Math.min(g, b);
      if (k <= 2L * m) return (k % 2 == 1) ? "GREEN" : "BLUE";
      return (g > b) ? "GREEN" : "BLUE";
    }
    if (g == 0) {
      long m = Math.min(r, b);
      if (k <= 2L * m) return (k % 2 == 1) ? "RED" : "BLUE";
      return (r > b) ? "RED" : "BLUE";
    }

    long m = Math.min(r, g);
    if (k <= 2L * m) return (k % 2 == 1) ? "RED" : "GREEN";
    return (r > g) ? "RED" : "GREEN";

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
