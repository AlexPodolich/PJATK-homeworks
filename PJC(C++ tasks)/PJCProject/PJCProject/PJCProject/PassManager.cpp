#define _CRT_SECURE_NO_WARNINGS
#include "PassManager.h"

using namespace std;

void PassManager::askFile() {
    cout << "Write the name of the file with passwords that you want to open: ";
    string fileName;
    cin >> fileName;
    ifstream fin;
    fin.open(fileName);
    if (fin.is_open()) {
        cout << "file exists!" << endl;
        askPass(fin, fileName);
    }
    else {
        cout << "file doesn't exist!" << endl;
        cout << "Do you want to try to enter file name one more time or to create new file?\n1.Enter file name\n2.Create new file" << endl;
        fileNotExist();
    }

    fin.close();
}

void PassManager::askPass(ifstream& fin, string fileName) {
    fin.close();
    fin.open(fileName);
    cout << "Enter password for this file: " << endl;
    string shiftStr;
    string filePass;
    string userPass;
    cin >> userPass;
    getline(fin, shiftStr);
    int lineNum = 0;
    while (lineNum != 1) {
        getline(fin, filePass);
        lineNum++;
    }
    if (lineNum == 1) {
        int shift = stoi(shiftStr);
        filePass = decryptPass(shift, filePass);
    }
    else {
        cout << "An error has occurred!";
    }
    if (userPass == filePass) {
        cout << "Hello admin!" << endl;
        lastValidLogin(fin, fileName);
        menu(fileName);
    }
    else {
        cout << "Wrong password" << endl;
        cout << "Do you want to try one more time?\n1.Enter pass one more time\n2.Create new file" << endl;
        wrongPass(fin, fileName);
    }
    fin.close();
}

void PassManager::lastValidLogin(ifstream& fin, string fileName) {
    fin.close();
    fin.open(fileName);
    string newFileContent = "";
    string line;
    for (int i = 0; i < 2; i++) {
        getline(fin, line);
        newFileContent += line + '\n';
    }
    getline(fin, line);
    time_t now = time(0);
    string date_time = ctime(&now);
    newFileContent += date_time;
    while (getline(fin, line))
        newFileContent += line + '\n';
    fin.close();
    ofstream fout(fileName);
    fout << newFileContent;
}

void PassManager::wrongPass(ifstream& fin, string fileName) {
    fin.close();
    fin.open(fileName);
    int userChoice;
    cin >> userChoice;
    switch (userChoice)
    {
    case(1):
        askPass(fin, fileName);
        break;
    case(2):
        createFile();
        break;
    default:
        std::cout << "Enter 1 or 2" << endl;
        wrongPass(fin, fileName);
        break;
    }
    fin.close();
}

void PassManager::fileNotExist() {
    int userChoice;
    cin >> userChoice;
    switch (userChoice)
    {
    case(1):
        askFile();
        break;
    case(2):
        createFile();
        break;
    default:
        std::cout << "Enter 1 or 2" << endl;
        fileNotExist();
        break;
    }
}

void PassManager::createFile() {
    cout << "Enter name of the new file: " << endl;
    string fileName;
    cin >> fileName;
    ofstream fout;
    fout.open(fileName);
    cout << "Enter password for this file: " << endl;
    string filePass;
    cin >> filePass;
    if (!fout.is_open()) {
        cout << "Error while opening file!";
    }
    else {
        int shift = (rand() % 26) + 1;
        fout << shift << endl;
        fout << encryptPass(shift, filePass) << endl;
        auto currTime = time(nullptr);
        fout << put_time(localtime(&currTime), "%c %A") << endl;
        fout << "Existing categories:" << endl;
        fout << "Content:" << endl;
    }
    cout << "File successfully created!" << endl;
    fout.close();
    menu(fileName);
}

void PassManager::menu(string currFile) {
    cout << "Menu: " << endl;
    cout << "1.Add password record to file" << endl;
    cout << "2.Delete password record from file" << endl;
    cout << "3.Output all passwords from a given category" << endl;
    cout << "4.Sort passwords by login" << endl;
    cout << "5.Add category" << endl;
    cout << "6.Delete categories with all assigned records" << endl;
    cout << "7.Exit" << endl;
    cout << "Your choice:" << endl;
    int userChoice;
    cin >> userChoice;
    switch (userChoice)
    {
    case 1:
        addPassRecord(currFile);
        break;
    case 2:
        delPassRecord(currFile);
        break;
    case 3:
        selectPassFromCategory(currFile);
        break;
    case 4:
        sortByLogin(currFile);
        break;
    case 5:
        addCategory(currFile);
        break;
    case 6:
        delCategory(currFile);
        break;
    case 7:
        cout << "See you later!";
        exit(0);
    default:
        cout << "Enter 1-7!" << endl;
        menu(currFile);
        break;
    }
}

