import java.util.HashMap;

public class Checker {
    public MonthManager monthManager;
    public YearManager yearManager;

    HashMap<Integer, Integer> checkerProfit = new HashMap<>();
    HashMap<Integer, Integer> checkerSpent = new HashMap<>();

    public Checker(MonthManager monthManager, YearManager yearManager) {
        this.monthManager = monthManager;
        this.yearManager = yearManager;
    }

    public void check() {
        if (monthManager.hash.size() == 0 || yearManager.saleYear.size() == 0) { // Проверка были ли считаны файлы
            System.out.println("Для сверки сначала считайте отчет.");
            return;
        }

        monthManager.getIncomeAndSpend(); //Метод определяет расходы и доходы по разным хэш таблицам

        if (yearManager.profitOrders.equals(monthManager.profitOrders)) { //Сверка отчетов по доходам
            System.out.println("Сверка отчетов о доходах прошла успешно.");
        } else {
            checkerProfit = monthManager.profitOrders;
            for (Integer month : yearManager.profitOrders.keySet()) {
                if (checkerProfit.containsKey(month)) {
                    if (!checkerProfit.get(month).equals(yearManager.profitOrders.get(month))) {
                        if (month == 1) {
                            System.out.println("Отчет о доходах за Январь не сошелся.");
                        } else if (month == 2) {
                            System.out.println("Отчет о доходах за Февраль не сошелся.");
                        } else if (month == 3) {
                            System.out.println("Отчет о доходах за Март не сошелся.");
                        }
                    }
                }
            }
        }

        if (yearManager.spendOrders.equals(monthManager.spendOrders)) { //Сверка отчетов по расходам
            System.out.println("Сверка отчетов о расходах прошла успешно.");
        } else {
            checkerSpent = monthManager.spendOrders;
            for (Integer month : yearManager.spendOrders.keySet()) {
                if (checkerSpent.containsKey(month)) {
                    if (!checkerSpent.get(month).equals(yearManager.spendOrders.get(month))) {
                        if (month == 1) {
                            System.out.println("Отчет о расходах за Январь не сошелся.");
                        } else if (month == 2) {
                            System.out.println("Отчет о расходах за Февраль не сошелся.");
                        } else if (month == 3) {
                            System.out.println("Отчет о расходах за Март не сошелся.");
                        }
                    }
                }
            }
        }
    }
}