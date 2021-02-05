import javax.sound.sampled.*;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class ATM implements OperationATM{
    public boolean cardOnATM = false;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ATM atm = new ATM();
        Card card = new Card("СБЕРБАНК", new AccountNumber(123456789), 000, "VISA", "1234 1234 1234 1234", 12, 23, "DMITRIY TOKAR", 1234);
        while (true) {
            atm.greeting();
            System.out.println("***********************************");
            System.out.println("Вставить карту? Y/N");
            System.out.println("***********************************");
            try {
                String temp = reader.readLine(),
                s = temp.toUpperCase();
                if ("Y".equals(s)) {
                    atm.cardOnATM = true;
                } else if ("N".equals(s)) {
                    System.out.println("Зачем ты тогда пришёл?");
                } else if ("EXIT".equals(s)){
                    break;}
                else {
                    System.out.println(card.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (atm.cardOnATM == true) {
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
                while (atm.cardOnATM == true) {
                    int s = Integer.parseInt(reader.readLine());
                    try {
                        switch (s) {
                            case (1):
                                System.out.println(card.getBalance());
                                atm.mainMenu();
                                break;
                            case (2):
                                card.setBalance(atm.cash());
                                atm.mainMenu();
                                break;
                            case (3):
                                int temp = -atm.refill();
                                if (temp < card.getBal()){
                                card.setBalance(-temp);
                                //Thread.sleep(800);
                                atm.audio();
                                System.out.println("Заберите карту");
                                System.out.println("Заберите деньги");
                                atm.cardOnATM = false;
                                break;}
                                else System.out.println("Недостаточно средств");
                                atm.mainMenu();
                                break;
                            case (4):
                                System.out.println("Заберите карту");
                                System.out.println("");
                                atm.cardOnATM = false;
                                break;
                            default:break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
            case (7):
                System.out.println("Введите сумму");
                cash = -(Integer.parseInt(reader.readLine()));
            break;
        }
        return cash;
    }

    public void audio(){
        try {
            File soundFile = new File("1.wav"); //Звуковой файл

            //Получаем AudioInputStream
            //Вот тут могут полететь IOException и UnsupportedAudioFileException
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            //Получаем реализацию интерфейса Clip
            //Может выкинуть LineUnavailableException
            Clip clip = AudioSystem.getClip();

            //Загружаем наш звуковой поток в Clip
            //Может выкинуть IOException и LineUnavailableException
            clip.open(ais);

            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start(); //Поехали!!!

            //Если не запущено других потоков, то стоит подождать, пока клип не закончится
            //В GUI-приложениях следующие 3 строчки не понадобятся
            Thread.sleep(clip.getMicrosecondLength()/1000);
            clip.stop(); //Останавливаем
            clip.close(); //Закрываем
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {}
    }
}
