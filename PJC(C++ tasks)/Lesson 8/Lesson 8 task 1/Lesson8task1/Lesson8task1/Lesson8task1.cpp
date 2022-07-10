#include <iostream>
#include <functional>  // std::function
#include <utility>     // std::swap (not indispensable)

using namespace std;

void printArr(int arr[], size_t size) {
    cout << "{";
    for (size_t i = 0; i < size; i++)
    {
        cout << arr[i] << " ";
    }
    cout << "}" << endl;
}

void mysort(int arr[], size_t size, function<bool(int, int)> cmp) {

    for (size_t i = 0; i < size; i++)
    {
        int index = i;
        for (size_t j = 0; j < size; j++)
        {
            if (cmp(arr[index], arr[j])) {
                index = j;
            }
        }
        swap(arr[i], arr[index]);
    }
}
// ...

int main() {
    int a[]{ 3, 77, 21, 19, 2, 54, 28, 91 };
    size_t size = std::size(a);
    cout << "Normal (natural) order\n";
    mysort(a, size, [](int n, int m) -> bool { return n > m; });
    printArr(a, size);
    cout << "Natural order reversed\n";
    mysort(a, size, [](int n, int m) -> bool { return n < m; });
    printArr(a, size);
    std::cout << "By sum of digits, then natural\n";
    mysort(a, size, [](int a, int b) -> bool {
        int a_sum{}, b_sum{}, a_tmp = a, b_tmp = b;
        while (a_tmp > 0) {
            a_sum += a_tmp % 10;
            a_tmp /= 10;
        }

        while (b_tmp > 0) {
            b_sum += b_tmp % 10;
            b_tmp /= 10;
        }

        if (a_sum == b_sum)
            return a > b;
        else
            return a_sum > b_sum;
        });
    printArr(a, size);


    int mod{};
    auto byrem = [&mod](int a, int b) -> bool {
        if (a % mod == b % mod)
            return a < b;
        else
            return a % mod > b % mod;
    };
    for (int i : {3, 5, 7}) {
        mod = i;
        std::cout << "By remainder mod " << mod << ", then natural reversed\n";
        mysort(a, size, byrem);
        printArr(a, size);
    }
}