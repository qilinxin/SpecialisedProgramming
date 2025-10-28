public class RunLengthEncoding {
  public static String decode(String text) {
    StringBuilder res = new StringBuilder();
    int count = 0;
    for (int i = 0; i < text.length(); i++) {
      char currentChar = text.charAt(i);
      if (currentChar <= '9' && currentChar >= '0') {
        if (count > 0) {
          count *= 10;
        }
        count += currentChar - '0';
        continue;
      } else if (count == 0){
        count = 1;
      }
      for (int j = 0; j < count; j++) {
        res.append(currentChar);
      }
      count = 0;
    }

    if (res.length() > 50) {
      return "TOO LONG";
    }
    return res.toString();
  }
  public static void main(String[] args) {
     String text = "21Z13S9A8M";
     String res = decode(text);
     System.out.println(res);
  }
}
