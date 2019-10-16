import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SabreIndexer {
    String indexText(String text) {
        String lowerCaseText = text.toLowerCase(Locale.ENGLISH).replaceAll("[^a-zA-Z ]", "");

        List<String> wordsInText = getSortedWordsFrom(lowerCaseText);
        List<Character> lettersInText = getLettersFrom(lowerCaseText);

        return buildResponse(wordsInText, lettersInText);
    }

    private List<String> getSortedWordsFrom(String text) {
        return Arrays.stream(text.split(" "))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private List<Character> getLettersFrom(String text) {
        return IntStream.rangeClosed('a', 'z')
                .mapToObj(var -> (char) var)
                .filter(character -> isCharacterAppearsInString(character, text))
                .collect(Collectors.toList());
    }

    private String buildResponse(List<String> wordsInText, List<Character> lettersInText) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char letter: lettersInText) {
            List<String> strings = getAllWordsThatContains(letter, wordsInText);
            stringBuilder.append(letter);
            stringBuilder.append(": ");
            stringBuilder.append(strings.get(0));
            for (String string: strings.subList(1, strings.size())) {
                stringBuilder.append(", ");
                stringBuilder.append(string);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private List<String> getAllWordsThatContains(char value, List<String> split) {
        return split.stream()
                .filter(s -> isCharacterAppearsInString(value, s))
                .collect(Collectors.toList());
    }

    private boolean isCharacterAppearsInString(char character, String string) {
        return 0 <= string.indexOf(character);
    }
}