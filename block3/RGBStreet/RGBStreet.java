import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RGBStreet {

  public static int estimateCost(String[] houses) {
    List<List<String>> finalHousesList = new ArrayList<>();
    for (String s : houses) {
      finalHousesList.add(Arrays.asList(s.split(" ")));
    }
    int[] dpR = new int[1];
    int[] dpG = new int[1];
    int[] dpB = new int[1];
    dpR[0] = Integer.parseInt(finalHousesList.get(0).get(0));
    dpG[0] = Integer.parseInt(finalHousesList.get(0).get(1));
    dpB[0] = Integer.parseInt(finalHousesList.get(0).get(2));
    for (int i = 1; i < finalHousesList.size(); i++) {
      int[] curDpR = new int[1];
      int[] curDpG = new int[1];
      int[] curDpB = new int[1];
      curDpR[0] = Integer.parseInt(finalHousesList.get(i).get(0)) + Math.min(dpG[0], dpB[0]);
      curDpG[0] = Integer.parseInt(finalHousesList.get(i).get(1)) + Math.min(dpB[0], dpR[0]);
      curDpB[0] = Integer.parseInt(finalHousesList.get(i).get(2)) + Math.min(dpR[0], dpG[0]);
      dpR = curDpR;
      dpG = curDpG;
      dpB = curDpB;
    }

    return Math.min(dpR[0], Math.min(dpG[0], dpB[0]));
  }

  public static void main(String[] args) {
    String[] houses = {"30 19 5", "64 77 64", "15 19 97", "4 71 57", "90 86 84", "93 32 91"}
        ;
     int res = estimateCost(houses);
    System.out.println(res);
  }
}
