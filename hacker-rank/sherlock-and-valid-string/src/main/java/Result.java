import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        // Write your code here

        Map<Character, Integer> occurrencesClassic = new HashMap<>();

        s.chars()
            .mapToObj(c -> (char) c)
            .forEach(
                ch ->
                {
                    if (!occurrencesClassic.containsKey(ch)) {
                        occurrencesClassic.put(ch, 1);
                    } else {
                        occurrencesClassic.put(ch, occurrencesClassic.get(ch) + 1);
                    }
                }
            );
        System.out.println(s + ": "+ occurrencesClassic);

        Map<Character, Long> occurrencesStream = s.chars()
            .mapToObj(c -> (char) c)
            .collect(groupingBy(Function.identity(), counting()));
        System.out.println(s + ": "+ occurrencesStream);

        return "BOH";
    }
}
