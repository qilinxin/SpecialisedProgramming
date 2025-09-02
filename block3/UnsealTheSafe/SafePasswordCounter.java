import java.util.Arrays;

public class SafePasswordCounter {

  /**
   * 键盘相邻关系（仅上下左右共边，不含斜对角）。
   * 下标是“数字”，值是它能相邻的数字列表。
   * 0 只与 7 相邻。
   */
  private static final int[][] ADJACENT_DIGITS = {
      {7},          // 0
      {2, 4},       // 1
      {1, 3, 5},    // 2
      {2, 6},       // 3
      {1, 5, 7},    // 4
      {2, 4, 6, 8}, // 5
      {3, 5, 9},    // 6
      {4, 8, 0},    // 7
      {5, 7, 9},    // 8
      {6, 8}        // 9
  };

  /**
   * 统计满足“相邻数字必须在键盘上相邻”约束的密码数量。
   * 密码允许前导 0（题目未禁止）。
   *
   * @param passwordLength 密码长度 N（必须 >= 1）
   * @return 合法密码总数（使用 long；N 很大时可能溢出，见下方说明）
   */
  public static long countPasswords(int passwordLength) {
    if (passwordLength <= 0) return 0L;

    // dp[d] 表示“当前长度下，以数字 d 结尾”的方案数
    long[] currentCounts = new long[10];
    Arrays.fill(currentCounts, 1L); // 长度为 1 时：每个数字都各 1 种
    // 如果要“禁止前导 0”，把上一行改为：
    // Arrays.fill(currentCounts, 1L); currentCounts[0] = 0L;

    // 从长度 2 推到 N：next[d] = sum_{u in Adj(d)} current[u]
    for (int len = 2; len <= passwordLength; len++) {
      long[] nextCounts = new long[10];
      for (int lastDigit = 0; lastDigit <= 9; lastDigit++) {
        long ways = 0L;
        for (int prevDigit : ADJACENT_DIGITS[lastDigit]) {
          ways += currentCounts[prevDigit];
        }
        nextCounts[lastDigit] = ways;
      }
      currentCounts = nextCounts;
    }

    // 将“以 0..9 结尾”的方案数汇总得到长度为 N 的总数
    long total = 0L;
    for (long ways : currentCounts) total += ways;
    return total;
  }

  /**
   * 如需避免 long 溢出，可使用取模版本（同样的转移，但每步对 mod 取余）。
   *
   * @param passwordLength 密码长度 N
   * @param mod            模数（如 1_000_000_007）；必须 > 0
   * @return 合法密码总数的模 mod 值
   */
  public static long countPasswordsMod(int passwordLength, long mod) {
    if (passwordLength <= 0) return 0L;
    long[] currentCounts = new long[10];
    Arrays.fill(currentCounts, 1L % mod); // 允许前导 0
    for (int len = 2; len <= passwordLength; len++) {
      long[] nextCounts = new long[10];
      for (int lastDigit = 0; lastDigit <= 9; lastDigit++) {
        long ways = 0L;
        for (int prevDigit : ADJACENT_DIGITS[lastDigit]) {
          ways += currentCounts[prevDigit];
          if (ways >= Long.MAX_VALUE / 4) ways %= mod; // 粗暴防溢出
        }
        nextCounts[lastDigit] = ways % mod;
      }
      currentCounts = nextCounts;
    }
    long total = 0L;
    for (long ways : currentCounts) {
      total += ways;
      if (total >= Long.MAX_VALUE / 4) total %= mod;
    }
    return total % mod;
  }

  // 简单自测
  public static void main(String[] args) {
//    System.out.println(countPasswords(1)); // 10
//    System.out.println(countPasswords(2)); // 26
    System.out.println(countPasswords(3)); // 74
//    System.out.println(countPasswords(4)); // 210
//    System.out.println(countPasswords(5)); // 600
  }
}
