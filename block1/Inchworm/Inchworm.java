public class Inchworm {

  public int lunchtime(int branch, int rest, int leaf) {
    //check parameters if needed

    int eatCount = 0;
    int currentStep = 0;

    while (currentStep <= branch) {

      if (currentStep % leaf == 0) {
        eatCount++;
//        System.out.println("currentStep =====" + currentStep);
      }
      currentStep += rest;
    }

    return eatCount;
  }


//  public static void main(String[] args) {
//    Inchworm inchworm = new Inchworm();
////    int res = inchworm.lunchtime(11, 2,4);
////    int res = inchworm.lunchtime(12, 6,4);
////    int res = inchworm.lunchtime(20, 3,7);
////    int res = inchworm.lunchtime(15, 16,5);
////    int res = inchworm.lunchtime(1000, 3,7);
//    int res = inchworm.lunchtime(1000, 7,3);
//    System.out.println("res =====" + res);
//  }
}
