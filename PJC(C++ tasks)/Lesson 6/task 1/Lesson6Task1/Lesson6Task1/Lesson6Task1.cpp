
#include <iostream>
#include <string>
#include <functional>

using namespace std;

template <typename T>
int theLargestElem(const T* arr, size_t arrSize);

int main()
{
    int arr[] = { 10, 321, 45, 90 ,9890 };
    int arrSize = sizeof(arr) / sizeof(arr[0]);
    cout << "Index of largest elem of int arr is " << theLargestElem(arr, arrSize) << endl;
   
    double arr2[] = { 10.0, 21321.0, 45.0, 90.0 ,9890.0 };
    int arrSize2 = sizeof(arr2) / sizeof(arr2[0]);
    cout << "Index of largest elem of double arr is " << theLargestElem(arr2, arrSize2) << endl;

    string arr3[] = { "Ala", "Ela", "Ula", "Ola" };
    int arrSize3 = sizeof(arr3) / sizeof(arr3[0]);
    cout << "Index of largest elem of String arr is " << theLargestElem(arr3, arrSize3) << endl;

}

template <typename T>
int theLargestElem(const T* arr, size_t arrSize) {
    int indexOfLargestElem = 0;
    for (size_t i = 0; i < arrSize; i++)
    {
        if (arr[indexOfLargestElem] < arr[i]) {
            indexOfLargestElem = i;
        }
    }

    return indexOfLargestElem;
}