void PassManager::addPassRecord(string currFile) {
    ifstream fin;
    fin.open(currFile);
    string shiftStr;
    getline(fin, shiftStr);
    int shift = stoi(shiftStr);
    cout << "Creating of new password record" << endl;
    cout << "Enter name of password: " << endl;
    string newPassName;
    cin >> newPassName;
    cout << "Enter password: " << endl;
    string newPass;
    cin >> newPass;
    cout << "Choose category from existing: (or type menu)" << endl;
    string line;
    bool isCategory = false;
    vector<string> exiCategories;
    while (!fin.eof()) {
        getline(fin, line);
        if (line == "Existing categories:") {
            isCategory = true;
        }
        else if (line == "Content:") {
            isCategory = false;
        }
        if (isCategory == true) {
            cout << line << endl;
            exiCategories.push_back(line);
        }
    }
    string newCategory;
    bool isPresent;
    bool goMenu = false;
    do {
        cin >> newCategory;
        isPresent = find(exiCategories.begin(), exiCategories.end(), newCategory) != exiCategories.end();
        if (newCategory == "menu") {
            goMenu = true;
        }else if (isPresent == false) {
            cout << "Select existing category! or type menu" << endl;
        }
    } while (isPresent == false && goMenu == false);
    if (goMenu == true) {
        menu(currFile);
    }
    cout << "Enter login: " << endl;
    string newLogin;
    cin >> newLogin;
    string encryptedPass = encryptPass(shift, newPass);
    ofstream fout;
    fout.open(currFile, ofstream::app);
    fout << newPassName + " " + encryptedPass + " " + newCategory + " " + newLogin << endl;
    cout << "Record successfully added!" << endl;
    fout.close();
    fin.close();
    menu(currFile);
}

void PassManager::delPassRecord(string currFile) {
    ifstream fin;
    fin.open(currFile);
    ofstream fout;
    fout.open("tmp.txt", ofstream::out);
    cout << "Deleting of password record" << endl;
    cout << "Enter name of password: " << endl;
    string passName;
    cin >> passName;
    bool isContent = false;
    bool passIsFound = false;
    string line;
    string shiftStr;
    getline(fin, shiftStr);
    fout << shiftStr << endl;
    int shift = stoi(shiftStr);
    string recordName;
    string recordPass;
    int currLineNumber = 1;
    while (!fin.eof()) {
        getline(fin, line);
        if (line == "Content:") {
            isContent = true;
        }
        if (isContent == true) {
            recordName = line.substr(0, line.find(" "));
            if (passName != recordName) {
                fout << line << endl;
            }
            else {
                passIsFound = true;
                cout << "Enter password to delete this password record: " << endl;
                string password;
                cin >> password;
                string encriptedPass = encryptPass(shift, password);
                recordPass = line.substr(recordName.size() + 1, encriptedPass.size());
                if (encriptedPass != recordPass) {
                    fout << line << endl;
                    cout << "WRONG PASSWORD!" << endl;
                }
                else {
                    cout << "Record successfully deleted!" << endl;
                }
            }
        }
        else {
            fout << line << endl;
        }
    }
    if (!passIsFound) {
        cout << "No password found with " << passName << " name!" << endl;
    }

    fout.close();
    fin.close();
    const char* nameOfFile = currFile.c_str();
    remove(nameOfFile);
    rename("tmp.txt", nameOfFile);
    menu(currFile);
}

void PassManager::selectPassFromCategory(string currFile) {
    ifstream fin;
    fin.open(currFile);
    cout << "Enter name of category: " << endl;
    string userCategory;
    string recordCategory;
    string decryptedPass;
    string recordName;
    string shiftStr;
    getline(fin, shiftStr);
    int shift = stoi(shiftStr);
    cin >> userCategory;
    bool isCategory = false;
    bool isContent = false;
    bool isExist = false;
    string line;
    while (!fin.eof()) {
        getline(fin, line);
        if (line == "Existing categories:") {
            isCategory = true;
        }
        else if (line == "Content:") {
            isCategory = false;
            isContent = true;
        }
        if (isCategory == true) {
            if (line == userCategory) {
                isExist = true;
            }
        }
        else if (isContent == true) {
            if (line != "Content:") {
                int spaces = 0;
                decryptedPass = "";
                recordName = "";
                int numOfRepForFirst = 0;
                int numOfRepForSec = 0;
                for (size_t i = 0; i < line.size(); i++)
                {
                    if (line[i] == ' ') {
                        spaces++;
                    }
                    if (spaces == 2) {
                        recordCategory = line.substr(i + 1, userCategory.size());
                    }
                    if (spaces == 1 && numOfRepForFirst == 0) {
                        int j = i + 1;
                        while (line[j] != ' ') {
                            decryptedPass += line[j];
                            j++;
                        }
                        decryptedPass = decryptPass(shift, decryptedPass);
                        numOfRepForFirst++;
                    }
                    if (spaces == 0 && numOfRepForSec == 0) {
                        int j = i;
                        while (line[j] != ' ') {
                            recordName += line[j];
                            j++;
                        }
                        numOfRepForSec++;
                    }
                    if (recordCategory == userCategory) {
                        cout << "Name: " << recordName << " Password: " << decryptedPass << " Category: " << recordCategory << endl;
                    }
                }
            }
        }
    }
    if (isExist == false) {
        cout << "Category doesn't exist!" << endl;
    }
    fin.close();
    menu(currFile);
}

