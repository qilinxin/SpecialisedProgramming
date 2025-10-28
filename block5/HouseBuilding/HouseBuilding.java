import java.util.Arrays;
import java.util.List;

public class HouseBuilding {

  public static int getMinimum(String[] area) {
    List<String> areaList = Arrays.asList(area);
    int[][] areaHeights = new int[areaList.size()][areaList.get(0).length()];

    for (int i = 0; i < areaList.size(); i++) {
      for (int j = 0; j < areaList.get(0).length(); j++) {
        areaHeights[i][j] = Integer.valueOf(areaList.get(i).substring(j, j+1));
      }
    }
    int min = Integer.MAX_VALUE;
    for (int height = 0; height < 10; height++) {
      int sum = 0;
      for (int row = 0; row < areaList.size(); row++) {
        for (int col = 0; col < areaList.get(0).length(); col++) {
          System.out.println("currentHeight === "+areaHeights[row][col]);
          int differ1 = Math.abs(areaHeights[row][col] - height);
          int differ2 = Math.abs(areaHeights[row][col] - height - 1);
          sum += Math.min(differ1, differ2);
        }
      }
      System.out.println("height === "+height+";sum====="+sum);
      min = Math.min(min, sum);
    }

    return min;
  }

  public static void main(String[] args) {
    String[] area = {"5781252",
        "2471255",
        "0000291",
        "1212489"}
        ;
    int res = getMinimum(area);
    System.out.println("res === "+res);
  }
}
