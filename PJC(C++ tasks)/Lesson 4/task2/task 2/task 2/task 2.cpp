#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

template<typename T> std::vector<size_t> smallSum3(std::vector<T>& a) {

    vector<size_t> result;

    size_t b1 = a[0];
    size_t b2 = a[1];
    size_t b3 = a[2];

    for (size_t i = 0; i < a.size(); i++) {
        for (size_t j = 0; j < a.size(); j++) {
            for (size_t k = 0; k < a.size(); k++) {

                if ((i != j) && (j != k) && (i != k)) {
                    T currentBest = abs(a[b1] + a[b2] + a[b3]);
                    T currentScore = abs(a[i] + a[j] + a[k]);

                    if (currentBest > currentScore) {
                        b1 = i;
                        b2 = j;
                        b3 = k;
                    }
                }

            }
        }
    }

    result.push_back(b1);
    result.push_back(b2);
    result.push_back(b3);

    return result;


}

int main() {
    std::vector<int> a{ 2,-13,3,6,4,5,-14,1,-15 };
    auto r = smallSum3(a);

    std::printf("Sum=%d for elements ""a[%u]=%d, a[%u]=%d, a[%u]=%d\n", a[r[0]] + a[r[1]] + a[r[2]], r[0], a[r[0]], r[1], a[r[1]], r[2], a[r[2]]);

    system("pause");
}