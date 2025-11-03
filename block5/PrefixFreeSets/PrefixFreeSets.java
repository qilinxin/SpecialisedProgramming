import java.util.Arrays;

public class PrefixFreeSets {

  public static int maxElements(String[] words) {
    Arrays.sort(words);
    int res = 0;
    for (int i = 0; i < words.length; i++) {
      boolean isContain = false;
      if (i + 1 < words.length) {
        String currentWord = words[i];
        String nextWord = words[i + 1];
        if (nextWord.startsWith(currentWord)) {
          isContain = true;
        }
      }
      if (!isContain) {
        res++;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    String[] words = {"ab","abc","abd","abe","rerun","running"};

    int res = maxElements(words);
    System.out.println(res);
  }
}
