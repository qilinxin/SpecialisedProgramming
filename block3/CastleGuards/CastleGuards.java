public class CastleGuards {
  public static int missing(String[] castle) {
    int columnMissing = 0;
    int columnNumber = castle[0].length();
    int rowMissing = 0;
    for (String currentRow : castle) {
      for (int j = 0; j < currentRow.length(); j++) {
        if ("X".equals(String.valueOf(currentRow.charAt(j)))) {
          break;
        } else if (j == currentRow.length() - 1) {
          rowMissing++;
        }
      }
    }
    for (int i = 0; i < columnNumber; i++) {
      for (int j = 0; j < castle.length; j++) {
        if ("X".equals(String.valueOf(castle[j].charAt(i)))) {
          break;
        } else if ( j == castle.length - 1) {
          columnMissing ++;
        }
      }
    }

    return Math.max(columnMissing, rowMissing);
  }

  public static void main(String[] args) {
    String[] str = { "........X..",
        "...X.......",
        "...........",
        "..X...X....",
        "...........",
        "...........",
        "........X..",
        "...........",
        "...........",
        "........X..",
        ".....X....." };
    int res = missing(str);
    System.out.println(res);


  }
}
