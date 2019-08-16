public class Poker {
    private int number;
    private char type;
    private String card;

    public Poker(){

    }

    public Poker(int number, char type, String card) {
        this.number = number;
        this.type = type;
        this.card = card;
    }

    public int getNumber() {
        return number;
    }


    public char getType() {
        return type;
    }


    public String getCard() {
        return card;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
