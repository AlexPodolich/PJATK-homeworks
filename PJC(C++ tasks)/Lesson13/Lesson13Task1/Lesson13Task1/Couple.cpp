#include "Couple.h"

Couple::Couple(const Person& she, const Person& he) {
    wife->name = she.name;
    husband->name = he.name;
}

Couple::Couple(const Couple& other) {

}

Couple& Couple::operator=(const Couple& other) {
    if (this == &other)
        return *this;
    delete wife, husband;

    return *this;
    //return  *(new Couple(other));
}

Couple::~Couple() {
    std::cout << "DEL " << wife << " " << husband;
    delete wife;
    delete husband;
}

std::ostream& operator<<(std::ostream& str, const Couple& p) {
    return str << "he " << p.husband << ", she" << p.wife;
}
