public class TimeTravellingCellar {

  public static int determineProfit(int[] profit, int[] decay) {
    int[] maxProfit = {0, -1};
    int[] maxProfit2 = {0, -1};
    int[] minDecay = {0, Integer.MAX_VALUE};
    int[] minDecay2 = {0, Integer.MAX_VALUE};
    for (int i = 0; i < profit.length; i++) {
      if (profit[i] > maxProfit[1]) {
        maxProfit2[0] = maxProfit[0];
        maxProfit2[1] = maxProfit[1];
        maxProfit[0] = i;
        maxProfit[1] = profit[i];
      } else if (profit[i] > maxProfit2[1]) {
        maxProfit2[0] = i;
        maxProfit2[1] = profit[i];
      }
    }
    for (int i = 0; i < decay.length; i++) {
     if (decay[i] < minDecay[1]) {
        minDecay2[0] = minDecay[0];
        minDecay2[1] = minDecay[1];
        minDecay[0] = i;
        minDecay[1] = decay[i];
      } else if (decay[i] < minDecay2[1]) {
       minDecay2[0] = i;
       minDecay2[1] = decay[i];
     }
    }

    return minDecay[0] != maxProfit[0] ? maxProfit[1] - minDecay[1] : Math.max(maxProfit2[1] - minDecay[1], maxProfit[1] - minDecay2[1]);

  }

  public static void main(String[] args) {
    int[] profit = {1030, 279, 9400, 9270, 1333, 1401, 6918, 5986};
    int[] decay = {7104, 6890, 526, 1252, 3651, 6313, 8445, 7023};
    int res = determineProfit(profit, decay);
    System.out.println(res);

  }
}
