#include <iostream>
#include"Cmath"

using namespace std;

class Segment {
    double A, B;
public:
    Segment(double A, double B) : A(A), B(B) {}
    Segment operator*(double d) {
        return *(new Segment(A * d, B * d));
    };
    Segment operator/(double d) {
        return *(new Segment(A * (1/d), B * (1 / d)));
    };
    Segment operator+(double d) {
        return *(new Segment(A + d, B + d));
    };
    Segment operator-(double d) {
        return *(new Segment(A - d, B - d));
    };
    Segment operator+(Segment seg) {
        return *(new Segment(min (this->A, seg.A), max(this->B, seg.B)));
    };
    bool operator()(double d) {
        if (d > A && d < B) {
            return true;
        }
        else {
            return false;
        }
    };
    friend Segment operator*(double d, Segment seg);
    friend Segment operator/(double d, Segment seg);
    friend Segment operator+(double d, Segment seg);
    friend Segment operator-(double d, Segment seg);
    friend ostream& operator<< (ostream& stream, const Segment seg);
};

Segment operator*(double d, Segment seg) {
    return *(new Segment(seg.A * d, seg.B * d));
};

Segment operator/(double d, Segment seg) {
    return *(new Segment(seg.A * (1 / d), seg.B * (1 / d)));
};

Segment operator+(double d, Segment seg) {
    return *(new Segment(seg.A + d, seg.B + d));
};

Segment operator-(double d, Segment seg) {
    return *(new Segment(seg.A - d, seg.B - d));
};

ostream& operator<<(ostream& stream, const Segment seg) {
    return stream << "[" << seg.A << ", " << seg.B << "]";
}

int main()
{
    Segment seg{ 2,3 }, s = 1 + 2 * ((seg - 2) / 2 + seg) / 3;
    cout << s << endl << std::boolalpha;
    for (double x = 0.5; x < 4; x += 1)
        cout << "x=" << x << ": " << s(x) << endl;
}
