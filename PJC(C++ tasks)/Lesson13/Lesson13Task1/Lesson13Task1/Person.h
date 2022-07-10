#pragma once
#include <iostream>

class Person {
    char* name;
    friend class Couple;
public:
    explicit Person(const char* n);
    Person(const Person& os);
    Person& operator=(const Person& os);
    ~Person();
    friend std::ostream& operator<<(std::ostream& str,
        const Person& os);
};

