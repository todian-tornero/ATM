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
            String s = reader.readLine();
            if ("Y".equals(s)) {
                atm.cardOnATM = true;
            }
            else if ("N".equals(s)){
                System.out.println("Зачем ты тогда пришёл?");
            }
            else {
                atm.cardOnATM = true;                     //Тестировочный блок
                System.out.println(card.toString());
                atm.cardOnATM = false;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        if (atm.cardOnATM == true){
            try {
                while ((card.getCardBlock() < 3)) {
                    atm.requestPin();
                    if (Integer.parseInt(reader.readLine()) != card.getPin()) {
                        card.counterCardBlock();
                        System.out.println("Введён неверный PIN-код");
                    } else {
                        System.out.println("OK");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (card.getCardBlock() == 3) {
                System.out.println("Карта заблокирована");
            }
            atm.mainMenu();
            try {
                switch (Integer.parseInt(reader.readLine())){
                    case (1):
                        System.out.println(card.getBalance());
                    break;
                    case (2): card.setBalance(atm.cash());
//                        System.out.println(card.getBalance());
                        break;
                    case (3):System.out.println(card.getBalance());
                        card.setBalance(atm.refill());
                        System.out.println(card.getBalance());
                        break;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
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

    @Override
    public void mainMenu() {
        System.out.println("_____________________________________________" + "\n" + "| ГЛАВНОЕ МЕНЮ"  +
                "                              |" + "\n" +
                "| 1. Запросить баланс                       |" + "\n" +
                "| 2. Пополнить счёт                         |" + "\n" +
                "| 3. Снять наличные                         |" + "\n" +
                "| 4. Вернуть карту                          |" + "\n" +
                "|___________________________________________|");
    }

    public int cash()throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вставьте купюры в купюроприёмник");
        System.out.println("***********************************");
        System.out.println("Введите сумму пополнения");
        System.out.println("***********************************");
        int sum = Integer.parseInt(reader.readLine());
        return sum;
    }

    public int refill()throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cash;
        System.out.println("_____________________________________________" + "\n" + "| ВЫБЕРИТЕ СУММУ"  +
                "                            |" + "\n" +
                "| 1. 100                             3000 .5|" + "\n" +
                "| 2. 500                             5000 .6|" + "\n" +
                "| 3. 1000                                   |" + "\n" +
                "| 4. 2000                    Другая сумма .7|" + "\n" +
                "|___________________________________________|");
        switch (cash = Integer.parseInt(reader.readLine())){
            case (1):cash = -100;
            break;
            case (2):cash = -500;
            break;
            case (3):cash = -1000;
            break;
            case (4):cash = -2000;
            break;
            case (5):cash = -3000;
            break;
            case (6):cash = -5000;
            break;
            case (7):cash = Integer.parseInt(reader.readLine());
            break;
        }
        return cash;
    }
}
