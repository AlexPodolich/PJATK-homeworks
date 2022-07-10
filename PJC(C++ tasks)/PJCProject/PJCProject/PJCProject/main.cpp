#include "PassManager.h"
using namespace std;
int main()
{
	srand(time(NULL));
	cout << "Welcome to the password manager" << endl;
	PassManager* passwordManager = new PassManager();
	passwordManager->askFile();
	delete passwordManager;
	return 0;
}