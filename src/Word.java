import java.util.ArrayList;

public class Word {
    private ArrayList<Letter> letters;

    public Word(int length) {
        letters = new ArrayList<>(length);
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }

    public void addLetter(Letter letter) {
        letters.add(letter);
    }
}
