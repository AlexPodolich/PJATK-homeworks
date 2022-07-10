#include <cmath>
#include <iostream>
#include <functional>
#include <vector>
using namespace std;

template <typename T, typename FunType>
vector<T> filter(const vector<T>& v, FunType p) {
	vector<T> result;

	for (size_t i = 0; i < v.size(); i++)
	{
		if (p(v[i])) {
			result.push_back(v[i]);
		}
	}

	return result;
}

template <typename T, typename FunType1, typename FunType2>
vector<T> transfilt(vector<T>& v, FunType1 t, FunType2 p) {
	std::vector<T> result;
	for (int i = 0; i < v.size(); ++i) {
		v[i] = t(v[i]);
		if (p(v[i]))
			result.push_back(v[i]);
	}
	return result;
}

template <typename T>
void printVec(const vector<T>& v) {
	for (size_t i = 0; i < v.size(); i++)
	{
		cout << v[i] << "\t";
	}
	cout << endl;
}

int main() {
	vector<int> v{ 1, -3, 4, -2, 6, -8, 5 };
	printVec(v);
	vector<int> r = filter(v, [](int n) -> bool {return n % 2 == 0; });
	printVec(r);
	vector<int> s = filter(v, [](int n) -> bool {return n > 0; });
	printVec(s);
	vector<double> w{ 1.5, -3.1, 4.0, -2.0, 6.3 };
	printVec(w);


	double mn = -0.5, mx = 0.5;
	vector<double> d =
		transfilt(w, [](double n) -> double {return sin(n); }, [mn, mx](double n) -> bool {return n >= mn && n <= mx; });
	printVec(w);
	printVec(d);
}