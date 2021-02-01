public class Card {
    private AccountNumber number;
    private int secretNumber;
    private String cardType, cardNumber, mounth, year, userName;

    public Card(AccountNumber number, int secretNumber, String cardType, String cardNumber, String mounth, String year, String userName) {
        this.number = number;
        this.secretNumber = secretNumber;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.mounth = mounth;
        this.year = year;
        this.userName = userName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getMounth() {
        return mounth;
    }

    public String getYear() {
        return year;
    }

    public String getUserName() {
        return userName;
    }
}
