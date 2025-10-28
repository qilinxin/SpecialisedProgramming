public class RunLengthEncoding {
  public static String decode(String text) {
    StringBuilder res = new StringBuilder();
    int count = 0;
    for (int i = 0; i < text.length(); i++) {
      char currentChar = text.charAt(i);
      if (currentChar <= '9' && currentChar >= '0') {
        if (count > 0) {
          count *= 10;
          if (count > 50) {
            return "TOO LONG";
          }
        }
        count += currentChar - '0';
        continue;
      } else if (count == 0){
        count = 1;
      }
      for (int j = 0; j < count; j++) {
        res.append(currentChar);
        if (res.length() > 50) {
          return "TOO LONG";
        }
      }
      count = 0;
    }

    return res.toString();
  }
  public static void main(String[] args) {
     String text = "123456789012345678901234567890B";
     String res = decode(text);
     System.out.println(res);
  }
}
