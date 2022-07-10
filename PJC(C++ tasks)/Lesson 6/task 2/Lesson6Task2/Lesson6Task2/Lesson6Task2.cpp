#include <iostream>
#include <string>

template<typename T>
T getMaxRecur(const T* arr, size_t size) {
    if (size == 1) {
        return arr[0];
    }
    else {
        T secondToLast = getMaxRecur(arr, size - 1);
        return arr[size - 1] > secondToLast ? arr[size - 1] : secondToLast;
    }
}

int main() {
    using std::cout; using std::endl; using std::string;

    double ad[]{ 2.5, 9.1, 4.5, -1.0 };
    cout << "ad: " << getMaxRecur(ad, 4) << "; ";

    int    ai[]{ 2, 11, 5, 1, 9 };
    cout << "ai: " << getMaxRecur(ai, 5) << "; ";

    string as[]{ "Ala", "Ela", "Ula", "Ola" };
    cout << "as: " << getMaxRecur(as, 4) << endl;
}