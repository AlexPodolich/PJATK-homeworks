package Polimorphism;

public abstract class Employee {
    private String name;
    private float salary;

    public Employee(String name, float salary) {
        setName(name);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
    }

    public void setSalary(float salary) {
        if(salary < 0){
            throw new IllegalArgumentException("salary must be positive");
        }
        this.salary = salary;
    }

    public abstract void calculateCryptoSalaryWithTaxes();
}
