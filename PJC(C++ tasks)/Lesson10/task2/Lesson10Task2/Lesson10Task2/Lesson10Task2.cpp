#include <iostream>

using namespace std;

class Rect {
private:
	int side1;
	int side2;
public:
	Rect() {
		side1 = 1;
		side2 = 1;
	}
	Rect(int lenght) {
		side1 = lenght;
		side2 = lenght;
	}
	Rect(int length1, int length2) {
		side1 = length1;
		side2 = length2;
	}
	int getA() {
		return side1;
	}
	int getB() {
		return side2;
	}
	float getDiagonal() {
		return sqrt(side1 * side1 + side2 * side2);
	}
	int getArea()const {
		return side1 * side2;
	}
	bool isLargerThan(const Rect& rect2) {
		if (this->getArea() > rect2.getArea()) {
			return true;
		}
		else {
			return false;
		}
	}
	void info() {
		cout << "Rect[" << this->side1 << "," << this->side2 << "]";
	}
};

int main()
{
	Rect rect;
	rect.info();
	cout << endl;
	cout << rect.getA() << endl;
	cout << rect.getB() << endl;
	cout << rect.getDiagonal() << endl;
	cout << rect.getArea() << endl;

	Rect rect3(4);
	rect3.info();
	cout << endl;
	cout << rect3.getA() << endl;
	cout << rect3.getB() << endl;
	cout << rect3.getDiagonal() << endl;
	cout << rect3.getArea() << endl;
	cout << endl;
	cout << rect3.isLargerThan(rect);

	Rect rect2(3, 4);
	rect2.info();
	cout << endl;
	cout << rect2.getA() << endl;
	cout << rect2.getB() << endl;
	cout << rect2.getDiagonal() << endl;
	cout << rect2.getArea() << endl;

	cout << rect.isLargerThan(rect2);
	cout << endl;
	cout << rect2.isLargerThan(rect);
	return 0;
}
