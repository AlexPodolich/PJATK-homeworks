#include <iostream>
using namespace std;

int length(const char* s);
bool isLetter(char c);
void printLetterSingleOccurrence(const char* s1, const char* s2);
void printLetterOccurrenceInBoth(const char* s1, const char* s2);
void printLetterInFirstAndNotSecond(const char* s1, const char* s2);

void write_results(const char* s1, const char* s2) {
    printLetterSingleOccurrence(s1, s2);
    printLetterOccurrenceInBoth(s1, s2);
    printLetterInFirstAndNotSecond(s1, s2);
}



int main() {
    char s1[] = "To be, or not to be, that is the question:";
    char s2[] = "Whether 'tis nobler in the mind to suffer";
    write_results(s1, s2);
}

int length(const char* s) {

    size_t i = 0;
    while (s[i] != '\0') {
        i++;
    }
    return i;
}

bool isLetter(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
}

void printLetterSingleOccurrence(const char* s1, const char* s2) {
    for (int i = 0; i < length(s1); ++i) {
        bool notSeen = true;
        if (isLetter(s1[i])) {
            for (int j = 0; j < i && notSeen; ++j) {
                if (toupper(s1[i]) == toupper(s1[j]))
                    notSeen = false;
            }
            if (notSeen)
                cout << (char)toupper(s1[i]) << " ";
        }
    }
    for (int i = 0; i < length(s2); ++i) {
        bool notSeen = true;
        if (isLetter(s2[i])) {
            for (int j = 0; j < length(s1) && notSeen; ++j) {
                if (toupper(s2[i]) == toupper(s1[j]))
                    notSeen = false;
            }
            for (int j = 0; j < i && notSeen; ++j) {
                if (toupper(s2[i]) == toupper(s2[j]))
                    notSeen = false;
            }
            if (notSeen)
                cout << (char)toupper(s2[i]) << " ";
        }
    }
    cout << endl;
}

void printLetterOccurrenceInBoth(const char* s1, const char* s2) {
    for (int i = 0; i < length(s1); ++i) {
        bool notSeen = true;
        if (isLetter(s1[i])) {
            for (int j = 0; j < i && notSeen; ++j) {
                if (toupper(s1[i]) == toupper(s1[j]))
                    notSeen = false;
            }
            if (notSeen) {
                bool repeat = false;
                for (int j = 0; j < length(s2) && !repeat; ++j) {
                    if (toupper(s1[i]) == toupper(s2[j]))
                        repeat = true;
                }
                if (repeat)
                    cout << (char)toupper(s1[i]) << " ";
            }
        }
    }
    cout << endl;
}

void printLetterInFirstAndNotSecond(const char* s1, const char* s2) {
    for (int i = 0; i < length(s1); ++i) {
        bool notSeen = true;
        if (isLetter(s1[i])) {
            for (int j = 0; j < i && notSeen; ++j) {
                if (toupper(s1[i]) == toupper(s1[j]))
                    notSeen = false;
            }
            if (notSeen) {
                bool repeat = false;
                for (int j = 0; j < length(s2) && !repeat; ++j) {
                    if (toupper(s1[i]) == toupper(s2[j]))
                        repeat = true;
                }
                if (!repeat)
                    cout << (char)toupper(s1[i]) << " ";
            }
        }
    }
    cout << endl;
}


