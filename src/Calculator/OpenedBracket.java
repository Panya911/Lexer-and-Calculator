package Calculator;


public class OpenedBracket implements IBracket {
    private final char ch;

    public OpenedBracket(char str)
    {
        this.ch =str;
    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public String toString(){
        return Character.toString(ch);
    }
}
