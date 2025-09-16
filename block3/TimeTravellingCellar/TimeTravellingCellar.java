public class TimeTravellingCellar {

  public static int determineProfit(int[] profit, int[] decay) {
    int[] maxProfit = {0, -1};
    int[] maxProfit2 = {0, -1};
    int[] minDecay = {0, Integer.MAX_VALUE};
    int[] minDecay2 = {0, Integer.MAX_VALUE};
    for (int i = 1; i < profit.length; i++) {
      if(i == 1) {
        if (profit[1] < profit[0]) {
          maxProfit[1] = profit[0];
          maxProfit2[0] = 1;
          maxProfit2[1] = profit[1];
        } else {
          maxProfit[0] = 1;
          maxProfit[1] = profit[1];
          maxProfit2[1] = profit[0];
        }
      }else if (profit[i] >= maxProfit[1]) {
        maxProfit2[0] = maxProfit[0];
        maxProfit2[1] = maxProfit[1];
        maxProfit[0] = i;
        maxProfit[1] = profit[i];
      }
    }
    for (int i = 0; i < decay.length; i++) {
      if(i == 1) {
        if (decay[1] > decay[0]) {
          minDecay[1] = decay[0];
          minDecay2[0] = 1;
          minDecay2[1] = decay[1];
        } else {
          minDecay[0] = 1;
          minDecay[1] = decay[1];
          minDecay2[1] = decay[0];
        }
      }else if (decay[i] <= minDecay[1]) {
        minDecay2[0] = minDecay[0];
        minDecay2[1] = minDecay[1];
        minDecay[0] = i;
        minDecay[1] = decay[i];
      }
    }

    return minDecay[0] != maxProfit[0] ? maxProfit[1] - minDecay[1] : Math.max(maxProfit2[1] - minDecay[1], maxProfit[1] - minDecay2[1]);

  }

  public static void main(String[] args) {
    int[] profit = {1000,500,250,125};
    int[] decay = {64,32,16,8};
    int res = determineProfit(profit, decay);
    System.out.println(res);

  }
}