void PassManager::addCategory(string currFile) {
    ifstream fin;
    fin.open(currFile);
    ofstream fout;
    fout.open("tmp.txt", ofstream::out);
    cout << "Enter name of new category: " << endl;
    string newCategory;
    bool isPresent;
    string line;
    bool isCategory = false;
    vector<string> exiCategories;
    while (!fin.eof()) {
        getline(fin, line);
        if (line == "Existing categories:") {
            isCategory = true;
        }
        else if (line == "Content:") {
            do {
                cin >> newCategory;
                isPresent = !(find(exiCategories.begin(), exiCategories.end(), newCategory) != exiCategories.end());
                if (isPresent == false) {
                    cout << "This category already exists!" << endl;
                }
                else {
                    fout << newCategory << endl;
                    cout << "Category successfully added!" << endl;
                }
            } while (isPresent == false);
            isCategory = false;
        }
        else {
        }
        if (isCategory == true) {
            cout << line << endl;
            exiCategories.push_back(line);
        }
        fout << line << endl;
    }
    fout.close();
    fin.close();
    const char* nameOfFile = currFile.c_str();
    remove(nameOfFile);
    rename("tmp.txt", nameOfFile);
    menu(currFile);
}

void PassManager::delCategory(string currFile) {
    ifstream fin;
    fin.open(currFile);
    ofstream fout;
    fout.open("tmp.txt", ofstream::out);
    cout << "Deleting of category" << endl;
    cout << "Enter name of category: " << endl;
    string categoryName;
    cin >> categoryName;
    string line;
    string recordCategory;
    bool isCategoryFound = false;
    bool isDeleted = false;
    bool isCategory = false;
    bool isContent = false;
    while (!fin.eof()) {
        getline(fin, line);
        if (line == "Existing categories:") {
            isCategory = true;
        }
        else if (line == "Content:") {
            isContent = true;
        }
        if (isContent == true) {
            if (line != "Content:") {
                int spaces = 0;
                recordCategory = "";
                isDeleted = false;
                for (size_t i = 0; i < line.size(); i++)
                {
                    if (line[i] == ' ') {
                        spaces++;
                    }
                    if (spaces == 2) {
                        recordCategory = line.substr(i + 1, categoryName.size());
                    }
                    if (recordCategory == categoryName) {
                        isDeleted = true;
                        isCategoryFound = true;
                    }
                }
                if (!isDeleted) {
                    fout << line << endl;
                }
            }
            else {
                fout << "Content:" << endl;
            }
        }
        else if (isCategory == true) {
            recordCategory = line;
            if (categoryName != recordCategory) {
                fout << line << endl;
            }
            else {
                isCategoryFound = true;
            }
        }
        else {
            fout << line << endl;
        }
    }
    if (!isCategoryFound) {
        cout << "No category found with " << categoryName << " name!" << endl;
    }
    else {
        cout << "Records successfully deleted!" << endl;
    }
    fout.close();
    fin.close();
    const char* nameOfFile = currFile.c_str();
    remove(nameOfFile);
    rename("tmp.txt", nameOfFile);
    menu(currFile);
}

void PassManager::sortByLogin(string currFile) {
    ifstream fin;
    fin.open(currFile);
    cout << "Enter login: " << endl;
    string userLogin;
    cin >> userLogin;
    string shiftStr;
    string recordLogin;
    string decryptedPass;
    string recordName;
    getline(fin, shiftStr);
    int shift = stoi(shiftStr);
    bool isContent = false;
    bool isExist = false;
    string line;
    cout << "All paswords for login: " << userLogin << endl;
    while (!fin.eof()) {
        getline(fin, line);
        if (line == "Content:") {
            isContent = true;
        }
        if (isContent) {
            if (line != "Content:") {
                int spaces = 0;
                decryptedPass = "";
                recordName = "";
                int numOfRepForFirst = 0;
                int numOfRepForSec = 0;
                for (size_t i = 0; i < line.size(); i++)
                {
                    if (line[i] == ' ') {
                        spaces++;
                    }
                    if (spaces == 3) {
                        recordLogin = line.substr(i + 1, userLogin.size());
                    }
                    if (spaces == 1 && numOfRepForFirst == 0) {
                        int j = i + 1;
                        while (line[j] != ' ') {
                            decryptedPass += line[j];
                            j++;
                        }
                        decryptedPass = decryptPass(shift, decryptedPass);
                        numOfRepForFirst++;
                    }
                    if (spaces == 0 && numOfRepForSec == 0) {
                        int j = i;
                        while (line[j] != ' ') {
                            recordName += line[j];
                            j++;
                        }
                        numOfRepForSec++;
                    }
                    if (recordLogin == userLogin) {
                        isExist = true;
                        cout << "Name: " << recordName << " Password: " << decryptedPass << endl;
                    }
                }
            }
        }
    }
    if (isExist == false) {
        cout << "Login doesn't exist!" << endl;
    }
    fin.close();
    menu(currFile);
}