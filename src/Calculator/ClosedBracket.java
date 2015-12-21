package Calculator;


public class ClosedBracket implements IBracket {
    private final char ch;

    public ClosedBracket(char str) {
        this.ch = str;
    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public String toString() {
        return Character.toString(ch);
    }
}
