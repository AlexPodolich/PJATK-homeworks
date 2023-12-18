package Polimorphism;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CleaningStuff extends Employee{

    private int numOfPenalty;
    private List<String> goodReviews = new ArrayList<>();
    private List<String> badReviews = new ArrayList<>();


    public CleaningStuff(String name, float salary, int numOfPenalty, List<String> goodReviews, List<String> badReviews) {
        super(name, salary);
        setNumOfPenalty(numOfPenalty);
        setGoodReviews(goodReviews);
        setBadReviews(badReviews);
    }

    public int getNumOfPenalty() {
        return numOfPenalty;
    }

    public void setNumOfPenalty(int numOfPenalty) {
        if(numOfPenalty < 0){
            throw new IllegalArgumentException("numOfPenalty must be positive");
        }
        this.numOfPenalty = numOfPenalty;
    }

    public List<String> getGoodReviews() {
        return Collections.unmodifiableList(goodReviews);
    }

    public void setGoodReviews(List<String> goodReviews) {
        if(goodReviews == null){
            throw new IllegalArgumentException("goodReviews is required");
        }
        for (String review : goodReviews) {
            if(review == null || review.isBlank()){
                throw new IllegalArgumentException("review is required");
            }
        }
        this.goodReviews = goodReviews;
    }

    public List<String> getBadReviews() {
        return Collections.unmodifiableList(badReviews);
    }

    public void setBadReviews(List<String> badReviews) {
        if(badReviews == null){
            throw new IllegalArgumentException("badReviews is required");
        }
        for (String review : badReviews) {
            if(review == null || review.isBlank()){
                throw new IllegalArgumentException("review is required");
            }
        }
        this.badReviews = badReviews;
    }

    @Override
    public void calculateCryptoSalaryWithTaxes() {
        float bitcoinRate = 14.2f;
        float taxesRate = 0.165f;
        float bonusSalary = 0;
        float penaltySalary = 0;
        float bonusMoney = 250;
        float penaltyMoney = 50;

        penaltySalary += numOfPenalty * penaltyMoney;

        penaltySalary += badReviews.size() * penaltyMoney;

        bonusSalary += goodReviews.size() * bonusMoney;

        float finalSalary = ((getSalary() * bitcoinRate + bonusSalary) - penaltySalary) * (1 - taxesRate);
        System.out.println("Cleaning stuff salary: " + finalSalary + " UAH");
    }
}
