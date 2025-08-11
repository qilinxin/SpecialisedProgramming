import java.util.*;

public class SquareOfDigits {
  public  int  getMax(String[] data) {
      List<String> wholeData = Arrays.asList(data);
      int max = 1;
//      int row = 0;
      int colNum = 0;
      char curNum;
      for(int rowNum = 0; rowNum < wholeData.size(); rowNum ++) {
        String row = wholeData.get(rowNum);
        for(int curColNum = colNum; curColNum < row.length(); curColNum ++) {
          curNum = row.charAt(curColNum);
          //If there is a matching number in the current line and the interval is greater than the current max, determine whether the corresponding position in the subsequent line is the same number
          for (int curLength = 1; curLength + curColNum < row.length() && rowNum + curLength < wholeData.size() ; curLength++ ) {
            if(curNum == row.charAt(curColNum + curLength)
                && curNum == wholeData.get(rowNum + curLength).charAt(curColNum)
                && curNum == wholeData.get(rowNum + curLength).charAt(curColNum + curLength)) {
              int side = curLength + 1;
              if (side > max) {
                max = side;
              }
            }
          }
        }
      }

      return max * max;
  }

//  public static void main(String[] args) {
////    String[] arr = { "12", "34" };
////    String[] arr =  {"1255",
////        "3455"};
//    String[] arr =  {"42101",
//        "22100",
//        "22101"};
//
//
//    SquareOfDigits squareOfDigits = new SquareOfDigits();
//    int res = squareOfDigits.getMax(arr);
//    System.out.println(res);
//  }
}

