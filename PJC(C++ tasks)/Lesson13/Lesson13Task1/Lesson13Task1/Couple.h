#pragma once
#include <iostream>
#include "Person.h"

class Couple {
    Person* wife, * husband;
public:
    Couple(const Person& she, const Person& he);
    Couple(const Couple& other);
    Couple& operator=(const Couple& other);
    ~Couple();
    friend std::ostream& operator<<(std::ostream& str,
        const Couple& p);

};



