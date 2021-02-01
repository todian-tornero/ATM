public class ATM implements OperationATM{
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.greeting();
    }

    @Override
    public void greeting() {
        System.out.println("Здравствуйте!");
        System.out.println("Вставьте карту");
    }

    @Override
    public void requestPin() {

    }
}
