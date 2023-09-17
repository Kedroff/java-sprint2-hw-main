import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearManager yearManager = new YearManager();
        MonthManager monthManager = new MonthManager();
        Checker checker = new Checker(monthManager, yearManager);

        while (true) {
            printMenu();
            if (scanner.hasNextInt()) {
                int command = scanner.nextInt();
                switch (command) {
                    case (1):
                        monthManager.loadFile();
                        break;
                    case (2):
                        yearManager.YearManager("y.2021.csv");
                        break;
                    case (3):
                        checker.check();
                        break;
                    case (4):
                        monthManager.getMontyReport();
                        break;
                    case (5):
                        yearManager.printAll();
                        break;
                    case (0):
                        System.out.println("Всего доброго!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Выберите пункт от 1-6, 0 - чтобы выйти");
                        break;
                }
            } else {
                System.out.println("Вводить нужно только числа");
                break;
            }
        }

    }

    public static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из из приложения");
    }
}
