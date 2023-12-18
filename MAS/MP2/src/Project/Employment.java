package Project;

import Project.Dormitory.Dormitory;
import Project.Employee.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employment extends ObjectPlus{
    private Dormitory dormitory;
    private Employee employee;
    private Float monthlySalary;
    private List<String> penalties = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;

    public Employment(Dormitory dormitory, Employee employee, Float monthlySalary, LocalDate startDate) {
        super();

        //comenting this section we are doing bag/history constraint
        //prevent from having the same pair twice
/*        for (Employment employment : dormitory.getEmployments()){
            if(employment.getEmployee().equals(employee)){
                throw new IllegalArgumentException("Employment is already exists");
            }
        }*/

        setDormitory(dormitory);
        setEmployee(employee);
        setMonthlySalary(monthlySalary);
        setStartDate(startDate);

        addToExtent();
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    private void setDormitory(Dormitory dormitory) {
        if(dormitory == null){
            throw new IllegalArgumentException("dormitory is required");
        }
        this.dormitory = dormitory;
        dormitory.addEmployment(this);
    }

    public Employee getEmployee() {
        return employee;
    }

    private void setEmployee(Employee employee) {
        if(employee == null){
            throw new IllegalArgumentException("employee is required");
        }
        this.employee = employee;
        employee.addEmployment(this);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new IllegalArgumentException("start Date is required");
        }
        if(startDate.isBefore(LocalDate.parse("2000-01-01"))){
            throw new IllegalArgumentException("Date should be after 01.01.2000");
        }
        if(startDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date should be from the future");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(startDate == null){
            throw new IllegalArgumentException("end Date is required");
        }
        if(!endDate.isAfter(startDate)){
            throw new IllegalArgumentException("end Date should be after start date");
        }
        this.endDate = endDate;
    }

    public float getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Float monthlySalary) {
        if(monthlySalary != null && monthlySalary < 0){
            throw new IllegalArgumentException("monthly Salary must be positive");
        }
        if(this.monthlySalary != null){
            if(monthlySalary < this.monthlySalary * 0.9) {
                throw new IllegalArgumentException("you cannot reduce monthly salary by 10% or more");
            }
            if(monthlySalary > this.monthlySalary * 1.25) {
                throw new IllegalArgumentException("you cannot increase monthly salary by 25% or more");
            }
        }
        this.monthlySalary = monthlySalary;
    }

    public List<String> getPenalties() {
        return penalties;
    }

    public void addPenalty(String penalty){
        if(penalty == null || penalty.isBlank()){
            throw new IllegalArgumentException("penalty is required");
        }
        this.penalties.add(penalty);
    }

    public void removePenalty(String penalty){
        this.penalties.remove(penalty);
    }

    public float getActualSalary(){
        int numOfPenalties = penalties.size();
        float fineForPenalties = 100.0f * numOfPenalties;
        float actualSalary = monthlySalary - fineForPenalties;
        return actualSalary;
    }

    public void remove(){
        //should remove all references
        if(this.dormitory != null) {
            Dormitory tmpDorm = this.dormitory;
            this.dormitory = null;
            tmpDorm.removeEmployment(this);
        }
        if(this.employee != null) {
            Employee tmpEmp = this.employee;
            this.employee = null;
            tmpEmp.removeEmployment(this);
        }

//        this.startDate = null;
//        this.endDate = null;
        System.out.println("References deleted");

        //remove employment from class extent
        removeFromExtent();
    }

    @Override
    public String toString() {
        if(this.employee == null && this.dormitory == null)
            return null;
        return "Employment{" +
                "dormitory=" + dormitory.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
