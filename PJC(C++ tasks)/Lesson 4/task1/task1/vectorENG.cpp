#include <iostream>
#include <vector>
#include <string>

using namespace std;

void showTab(int tab[], size_t size);
void showVec(vector<int> tab);


int main() {
    //Creation and pass to a method
	{
		cout << "Creation and pass to a method" << endl;
		cout << "//////////////////////////////////" << endl;

		vector<int> vec;

		for (int i = 0; i < 10; i++) {
			vec.push_back(i);
		}

		showVec(vec);

		cout << "first = " << vec.front() << " last = " << vec.back();

		cout << endl;

		//LOOKOUT for strings
		vector<string> vecS;
		vecS.push_back("test");
		cout << "vecS.size()= " << vecS.size() << endl;
	}

	//From vector to array
	{
		cout << "From vector to array" << endl;
		cout << "//////////////////////////////////" << endl;

		vector<int> vec = { 1,2,3 };
		//showTab(vec, vec.size()); //Vector is not an array
		showTab(vec.data(), vec.size()); //vec.data returns pointer to first element after thanks to that we can treat it as array
	}

	//Vector clear
	{
		cout << "Vector clear" << endl;
		cout << "//////////////////////////////////" << endl;
		vector<int> vec = { 1,2,3 };
		cout << "Before clear" << endl;
		showVec(vec);
		cout << "size =" << vec.size() << endl;
		vec.clear();
		cout << "After clear" << endl;
		showVec(vec);
		cout << "size =" << vec.size() << endl;
	}

}

void showTab(int tab[], size_t size) {
	for (int i = 0; i < size; i++) {
		cout << tab[i];
	}
	cout << endl;
}

void showVec(vector<int> tab) {
	for (int i = 0; i < tab.size(); i++) {
		cout << tab[i];
	}
	cout << endl;
}