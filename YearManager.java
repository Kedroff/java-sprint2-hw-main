import java.util.ArrayList;
import java.util.HashMap;

public class YearManager {
    FileReader reader = new FileReader();
    ArrayList<YearTransaction> saleYear = new ArrayList<>();
    public HashMap<Integer, Integer> profitOrders = new HashMap<>();
    public HashMap<Integer, Integer> spendOrders = new HashMap<>();

    public void YearManager(String path) {  // Считываем годовой отчет

        ArrayList<String> content = reader.readFileContents(path);

        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearTransaction yearReport = new YearTransaction(month, amount, isExpense);
            saleYear.add(yearReport);

            if (yearReport.isExpense) {
                spendOrders.put(yearReport.month, yearReport.amount);
            } else {
                profitOrders.put(yearReport.month, yearReport.amount);
            }
        }
        System.out.println("Годовой отчет считан!");
    }

    void getMonthsAmount() {   //Получаем прибыль по месяцам за год
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (YearTransaction year : saleYear) {
            if (year.isExpense) {
                hash.put(year.month, hash.getOrDefault(year.month, 0) - year.amount);
            } else {
                hash.put(year.month, hash.getOrDefault(year.month, 0) + year.amount);
            }
        }
        for (Integer month : hash.keySet()) {
            if (month == 1) {
                System.out.println("Прибыль за Январь: " + hash.get(month));
            } else if (month == 2) {
                System.out.println("Прибыль за Февраль: " + hash.get(month));
            } else if (month == 3) {
                System.out.println("Прибыль за Март: " + hash.get(month));
            }
        }
    }

    void avgExpense() { // Среднии траты в году
        int allExpense = 0;
        int count = 0;
        for (YearTransaction transaction : saleYear) {
            if (transaction.isExpense == true) {
                count++;
                allExpense += transaction.amount;
            }
        }
        double avg = allExpense / count;
        System.out.println("Среднии расходы за год составили: " + avg);
    }

    void avgProfit() {  // Средний профит в году
        int count = 0;
        int allProfit = 0;
        for (YearTransaction transaction : saleYear) {
            if (transaction.isExpense == false) {
                count++;
                allProfit += transaction.amount;
            }
        }
        double avg = allProfit / count;
        System.out.println("Среднии доходы за год составили: " + avg);
    }

    void printAll() {   // Вывод всех методов
        if (saleYear.size() == 0) {
            System.out.println("Считайте сначала годовой отчет!");
        } else {
            getMonthsAmount();
            avgExpense();
            avgProfit();
        }
    }
}
