import java.util.*;

public class MinCoins {
  static final int INF = 1_000_000;

  // 返回最少硬币数；不可达返回 -1
  public static int minCoins(int[] coins, int target) {
    int[] dp = new int[target + 1];
//    int[] pick = new int[target + 1]; // 记录在 t 处选了哪枚硬币
    Arrays.fill(dp, INF);
//    Arrays.fill(pick, -1);
    dp[0] = 0;

    for (int coin : coins) {
      for (int t = coin; t <= target; t++) {
        if (dp[t - coin] + 1 < dp[t]) {
          dp[t] = dp[t - coin] + 1;
//          pick[t] = coin;
        }
      }
    }
    return dp[target] >= INF ? -1 : dp[target];
  }
  // 回溯一种最优组合
  public static Map<Integer, Integer> reconstruct(int[] coins, int target) {
    int[] dp = new int[target + 1];
    int[] pick = new int[target + 1];
    Arrays.fill(dp, INF);
    Arrays.fill(pick, -1);
    dp[0] = 0;
    for (int coin : coins) {
      for (int t = coin; t <= target; t++) {
        if (dp[t - coin] + 1 < dp[t]) {
          dp[t] = dp[t - coin] + 1;
          pick[t] = coin;
        }
      }
    }
    if (dp[target] >= INF) return Collections.emptyMap();

    Map<Integer, Integer> cnt = new LinkedHashMap<>();
    for (int c : coins) cnt.put(c, 0);
    int t = target;
    while (t > 0) {
      int c = pick[t];
      cnt.put(c, cnt.get(c) + 1);
      t -= c;
    }
    return cnt;
  }

  public static void main(String[] args) {
    int[] coins = {5, 3, 2};
    int target = 16;
    System.out.println(minCoins(coins, target)); // 5

    System.out.println(reconstruct(coins, target)); // {50=3, 30=2, 20=0}
  }
}
