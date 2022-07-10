#include <iostream>
#include <algorithm> // min
#include <fstream>

using namespace std;

int main()
{

    constexpr size_t N = 5;
    int greatest[N];
    std::ifstream inpfile("D:\\PJATK\\PJC\\Lesson 5\\task 1\\task 1\\NGreatest.txt");
    size_t count{};  // ile liczb zostalo wczytanych
    int n{};         // 'n' to ostatnio wczytana liczba
    size_t ile{};    // ile liczb zostalo umieszczonych
    // w tablicy 'greatest'

    // byc moze pomocnicze zmienne, ale
    // nie tablice ani zadne inne kolekcje!
    // ...

    while (inpfile >> n) {
        ++count;
        bool different = false;
        size_t minIndex{};
        for (int i = 0; i < ile && !different; ++i) {
            if (greatest[i] == n)
                different = true;
            if (greatest[minIndex] > greatest[i])
                minIndex = i;
        }
        if (!different) {
            if (ile < N)
                greatest[ile++] = n;
            else
                if (greatest[minIndex] < n)
                    greatest[minIndex] = n;
        }

    }

    // numData - liczba uzyskanych elementow
    // w 'greatest', moze byc mniejsza od N
    size_t numData = std::min(ile, N);
    std::cout << count << " numbers read. Greatest "
        << numData << " different are: ";
    for (size_t i = 0; i < numData; ++i)
        std::cout << greatest[i] << " ";
    std::cout << "\n";
}

