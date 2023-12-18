package Polimorphism;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Administrator administrator = new Administrator("Alex", 34000f, 3);

        administrator.calculateCryptoSalaryWithTaxes();

        List<String> goodReviews = new ArrayList<>();
        goodReviews.add("Nice job");
        goodReviews.add("Good");
        goodReviews.add("Perfect");
        goodReviews.add("Nice");

        List<String> badReviews = new ArrayList<>();
        goodReviews.add("Sooo bad");
        goodReviews.add("haYiaaa");
        goodReviews.add("very bad");
        goodReviews.add("nice try...");
        goodReviews.add("...");
        CleaningStuff cleaningStuff = new CleaningStuff("Vasya", 12000f, 5, goodReviews, badReviews);

        cleaningStuff.calculateCryptoSalaryWithTaxes();
    }
}
