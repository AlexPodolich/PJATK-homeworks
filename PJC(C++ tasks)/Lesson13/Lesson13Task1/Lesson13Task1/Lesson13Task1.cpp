#include <iostream>
#include "Person.h"
#include "Couple.h"

int main(void) {
    using std::cout; using std::endl;
    Person* pjohn = new Person("John"),
        * pjane = new Person("Jane");
    Person mary("Mary"), mark("Mark");
    Couple* pcouple1 = new Couple(mary, *pjohn);
    Couple couple2(*pjane, mark);
    delete pjohn;
    delete pjane;
    cout << *pcouple1 << endl;
    cout << couple2 << endl;
    couple2 = *pcouple1;
    delete pcouple1;
    cout << couple2 << endl;
}
