import java.util.Arrays;

public class MonstersValley2 {

  public static int minimumPrice(int[] monsterDreads, int[] monsterBribeCosts) {
    final int maximumTotalCostBudget = Arrays.stream(monsterBribeCosts).sum();

    // dpByExactTotalCost[k] = maximum total dread achievable after processing the current prefix
    //                         with total cost exactly k; -1 means "unreachable".
    final long UNREACHABLE = -1L;
    long[] dpByExactTotalCost = new long[maximumTotalCostBudget + 1];
    Arrays.fill(dpByExactTotalCost, UNREACHABLE);
    dpByExactTotalCost[0] = 0L;  // start with zero cost and zero total dread

    for (int monsterIndex = 0; monsterIndex < monsterDreads.length; monsterIndex++) {
      final int currentMonsterDread = monsterDreads[monsterIndex];
      final int currentMonsterBribeCost = monsterBribeCosts[monsterIndex];

      long[] updatedDpByExactTotalCost = new long[maximumTotalCostBudget + 1];
      Arrays.fill(updatedDpByExactTotalCost, UNREACHABLE);

      for (int exactTotalCost = 0; exactTotalCost <= maximumTotalCostBudget; exactTotalCost++) {
        long currentMaximumTotalDread = dpByExactTotalCost[exactTotalCost];
        if (currentMaximumTotalDread == UNREACHABLE) {
          continue;
        }

        // Option A: Walk past (skip) this monster if we already dominate it.
        if (currentMaximumTotalDread >= currentMonsterDread) {
          updatedDpByExactTotalCost[exactTotalCost] =
              Math.max(updatedDpByExactTotalCost[exactTotalCost], currentMaximumTotalDread);
        }

        // Option B: Bribe this monster (always allowed).
        int newExactTotalCost = exactTotalCost + currentMonsterBribeCost;
        if (newExactTotalCost <= maximumTotalCostBudget) {
          long newMaximumTotalDread = currentMaximumTotalDread + currentMonsterDread;
          updatedDpByExactTotalCost[newExactTotalCost] =
              Math.max(updatedDpByExactTotalCost[newExactTotalCost], newMaximumTotalDread);
        }
      }

      dpByExactTotalCost = updatedDpByExactTotalCost;
    }

    // The smallest cost k that remains reachable is the optimal total gold required.
    for (int exactTotalCost = 0; exactTotalCost <= maximumTotalCostBudget; exactTotalCost++) {
      if (dpByExactTotalCost[exactTotalCost] != UNREACHABLE) return exactTotalCost;
    }
    return -1; // should not happen for valid inputs
  }

  public static void main(String[] args) {
    int[] dread = {1, 2, 4, 1000000000};
    int[] price =  {1, 1, 1, 2};
    int res = minimumPrice(dread, price);
    System.out.println("res===="+res);
  }
}
