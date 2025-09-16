package calculator;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.SequencedMap;

public class Main {
    private static final SequencedMap<String, Integer> earnings;
    static {
        earnings = new LinkedHashMap<>();
        earnings.putLast("Bubblegum", 202);
        earnings.putLast("Toffee", 118);
        earnings.putLast("Ice cream", 2250);
        earnings.putLast("Milk chocolate", 1680);
        earnings.putLast("Doughnut", 1075);
        earnings.putLast("Pancake", 80);
    }

    public static void main(String[] args) {
        listEarnings();
        int income = sumEarnings();
        printIncome(income);
        int expenses = readExpenses();
        printNetIncome(income - expenses);
    }

    private static void  printNetIncome(int income) {
        System.out.println("Net income: $" + income);
    }

    private static int readExpenses() {
        Scanner input = new Scanner(System.in);
        System.out.println("Staff expenses:");
        int staffExpenses = input.nextInt();
        System.out.println("Other expenses:");
        int otherExpenses = input.nextInt();
        return staffExpenses + otherExpenses;
    }

    private static int sumEarnings() {
        return earnings.values().stream().mapToInt(Integer::intValue).sum();
    }

    private static void  printIncome(int income) {
        System.out.println("Income: $" + income);
    }

    private static void listEarnings() {
        System.out.println("Earned amount:");
        Main.earnings.forEach((key, value) ->
                System.out.printf("%s: $%d%n", key, value)
        );
        System.out.println();
    }
}