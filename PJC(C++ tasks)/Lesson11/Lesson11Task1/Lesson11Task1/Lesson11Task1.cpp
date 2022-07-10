#include <iostream>

using namespace std;

class Resistor {
private:
    double resistance;
public:
    Resistor() {
        resistance = 0;
    }
    Resistor(double resValue) {
        resistance = resValue;
    }
    double r() const {
        return resistance;
    }
    void r(double newValue) {
        resistance = newValue;
    }
    friend ostream& operator<< (ostream& stream, const Resistor res);
};

Resistor operator+(Resistor r1, Resistor r2) {
    Resistor result;
    result.r(r1.r() + r2.r());
    return result;
}

Resistor operator*(Resistor r1, Resistor r2) {
    Resistor result;
    result.r(pow(pow(r1.r(),-1) + pow(r2.r(), -1), -1));
    return result;
}

ostream& operator<<(ostream& stream, const Resistor res){
    return stream << res.resistance << " ";
}

int main()
{
    Resistor resistor(2);
    cout << "Resistor 1: " << resistor.r() << endl;
    resistor.r(10);
    Resistor resistor2(5);
    cout << "Resistor 1: " << resistor.r() << endl;
    cout << "Resistor 2: " << resistor2.r() << endl;
    std::cout << (resistor + resistor2) << " " << (resistor * resistor2) << std::endl;
}