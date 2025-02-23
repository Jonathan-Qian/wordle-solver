public class Letter {
    private final char letter;
    private final int state;
    public static final int GRAY = 0, YELLOW = 1, GREEN = 2;

    public Letter(char letter, int state) {
        this.letter = letter;
        this.state = state;
    }

    public char getLetter() {
        return letter;
    }

    public int getState() {
        return state;
    }
}
