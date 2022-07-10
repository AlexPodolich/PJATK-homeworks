
#include <iostream>
#include <cmath>

using namespace std;

const double* aver(const double* arr, size_t size, double& average);

int main()
{
	double arr[] = { 3.06, 4.66, 2.22 ,3.11, 1, 2, -1.5 , 3.25, 5.5, 7.75, -0.25, 5.75};
	size_t size = sizeof(arr)/sizeof(arr[0]);
	double average = 0;
	const double* p = aver(arr, size, average);
	cout << *p << " " << average << endl;
}

const double* aver(const double* arr, size_t size, double& average) {
	double sum = 0;
	for (size_t i = 0; i < size; i++)
	{
		sum += arr[i];
	}

	average = sum / size;
	double p = arr[0];
	double raznica;
	if (average > arr[0]) {
		raznica = abs(average - arr[0]);
	}
	else {
		raznica = abs(arr[0] - average);
	}
	for (size_t i = 0; i < size; i++)
	{
		if (abs(arr[i] - average) < raznica) {
			if (average > arr[i]) {
				raznica = abs(average - arr[i]);
			}
			else {
				raznica = abs(arr[i] - average);
			}
			p = arr[i];
		}
	}

	return &p;
}
