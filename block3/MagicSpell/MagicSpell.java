import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicSpell {

  public static String fixTheSpell(String spell) {
    char[] spellArray = spell.toCharArray();
    char[] azArray = new char[spell.length()];
    int index = 0;
    for (int i = 0; i < spellArray.length; i++) {
      char current = spell.charAt(i);
      if ("AZ".contains(String.valueOf(current))) {
        azArray[index] = current;
        index ++;
      }
    }
    int charIndex = 0;
    for (int i = azArray.length - 1; i >= 0; i--) {
      char current = spell.charAt(i);
      if ("AZ".contains(String.valueOf(current))) {
        spellArray[i] = azArray[charIndex];
        charIndex ++;
      }
    }
    return String.valueOf(spellArray);

  }

  public static void main(String[] args) {
    String spell = "AZBASGHNAZAHBNVZZGGGAGGZAZ"
        ;
    String res = fixTheSpell(spell);
    System.out.println(res);
  }
}
