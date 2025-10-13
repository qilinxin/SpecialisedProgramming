import java.util.Arrays;

public class JumpyNum {

  // Public API: count jumpy numbers in [low, high]
  public int howMany(int low, int high) {
    return (int) (countUpTo(high) - countUpTo((long)low - 1));
  }

  /** Count jumpy numbers in [1, n] via digit DP. */
  private long countUpTo(long n) {
    if (n <= 0) return 0;
    int[] digits = toDigits(n); // most-significant to least-significant
    int len = digits.length;

    // dp[pos][prev][started] for non-tight states only; -1 means uncalculated
    // prev: 0..9 are real digits; 10 means "no previous digit yet"
    long[][][] dp = new long[len + 1][11][2];
    for (int i = 0; i <= len; i++) {
      for (int j = 0; j < 11; j++) {
        Arrays.fill(dp[i][j], -1L);
      }
    }

    return dfs(0, 10, true, false, digits, dp);
  }

  /**
   * Digit-DP DFS.
   * @param pos     current position [0..len)
   * @param prev    previous digit (0..9) or 10 meaning "none yet"
   * @param tight   if true, current digit cannot exceed digits[pos]
   * @param started if we have placed a non-leading-zero digit
   */
  private long dfs(int pos, int prev, boolean tight, boolean started,
                   int[] digits, long[][][] dp) {
    int len = digits.length;
    if (pos == len) {
      // Valid number only if started==true (i.e., >= 1)
      return started ? 1L : 0L;
    }

    int startedIdx = started ? 1 : 0;
    if (!tight && dp[pos][prev][startedIdx] != -1L) {
      return dp[pos][prev][startedIdx];
    }

    int up = tight ? digits[pos] : 9;
    long res = 0;

    for (int d = 0; d <= up; d++) {
      boolean nextTight = tight && (d == up);
      boolean nextStarted = started || (d != 0);

      if (!nextStarted) {
        // Still in leading zeros; no adjacency constraint, keep prev as "none"
        res += dfs(pos + 1, 10, nextTight, false, digits, dp);
      } else {
        // We are placing a real digit. If prev exists, enforce |d - prev| >= 2
        if (prev == 10 || Math.abs(d - prev) >= 2) {
          res += dfs(pos + 1, d, nextTight, true, digits, dp);
        }
      }
    }

    if (!tight) dp[pos][prev][startedIdx] = res;
    return res;
  }

  /** Convert n to digit array (MSD..LSD). */
  private int[] toDigits(long n) {
    char[] s = Long.toString(n).toCharArray();
    int[] a = new int[s.length];
    for (int i = 0; i < s.length; i++) a[i] = s[i] - '0';
    return a;
  }

  // --- quick sanity checks ---
  public static void main(String[] args) {
    JumpyNum j = new JumpyNum();
    // [1, 10] -> 1..9 are jumpy, 10 is not -> 9
    System.out.println(j.howMany(1, 10)); // expected 9

    // Single number range
    System.out.println(j.howMany(5, 5)); // expected 1 (single-digit is jumpy)

    // Small custom checks
    System.out.println(j.howMany(10, 20)); // quick feel (should be small)
    System.out.println(j.howMany(13131313, 13131313)); // expected 1 (jumpy)
    System.out.println(j.howMany(111111, 111111)); // expected 0 (not jumpy)
  }
}
