public class Card {
    private AccountNumber number;
    private int secretNumber, pin, mounth, year, cardBlock = 0, balance;
    private String cardType, cardNumber, userName, bankName;

    public Card(String bankName, AccountNumber number, int secretNumber, String cardType, String cardNumber, int mounth, int year, String userName, int pin) {
        this.bankName = bankName;
        this.number = number;
        this.secretNumber = secretNumber;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.mounth = mounth;
        this.year = year;
        this.userName = userName;
        this.pin = pin;
        this.setBalance(1000);
    }

    public String getBalance() {
        return "На вашем счёте " + balance + " руб.";
    }

    public void setBalance(int balance) {
        this.balance = this.balance + balance;
    }

    public void counterCardBlock(){
        cardBlock++;
    }

    public int getCardBlock(){
        return cardBlock;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getMounth() {
        return mounth;
    }

    public int getYear() {
        return year;
    }

    public String getUserName() {
        return userName;
    }

    public int getPin() {
        return pin;
    }

    public String getBankName() {
        return bankName;
    }

    public String toString() {

        return "_________________________"+ "\n" +
                "|  " + bankName + "             |" + "\n" +
                "|                       |" + "\n" +
                "|                       |" + "\n" +
                "|  " + cardNumber + "  |" + "\n" +
                "|         " + mounth + "/" + year + "         |" + "\n" +
                "| " + userName + "    " + cardType + " |" + "\n" +
                "|_______________________|";
    }
}
