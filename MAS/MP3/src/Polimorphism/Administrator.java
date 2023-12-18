package Polimorphism;

public class Administrator extends Employee{
    private int numOfBonuses;

    public Administrator(String name, float salary, int numOfBonuses) {
        super(name, salary);
        setNumOfBonuses(numOfBonuses);
    }

    public int getNumOfBonuses() {
        return numOfBonuses;
    }

    public void setNumOfBonuses(int numOfBonuses) {
        if(numOfBonuses < 0){
            throw new IllegalArgumentException("numOfBonuses must be positive");
        }
        this.numOfBonuses = numOfBonuses;
    }

    @Override
    public void calculateCryptoSalaryWithTaxes() {
        float bitcoinRate = 34.6f;
        float taxesRate = 0.15f;
        String bonusName = "Alex";
        float bonusSalary = 0;
        float bonusMoney = 500;
        if(getName().equals(bonusName))
            bonusSalary += bonusMoney;

        bonusSalary = numOfBonuses * bonusMoney;
        float finalSalary = (getSalary() * bitcoinRate + bonusSalary) * (1 - taxesRate);
        System.out.println("Administrator salary: " + finalSalary + " USDT");
    }
}
