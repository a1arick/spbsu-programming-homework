#include <iostream>
#include <math.h>
#include <iomanip>

using namespace std;

const int byteSize = 8;
const int doubleSize = 64;
const int numOfBytes = 8;
const int exponentAddition = 1023;
const int exponentSize = 11;
const int mantissaSize = 52;
const int maxPrecision = 100;

int exp(int number, int exponent) {
    if (exponent == 0)
        return 1;
    if (exponent % 2 == 0)
        return exp(number * number, exponent / 2);
    return number * exp(number, exponent - 1);
}

int* getBinaryCode(double &number) {
    int *code = new int[doubleSize];
    int mask;
    char *bytes = (char*)&number;

    for (int i = numOfBytes - 1; i >= 0; --i) 	{
        mask = 0x80;
        for (int j = 0; j < byteSize; ++j) 		{
            if (mask & bytes[i])
                code[(numOfBytes - i - 1) * byteSize + j] = 1;
            else
                code[(numOfBytes - i - 1) * byteSize + j] = 0;
            mask >>= 1;
        }
    }

    return code;
}

int getExponent(int *binaryCode) {
    int offsetExponent = 0;
    for (int i = 0; i < exponentSize; ++i) 	{
        offsetExponent += binaryCode[1 + i] * exp(2, exponentSize - i - 1);
    }
    return offsetExponent - exponentAddition;
}

double getMantissa(int *binaryCode) {
    double mantissa = 1;
    for (int i = 1; i <= mantissaSize; ++i) 	{
        mantissa += binaryCode[11 + i] * pow(2, -i);
    }
    return mantissa;
}

int main() {
    double number = 0;
    cout << "Enter a number: " << endl;
    cin >> number;

    int *binaryCode = getBinaryCode(number);

    int exponent = getExponent(binaryCode);
    double mantissa = getMantissa(binaryCode);

    cout << "Number in E-notation: " << endl;
    if (binaryCode[0] != 0)
        cout << '-';

    cout << setprecision(maxPrecision) << mantissa << "*2^" << exponent;
    
    return 0;
}