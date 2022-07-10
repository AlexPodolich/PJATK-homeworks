#include <iostream>

int gcdRec(int a, int b);
int sumDigits(int n);
int numDigits(int n);
void printOddEven(int n);
void hailstone(int n);

int main() {
    std::cout << "gcdRec(12, 42) = " <<
        gcdRec(12, 42) << std::endl
        << "gcdRec(12, 25) = " <<
        gcdRec(12, 25) << std::endl;
    std::cout << "sumDigits(123) = " <<
        sumDigits(123) << std::endl
        << "sumDigits(971) = " <<
        sumDigits(971) << std::endl;
    std::cout << "numDigits(12345) = " <<
        numDigits(12345) << std::endl
        << "numDigits(971) = " <<
        numDigits(971) << std::endl;
    std::cout << "printOddEven(15): ";
    printOddEven(15);
    std::cout << std::endl;
    std::cout << "printOddEven(14): ";
    printOddEven(14);
    std::cout << std::endl;
    std::cout << "hailstone(13): ";
    hailstone(13);
    std::cout << std::endl;
}

int gcdRec(int a, int b) {
    return b == 0 ? a : gcdRec(b, a % b);
}

int sumDigits(int n) {
    if (n == 0)
        return 0;
    return (n % 10 + sumDigits(n / 10));
}

int numDigits(int n) {
    if (n < 10)
        return 1;
    return 1 + numDigits(n / 10);
}

void printOddEven(int n) {
    if (n == 2 || n == 1)
        std::cout << n << " ";
    else {
        printOddEven(n - 2);
        std::cout << n << " ";
    }
}

void hailstone(int n) {
    if (n == 1)
        std::cout << n << " ";
    else {
        std::cout << n << " ";
        if (n % 2 != 0) {
            n = 3 * n + 1;
        }
        else {
            n = n / 2;
        }
        hailstone(n);
    }
}