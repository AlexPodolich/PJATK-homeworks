#include <iostream>
using namespace std;

size_t length(const char* cstr);

int main(){
    char n1[] = "1234567";
    cout << "Before: >" << n1 << "<" << " length = " << length(n1) << endl;
    char copy [0];
    cout<< "CHAR_BIT = " << CHAR_BIT <<endl;
    memcpy(copy, n1, length(n1)+1); //overflow
    cout << " After: >" << copy << "<" << " length = " << length(copy) << endl;
}

size_t length(const char* cstr) {

    size_t i = 0;

    while (cstr[i] != '\0')
    {
        i++;
    }

    return i;
}
