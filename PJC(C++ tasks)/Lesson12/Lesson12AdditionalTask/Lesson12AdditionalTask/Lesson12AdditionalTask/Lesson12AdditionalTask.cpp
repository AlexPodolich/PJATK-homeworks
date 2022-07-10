#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
#include <cctype>

using namespace std;

class String {
	friend String operator+(const String& str1, const String& str2);
	friend String operator+(const String& str1, const char str2[]);
	friend String operator+(const char str1[], const String& str2);
	friend bool operator==(const String& str1, const String& str2);
	friend bool operator!=(const String& str1, const String& str2);
	friend ostream& operator<<(ostream& stream, const String& str);
	char* string = nullptr;
public:
	String(){
		string = new char[1];
		string[0] = '\0';
	}

	String(const char* newValue) {
		string = new char[strlen(newValue) + 1];
		strcpy(string, newValue);
	}

	String(const String& sourceValue) {
		string = new char[strlen(sourceValue.string) + 1];
		strcpy(string, sourceValue.string);
	}

	String(String&& sourceValue){
		string = sourceValue.string;
		sourceValue.string = nullptr;
	}

	~String() {
		delete string;
	}

	int length() {
		return strlen(string);
	}

	String toUpper() {
		for (size_t i = 0; i < strlen(string); i++)
		{
			string[i] = toupper(string[i]);
		}
		return string;
	};

	String toLower() {
		for (size_t i = 0; i < strlen(string); i++)
		{
			string[i] = tolower(string[i]);
		}
		return string;
	};

	String& operator=(const String& str) {
		if (this == &str)
			return *this;
		delete string;
		string = new char[strlen(str.string) + 1];
		strcpy(string, str.string);
		return *this;
	};
};

String operator+(const String& str1, const String& str2)
{
	int length = strlen(str1.string) + strlen(str2.string);

	char* buff = new char[length + 1];

	strcpy(buff, str1.string);
	strcat(buff, str2.string);

	String temp(buff);

	return temp;
	delete buff;
}

String operator+(const String& str1, const char str2[])
{
	int length = strlen(str1.string) + strlen(str2);

	char* buff = new char[length + 1];

	strcpy(buff, str1.string);
	strcat(buff, str2);

	String temp(buff);

	return temp;
	delete buff;
}

String operator+(const char str1[], const String& str2)
{
	int length = strlen(str1) + strlen(str2.string);

	char* buff = new char[length + 1];

	strcpy(buff, str1);
	strcat(buff, str2.string);

	String temp(buff);

	return temp;
	delete buff;
}

bool operator==(const String& str1, const String& str2)
{
	if (strlen(str1.string) != strlen(str2.string))
		return false;
	for (int i = 0; i < strlen(str1.string); ++i)
		if (str1.string[i] != str2.string[i])
			return false;
	return true;
}

bool operator!=(const String& str1, const String& str2)
{
	if (strlen(str1.string) != strlen(str2.string))
		return true;
	for (int i = 0; i < strlen(str1.string); ++i)
		if (str1.string[i] != str2.string[i])
			return true;
	return false;
}

ostream& operator<<(ostream& stream, const String& str) {
	return stream << str.string << " ";
}


int main() {
	String s = ("To " + String("be ") + "or not to be").toUpper() + "!";
	std::cout << s.toUpper() << std::endl;
	String a("a"), A = "A";
	std::cout << std::boolalpha
		<< ("a" == a && "a" != A && a != A &&
			a == "a" && A != "a" && a == A.toLower() &&
			s.length() == 19) << std::endl;
	a = String(a).toUpper() + "li" + String("ce");
	std::cout << a << ", len=" << a.length() << std::endl;
}