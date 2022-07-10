#include <iostream>
#include <string> // for tests
#include <ctime> // time
#include <utility> // std::swap may be useful

using namespace std;

template < typename T, typename FUN>
size_t part(T* arr, size_t size, FUN f) {
	size_t tmp = 0; //the number of elements that satisfy the condition f
	for (size_t i = 0; i < size; i++)
	{
		if (f(arr[i])) { // go inside the if statement when element of array with index i satisfy the condition
			//if (f(arr[0]) != true){
			//	swap(arr[0], arr[i]);//swaping with first element if first elem doesnt satisfy the condition
			//}
			//else {
			//	swap(arr[tmp], arr[i]);//swaping element with element with index tmp if first elem satisfy the condition
			//}

			swap(arr[tmp], arr[i]);//swaping element of index i with element with index tmp
			tmp++;//increasing number of elements that satisfy the condition f by 1
		}
	}

	return tmp;
};

template < typename T>
void printTab(const T* t, size_t size) {
	cout << "[";
	for (size_t i = 0; i < size; i++)
	{
		cout << t[i] << " ";
	}
	cout << "]";
	cout << '\n';
};

bool isEven(int e) { return e % 2 == 0; }
int main() {
	size_t ind = 0;
	//int a1[] = { 6,2,3,5,1,4 }; // arrays for cheking function part
	//int a1[] = { 1,3,5,2,6,4 };
	int a1[] = { 1,2,3,4,5,6 };
	ind = part(a1, 6, isEven);
	cout << "ind = " << ind << " ";
	printTab(a1, 6);
	int a2[] = { 1,2,3,4,5,6 };
	// lambda as argument: a predicate checking
	// if the given number is odd
	ind = part(a2, size(a2), [](int n) -> bool {return n % 2 == 1; });
	cout << "ind = " << ind << " ";
	printTab(a2, size(a2));
	
	string a3[] = { "Ala", "Ula", "Ela", "Ola", "Maja" };
	string mn = "Bea";
	string mx = "Sue";
	// lambda as argument: a predicate checking
	// if the given name is in the range [mn,mx]
	ind = part(a3, size(a3), [mn, mx](string n) -> bool {return n >= mn && n <= mx; });
	cout << "ind = " << ind << " ";
	printTab(a3, size(a3));
	
	constexpr size_t DIM = 500000;
	int* a4 = new int[DIM];
	srand(unsigned(time(0)));
	for (size_t i = 0; i < DIM; ++i) a4[i] = rand() % 21 + 1;
	// lambda as argument: a predicate checking
	// if the given number is divisible by 7
	ind = part(a4, DIM, [](int n) -> bool {return n % 7 == 0; });
	cout << "ind = " << ind <<endl;
	delete[] a4;
}