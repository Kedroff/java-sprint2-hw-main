import java.util.ArrayList;
import java.util.HashMap;

public class MonthManager {
    FileReader reader = new FileReader();
    ArrayList<MonthTransaction> monthT = new ArrayList<>();
    HashMap<Integer, Integer> profitOrders = new HashMap<>();
    HashMap<Integer, Integer> spendOrders = new HashMap<>();
    HashMap<Integer, ArrayList<MonthTransaction>> hash = new HashMap<>();

    public void loadFile() {
        for (int month = 1; month <= 3; month++) {
            monthT = new ArrayList<>();
            String nameFile = "m.20210" + month + ".csv";
            ArrayList<String> content = reader.readFileContents(nameFile);
            for (int j = 1; j < content.size(); j++) {
                String lines = content.get(j);
                String[] parts = lines.split(",");
                String name = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int price = Integer.parseInt(parts[3]);

                MonthTransaction monthTrans = new MonthTransaction(name, isExpense, quantity, price);
                monthT.add(monthTrans);
                hash.put(month, monthT);
            }
        }
    }

    void getMaxProfitOrder(Integer month) { //Поиск максимального дохода по месяцам
        int maxOrder = 0;
        String maxOrderTitle = null;
        if (hash.containsKey(month)) {
            ArrayList<MonthTransaction> freqs = hash.get(month);
            for (MonthTransaction freq : freqs) {
                if (!freq.isExpense) {
                    int order = freq.quantity * freq.price;
                    if (order > maxOrder) {
                        maxOrder = order;
                        maxOrderTitle = freq.name;
                    }
                }
            }
        }
        System.out.println("Самый прибыльный товар - " + maxOrderTitle + " и его стоимость: " + maxOrder);
    }

    void getMaxSpendOrder(Integer month) { //Поиск максимальной траты
        int maxSpend = 0;
        String maxSpendTitle = null;
        if (hash.containsKey(month)) {
            ArrayList<MonthTransaction> freqs = hash.get(month);
            for (MonthTransaction freq : freqs) {
                if (freq.isExpense) {
                    int spend = freq.quantity * freq.price;
                    if (spend > maxSpend) {
                        maxSpend = spend;
                        maxSpendTitle = freq.name;
                    }
                }
            }
        }
        System.out.println("Самая большая трата - " + maxSpendTitle + " и сумма траты: " + maxSpend);
    }

    void getIncomeAndSpend() {  //Метод определяет расходы и доходы по разным хэш таблицам
        int income = 0;
        int spend = 0;
        for (Integer month : hash.keySet()) {
            ArrayList<MonthTransaction> transaction = hash.get(month);
            for (MonthTransaction sale : transaction) {
                int sum = sale.quantity * sale.price;
                if (!sale.isExpense) {
                    income += sum;
                    profitOrders.put(month, income);
                } else {
                    spend += sum;
                    spendOrders.put(month, spend);
                }
            }
            income = 0;
            spend = 0;
        }
    }

    void getMontyReport() { //Вывод информации о доходах и расходах по месяцам
        if (hash.size() == 0) {
            System.out.println("Считайте сначала месячные отчеты!");
        }
        for (Integer month : hash.keySet()) {
            if (month == 1) {
                System.out.println("Январь");
                getMaxProfitOrder(month);
                getMaxSpendOrder(month);
            } else if (month == 2) {
                System.out.println("Февраль");
                getMaxProfitOrder(month);
                getMaxSpendOrder(month);
            } else if (month == 3) {
                System.out.println("Март");
                getMaxProfitOrder(month);
                getMaxSpendOrder(month);
            } else {
                System.out.println("Такого месяца еще нет");
            }
        }
    }
}


