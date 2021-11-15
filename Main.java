package com.VB5;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MonthsInYear = 12;
    final static byte Percent = 100;

    public static void main(String[] args) {
        int principle = (int)readNumber("Principle: ", 1000, 100000000);
        float annualInterest =(float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        printMortgage(principle, annualInterest, years);

        printPaymentSchedule(principle, annualInterest, years);
    }

    public static void printMortgage(int principle, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principle, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public static void printPaymentSchedule(int principle, float annualInterest, byte years) {
        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("-----------------");
        for(short month = 1; month <= years * MonthsInYear; month++){
            double balance = calculateBalance(principle, annualInterest, years, month);
            System.out.println( NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public  static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(int principle,
                                          float annualInterest,
                                          byte years,
                                          short numberOfPaymentsMade){

        float numberOfPayments = years * MonthsInYear;
        float  monthlyInterest = annualInterest / Percent / MonthsInYear;

        double balance = principle
                *(Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) -1);

        return balance;
    }

    public static double calculateMortgage(int principle,
                                           float annualInterest,
                                           byte years){

        float numberOfPayments = years * MonthsInYear;
        float  monthlyInterest = annualInterest / Percent / MonthsInYear;

        double mortgage = principle
                *(monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) -1);

        return mortgage;
    }
}