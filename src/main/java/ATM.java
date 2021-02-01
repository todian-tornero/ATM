import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ATM implements OperationATM{
    public boolean cardOnATM = false;
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ATM atm = new ATM();
        Card card = new Card("СБЕРБАНК", new AccountNumber(123456789), 000, "VISA", "1234 1234 1234 1234", 12, 23, "DMITRIY TOKAR", 1234);
        atm.greeting();
        System.out.println("***********************************");
        System.out.println("Вставить карту? Y/N");
        System.out.println("***********************************");
        try {
            if ("Y".equals(reader.readLine())) {
                atm.cardOnATM = true;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            while ((card.getCardBlock() < 3)) {
                atm.requestPin();
                if (Integer.parseInt(reader.readLine()) != card.getPin()){
                    card.counterCardBlock();
                    System.out.println("Введён неверный PIN-код");
                }
                else{
                    System.out.println("OK");
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public void greeting() {
        System.out.println("Здравствуйте!");
        System.out.println("Вставьте карту");
    }

    @Override
    public void requestPin() {
        System.out.println("Введите PIN-код");
    }
}
