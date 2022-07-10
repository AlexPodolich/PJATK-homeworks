#include "Crypting.h"
namespace possibleSymbols {
    const char arr[62] = { '1', '2', '3', '4', '5','6','7','8','9','0',
                       'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                       'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
}

using namespace possibleSymbols;
string encryptPass(int shift, string str)
{
    string result = "";

    for (int i = 0; i < str.length(); i++)
    {
        for (int j = 0; j < 62; j++)
        {
            if (str[i] == arr[j]) {
                if (j + shift >= 62) {
                    result += arr[j + shift - 62];
                }
                else {
                    result += arr[j + shift];
                }
            }
        }
    }

	return result;
}

string decryptPass(int shift, string str)
{
    string result = "";

    for (int i = 0; i < str.length(); i++)
    {
        for (int j = 0; j <= 62; j++)
        {
            if (str[i] == arr[j]) {
                if (j - shift < 0) {
                    result += arr[j - shift + 62];
                }
                else {
                    result += arr[j - shift];
                }
            }
        }
    }

    return result;
}