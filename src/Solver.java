import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solver {
    private final int length;
    private HashMap<Character, ArrayList<Integer>> confirmedPositions, excludedPositions;
    private HashMap<Character, Integer> minimumCount, confirmedCount; //for double letter word guesses (each one will confirm a letter count for the duplicated letter), a superset of gray
    private ArrayList<String> wordBuffer;

    // if continuing on with this system (which is flawed), we will have to store positions of grays so if for example there is: e (green) h (green) e (yellow) j (gray) e (gray), the system knows to eliminate any word with 'e' at the last letter (in this case, e must be at where j is and there must be two e's)
    // grays confirm how many of a letter there is
    // the current inputWord method generally will not work if inputWord is used more than once, namely where it sums up the number of yellows and greens of a letter since a yellow may turn into a green)
    // due to the observation made in the previous comment, I think the hashmaps should be split up into where a letter has to be, where a letter can't be and the letter count of that letter (if findable)

    public Solver(int length) {
        this.length = length;
        confirmedPositions = new HashMap<>();
        excludedPositions = new HashMap<>();
        minimumCount = new HashMap<>();
        confirmedCount = new HashMap<>();
        wordBuffer = new ArrayList<>(10000);
    }

    private void readWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("words_alpha.txt"));
            String temp;

            while ((temp = reader.readLine()) != null) {
                if (temp.length() == length)
                    wordBuffer.add(temp);
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    public void inputWord(Word word) {
        ArrayList<Letter> letters = word.getLetters();
        HashSet<Character> gray = new HashSet<>();
        Letter currentLetter;

        for (int i = 0; i < letters.size(); i++) {
            currentLetter = letters.get(i);

            switch (currentLetter.getState()) {
                case Letter.GRAY:
                    if (!confirmedLetterCounts.containsKey(currentLetter.getLetter()))
                        gray.add(currentLetter.getLetter());
                    break;

                case Letter.YELLOW:
                    addToHashMapList(yellow, currentLetter.getLetter(), i);
                    break;

                case Letter.GREEN:
                    addToHashMapList(green, currentLetter.getLetter(), i);
                    break;
            }
        }

        for (Character c : gray) {
            confirmedLetterCounts.put(c,
                    (yellow.get(c) == null ? 0 : yellow.get(c).size()) + (green.get(c) == null ?
                            0 : green.get(c).size()));
        }

        narrowDown();
    }

    private void narrowDown() {
        //ArrayList<Integer> indices;
        String currentWord;

        for (int i = 0; i < wordBuffer.size(); i++) {

            /*for (Map.Entry<Character, ArrayList<Integer>> entry : green.entrySet()) {
                indices = entry.getValue();

                for (Integer index : indices) {
                    if (wordBuffer.get(i).charAt(index) != entry.getKey()) {
                        wordBuffer.remove(i);
                        break;
                    }
                }
            }*/

//            currentWord =

            for (int j = 0; j < )

        }
    }

    public ArrayList<String> possibleWords() {
        return wordBuffer;
    }

    private static void addToHashMapList(HashMap<Character, ArrayList<Integer>> map, char c, int i) {
        if (!map.containsKey(c)) {
            map.put(c, new ArrayList<>());
        }

        map.get(c).add(i);
    }
}
