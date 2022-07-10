#pragma once
#include <iostream>
#include "Crypting.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <ctime>
#include <vector>
using namespace std;
class PassManager {
public:
	void askFile();
	void askPass(ifstream& fin, string fileName);
	void wrongPass(ifstream& fin, string fileName);
	void createFile();
	void fileNotExist();
	void lastValidLogin(ifstream& fin, string fileName);
	void menu(string currFile);
	void addPassRecord(string currFile);
	void delPassRecord(string currFile);
	void selectPassFromCategory(string currFile);
	void addCategory(string currFile);
	void delCategory(string currFile);
	void sortByLogin(string currFile);
};