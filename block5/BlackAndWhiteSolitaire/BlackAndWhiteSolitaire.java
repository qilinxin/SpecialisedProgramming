public class BlackAndWhiteSolitaire {
  public static int minimumTurns(String cardFront) {

    int res1 = 0;
    char previousChar = cardFront.charAt(0);
    for (int i = 1; i < cardFront.length(); i++) {
      char currentChar = cardFront.charAt(i);
      if (previousChar == currentChar) {
        res1 += 1;
        previousChar = currentChar == 'W' ? 'B' : 'W';
      } else {
        previousChar = currentChar;
      }
    }
    int res2 = 1;
    char previousChar2 = cardFront.charAt(0) == 'W' ? 'B' : 'W';
    for (int i = 1; i < cardFront.length(); i++) {
      char currentChar = cardFront.charAt(i);
      if (previousChar2 == currentChar) {
        res2 += 1;
        previousChar2 = currentChar == 'W' ? 'B' : 'W';
      } else {
        previousChar2 = currentChar;
      }
    }
    return Math.min(res1, res2);
  }
  public static void main(String[] args) {
    String cardFront = "BBWBWWBWBWWBBBWBWBWBBWBBW";
    System.out.println(cardFront.length());
    int res = minimumTurns(cardFront);
    System.out.println(res);
  }
}
