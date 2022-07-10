#include <iostream>
#define ENG
//#define POL

using namespace std;

bool isPrime(int n);
void printDivisors(int n);
int numDivisors(int n);
int gcd(int m, int n);
int phi(int n);

int main()
{
    // old task 3
    #ifndef ENG
    #ifndef POL
    #error "Choose language"
    #endif
    #endif
    #ifdef ENG
    #ifdef POL
    #error "Choose only one lanuage"
    #endif
    #endif
        int number = 1;
        int maxSumOfDigits = 0;
        int sumOfDigits = 0;
        int userNum = 0;
        int maxNumber = 0;
        while (number != 0) {
    #ifdef ENG
            cout << "Enter a natural number(0 if done): " << endl;
    #endif
    #ifdef POL
            cout << "Wpisz liczbe naturalna (0 jesli jest zrobione): " << endl;
    #endif
            cin >> number;
            userNum = number;
            int ostacha;
            while (number > 0)
            {
                ostacha = number % 10;
                sumOfDigits = sumOfDigits + ostacha;
                number = number / 10;
            }
            number = userNum;
            if (maxSumOfDigits < sumOfDigits) {
                maxSumOfDigits = sumOfDigits;
                maxNumber = number;
            }
            sumOfDigits = 0;
        }
    #ifdef ENG
        cout << "Max sum of digits was " << maxSumOfDigits << " for " << maxNumber;
    #endif
    #ifdef POL
        cout << "Maksymalna suma cyfr wynosila " << maxSumOfDigits << " dla " << maxNumber;
    #endif

   //task1 
        int userNum;
        int lastUserNum;
        int inputCounter = 0;
        int maxCounter = 0;
        int maxVariable;
        bool inputNotZero = false;

        cout << "Enter a number: " << endl;
        cin >> userNum;
        if (userNum == 0) {
            cout << "Longest sequence: 1 time 0" << endl;
            return 0;
        }
        else {
            lastUserNum = userNum;
            inputNotZero = true;
            inputCounter++;
        }

        do {
            cin >> userNum;
            if (userNum == 0) {
                inputNotZero = false;
                if (inputCounter > maxCounter) {
                    maxCounter = inputCounter;
                    maxVariable = lastUserNum;
                }
            }
            else if (userNum == lastUserNum) {
                inputCounter++;
            }
            else {
                if (inputCounter > maxCounter) {
                    maxCounter = inputCounter;
                    maxVariable = lastUserNum;
                }
                lastUserNum = userNum;
                inputCounter = 1;
            }
        } while (inputNotZero);

        cout << "Longest sequence: " << maxCounter << " times " << maxVariable << endl;
        
        //task 2
        int userNum;
        int maxCounter = 1;
        int maxVariable;
        int minCounter = 1;
        int minVariable;
        bool inputNotZero = true;

        cout << "Enter a number: " << endl;
        cin >> userNum;
        if (userNum == 0)
        {
            cout << "Min: 0 time 1" << endl;
            cout << "Max: 0 time 1" << endl;
        }
        else
        {
            maxVariable = userNum;
            minVariable = userNum;
        }

        do {
            cout << "Enter a number: " << endl;
            cin >> userNum;

            if (userNum == maxVariable)
                maxCounter++;

            if (userNum == minVariable)
                minCounter++;

            if (userNum > maxVariable)
            {
                maxVariable = userNum;
                maxCounter = 1;
            }

            if (userNum < minVariable)
            {
                minVariable = userNum;
                minCounter = 1;
            }

            if (userNum == 0)
                inputNotZero = false;

        } while (inputNotZero);


        cout << "Min: " << minVariable << " time " << minCounter << endl;
        cout << "Max: " << maxVariable << " time " << maxCounter << endl;

        //task 3
        int n;
        cout << "Enter number n: " << endl;
        cin >> n;
        if (isPrime(n) == true) {
            cout << "Number is prime" << endl;
        }
        else {
            cout << "Number isn't prime" << endl;
        }
        printDivisors(n);
        numDivisors(n);
        int m;
        cout << "Enter number m: " << endl;
        cin >> m;
        cout << "GCD =  " << gcd(m, n) << endl;
        cout << "PHI =  " << phi(n) << endl;
}

bool isPrime(int n)
{
    double nSqrt = sqrt(n);
    for (int i = 2; i < nSqrt; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
  
}

void printDivisors(int n) {
    cout << "All divisors for " << n << ": " << endl;
    for (int i = 1; i <= n; i++)
    {
        if (n % i == 0) {
            cout << i << endl;
        }
    }
}

int numDivisors(int n) {
    int numDivisors = 0;
    for (int i = 1; i <= n; i++)
    {
        if (n % i == 0) {
            numDivisors++;
        }
    }
    cout << "Number divisors for " << n << ": " << numDivisors << endl;
    return numDivisors;
}

int gcd(int m, int n) {
    while (n != m) {
        if (n > m) {
            n -= m;
        }
        else {
            m -= n;
        }
    }
    return n;
}

int phi(int n) {
    int primes[9] = { 2, 3, 5, 7, 11, 13, 17, 19 , 23 };
    int usedPrimes[9] = { 0, 0, 0, 0, 0, 0, 0, 0 };
    int i = 0;


    while (n != 1) {
        if (n % primes[i] == 0 && i < 9) {
            n = n / primes[i];
            usedPrimes[i]++;
        }
        else {
            i++;
        }

    }

    int result = 1;

    for (int i = 0; i < 9; i++) {
        if (usedPrimes[i] != 0) {
            if (usedPrimes[i] == 1) {
                result *= primes[i] - 1;
            }
            else {
                result *= (pow(primes[i], usedPrimes[i]) - pow(primes[i], (usedPrimes[i] - 1)));
            }
        }
    }

    return result;
}


