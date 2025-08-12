import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartySeats {
  static 	String[] seating(String[] attendees) {
    List<String> people = Arrays.asList(attendees);
    if (people.size() % 4 != 0) {
      return new String[]{};
    }
    List<String> boys = new ArrayList<>(people.stream()
        .map(String::trim)
        .map(s -> s.split("\\s+"))
        .filter(p -> p.length >= 2 && p[1].equalsIgnoreCase("boy"))
        .map(p -> p[0])
        .sorted()
        .toList());
    List<String> girls = people.stream()
        .map(String::trim)
        .map(s -> s.split("\\s+"))
        .filter(p -> p.length >= 2 && p[1].equalsIgnoreCase("girl"))
        .map(p -> p[0])
        .sorted()
        .toList();
    if (boys.size() != girls.size()) {
      return new String[]{};
    }

    for (int i = 0; i < girls.size(); i++) {
      int index = 0;
      if (i >= girls.size() / 2) {
        index = 1;
      }
      boys.add(i * 2 + index, girls.get(i));
    }
    boys.add(girls.size(), "HOSTESS");
    boys.add(0, "HOST");
//    String[] arr =
    System.out.println(boys.toString());
    return boys.toArray(new String[0]);
  }

  public static void main(String[] args) {
    String[] arr = {"BOB boy","SUZIE girl","DAVE boy","JO girl",
        "AL boy","BOB boy","CARLA girl","DEBBIE girl"};
    String[] res = seating(arr);
    System.out.println(res);
  }
}
