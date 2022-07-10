#include <iostream>
#include <vector>
#include <functional>
#include <cmath>
using namespace std;


struct Point {
    double x;
    double y;
};

struct Rect {
    Point upL;
    Point dwR;
};

vector<double> process(const Rect* rects, size_t sz, function<double(Rect)> f) {
    vector<double> vector1;
    for (size_t i = 0; i < sz; i++)
    {
        vector1.push_back(f(rects[i]));
    }
    return vector1;
};

vector<double> process(const Rect* rects, size_t sz, double (*f)(Rect)) {
    vector<double> vector1;
    for (size_t i = 0; i < sz; i++)
    {
        vector1.push_back(f(rects[i]));
    }
    return vector1;
};

template <typename T>
void printVec(const vector<T>& v) {
    for (int i = 0; i < v.size(); ++i) {
        std::cout << v[i] << "\t";
    }
    std::cout << std::endl;
}

double diagonal(Rect r) {
    return sqrt(pow(r.upL.x - r.dwR.x ,2) + pow(r.upL.y - r.dwR.y, 2));
}

int main() {
    Rect rects[3] = { {{0, 4},{4, 1}}, {{-6, 3},{6, -2}}, {{-7, 4},{8, -4}} };
    double (*f)(Rect) = [](Rect r) -> double {return abs(r.dwR.x - r.upL.x) * abs(r.dwR.y - r.upL.y); };
    vector<double> result1 = process(rects, 3, f);
    printVec(result1);
    vector<double> result2 = process(rects, 3, diagonal);
    vector<double> result3 = process(rects, 3, &diagonal);
    printVec(result2);
}


