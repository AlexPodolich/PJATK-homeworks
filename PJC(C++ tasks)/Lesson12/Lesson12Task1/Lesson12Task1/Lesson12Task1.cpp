#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string.h>
#include <vector>

#include <stdio.h>


using namespace std;

class MechanicalVehicle {
    const char* nazwa;
    int wiek;
public:
    MechanicalVehicle(const char* nazwa, int wiek): nazwa(strcpy(new char[strlen(nazwa) + 1], nazwa)), wiek(wiek) {}

    virtual void show() const {
        std::cout << "nazwa: " << this->getNazwa() << ", wiek: " << this->getWiek();
    }

    const  char* getNazwa() const {
        return nazwa;
    }

    int getWiek() const {
        return wiek;
    }

    ~MechanicalVehicle() {
        delete nazwa;
    }
};

class Car : public virtual MechanicalVehicle {
    const char* kolor;
    int pojemnosc;
public:
    Car(const char* nazwa, int wiek, const char* kolor, int pojemnosc)
        : MechanicalVehicle(nazwa, wiek), kolor(strcpy(new char[strlen(kolor) + 1], kolor)), pojemnosc(pojemnosc) {}

    void show() const {
        MechanicalVehicle::show();
        std::cout << ", kolor: " << this->getKolor() << ", pojemnosc: " << this->getPojemnosc();
    }

    const  char* getKolor() const {
        return kolor;
    }

    int getPojemnosc() const {
        return pojemnosc;
    }

    ~Car() {
        delete kolor;
    }
};

class Boat : public virtual MechanicalVehicle {
    int wypornosc;
    const  char* typ;
public:
    Boat(const char* nazwa, int wiek, int wypornosc, const char* typ)
        : MechanicalVehicle(nazwa, wiek), wypornosc(wypornosc), typ(strcpy(new char[strlen(typ) + 1], typ)) {}

    void show() const {
        MechanicalVehicle::show();
        std::cout << ", wypornosc: " << this->getWypornosc() << ", typ: " << this->getTyp();
    }

    int getWypornosc() const {
        return wypornosc;
    }

    const char* getTyp() const {
        return typ;
    }

    ~Boat() {
        delete typ;
    }
};

class Amfibia : public Car, public Boat {
public:
    Amfibia(const char* nazwa, int wiek, const char* kolor, int pojemnosc, int wypornosc, const char* typ)
        : MechanicalVehicle(nazwa, wiek), Car(nazwa, wiek, kolor, pojemnosc), Boat(nazwa, wiek, wypornosc, typ) {}

    void show() const {
        MechanicalVehicle::show();
        std::cout << ", kolor: " << this->getKolor() << ", pojemnosc: "
            << this->getPojemnosc() << ", wypornosc: "
            << this->getWypornosc() << ", typ: " << this->getTyp();;
    }

};

template<class T>
class Garage {
    std::vector<T*> list;
public:
    void add(T& pojazd) {
        this->list.push_back(&pojazd);
    }
    void show() {
        for (T* v : this->list) {
            v->show();
            std::cout << std::endl;
        }
    }
};

int main() {


    Car samochod{ "Samochod1", 2, "GREEN", 5 };
    Boat lodz{ "Lodz1", 3, 10, "Typ1" };

    Amfibia a1{ "Nazwa1", 1, "RED", 6, 1, "Typ1" };
    Amfibia a2{ "Nazwa2", 3, "GREEN", 2, 10, "Typ2" };
    Amfibia a3{ "Nazwa3", 5, "BLUE", 4, 8, "Typ3" };

    Garage<MechanicalVehicle> garage;
    garage.add(samochod);
    garage.add(lodz);
    garage.add(a1);
    garage.add(a2);
    garage.add(a3);

    garage.show();

    return 0;
}