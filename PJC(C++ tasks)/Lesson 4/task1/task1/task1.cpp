#include <iostream>
using namespace std;

size_t length(const char* cstr);

void clean(char* s) {
    size_t tabLength = length(s);

    size_t index{};


    //Front
    while (index <= tabLength && s[index] == ' ') {
        index++;
    }
    memcpy(s, s + index, tabLength + 2 - index); //+1 index +1 NUL
    tabLength -= index;
    index = 0;

    //Mid
    while (index <= tabLength) {
        if (s[index] == ' ') {
            size_t spaces = 1;
            size_t start = index;
            while (++index <= tabLength && s[index] == ' ') {
                spaces++;
            }
            if (spaces != 1) {
                memcpy(s + start, s + index - 1, tabLength + 2 - index);
                tabLength -= spaces - 1;
            }
        }
        index++;
    }

    //END
    index = tabLength;
    while (s[--index] == ' ' && index >= 0);
    memcpy(s + index + 1, s + tabLength, 1);  //s[index+1] = '/0/;

}

int main() {
    char n1[] = " a   bc def   ghijk ";
    cout << "Before: >" << n1 << "<" << endl;
    clean(n1);
    cout << " After: >" << n1 << "<" << endl;

    char n2[] = "  a   bc def    ghijk    ";
    cout << "Before: >" << n2 << "<" << endl;
    clean(n2);
    cout << " After: >" << n2 << "<" << endl;

    char n3[] = "   ";
    cout << "Before: >" << n3 << "<" << endl;
    clean(n3);
    cout << " After: >" << n3 << "<" << endl;

    char n4[] = "  x  ";
    cout << "Before: >" << n4 << "<" << endl;
    clean(n4);
    cout << " After: >" << n4 << "<" << endl;

    char n5[] = "y   ";
    cout << "Before: >" << n5 << "<" << endl;
    clean(n5);
    cout << " After: >" << n5 << "<" << endl;

    char n6[] = "    z";
    cout << "Before: >" << n6 << "<" << endl;
    clean(n6);
    cout << " After: >" << n6 << "<" << endl;

    char n7[] = "a         b";
    cout << "Before: >" << n7 << "<" << endl;
    clean(n7);
    cout << " After: >" << n7 << "<" << endl;
}

size_t length(const char* cstr) {

    size_t i = 0;

    while (cstr[i] != '\0')
    {
        i++;
    }

    return i;
}