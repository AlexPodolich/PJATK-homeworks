#include "Person.h"

Person::Person(const char* n) : name(strcpy(new char[strlen(n) + 1], n)) {}

Person::Person(const Person& os) {
    name = new char[strlen(os.name) + 1];
    strcpy(name, os.name);
}

Person& Person::operator=(const Person& os) {
    if (this == &os)
        return *this;
    delete name;
    name = new char[strlen(os.name) + 1];
    strcpy(name, os.name);
    return *this;
    //return *(new Person(os));
}

Person::~Person() {
    std::cout << "DEL " << name;
    delete name;
}

std::ostream& operator<<(std::ostream& str, const Person& os) {
    return str << os.name << " ";
}
