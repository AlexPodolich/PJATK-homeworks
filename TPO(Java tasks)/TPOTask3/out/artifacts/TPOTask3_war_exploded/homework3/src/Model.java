import javax.ejb.Singleton;

public class Model {
    private static Model instance;
    private int num1;
    private int num2;
    private int sum;
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getSum() {
        return sum;
    }

    public void calculateSum(){
        sum = num1 + num2;
    }
}
