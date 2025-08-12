public class RugSizes {

  static int rugCount(int area){
    int a = (int) Math.floor(Math.sqrt(area));
    int count = 0;
    for(int i = 1; i <= a; i++){
      double b = (double) area /i;
      if (b != (int) b) {
        continue;
      }
//      System.out.println("long ===" + i + "; width ====" + b);
      if (!(i % 2 == 0 && b % 2 == 0) || i == b) {
        count ++;
      }

    }

    return count;
  }

  public static void main(String[] args) {
    int count = rugCount(30);
    System.out.println(count);
  }


}
