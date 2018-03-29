#include <iostream>
using namespace std;

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

int main()
{
    enum {start, afterSign, integralPart, afterDot, digitAfterDot, afterE, afterESign, digitAfterE, fail} status;
    status = start;

    char current = cin.get();

    while (current != '\n')
    {
        switch (status)
        {
            case start:
                if (current == '+' || current == '-')
                    status = afterSign;
                else if (isDigit(current))
                    status = integralPart;
                else
                    status = fail;
                break;
            case afterSign:
                if (isDigit(current))
                    status = integralPart;
                else
                    status = fail;
                break;
            case integralPart:
                if (isDigit(current))
                    status = integralPart;
                else if (current == '.')
                    status = afterDot;
                else if (current == 'E')
                    status = afterE;
                else
                    status = fail;
                break;
            case afterDot:
                if (isDigit(current))
                    status = digitAfterDot;
                else
                    status = fail;
                break;
            case digitAfterDot:
                if (isDigit(current))
                    status = digitAfterDot;
                else if (current == 'E')
                    status = afterE;
                else
                    status = fail;
                break;
            case afterE:
                if (current == '+' || current == '-')
                    status = afterESign;
                else if (isDigit(current))
                    status = digitAfterE;
                else
                    status = fail;
                break;
            case afterESign:
                if (isDigit(current))
                    status = digitAfterE;
                else
                    status = fail;
                break;
            case digitAfterE:
                if (!isDigit(current))
                    status = fail;
                break;
        }
        current = cin.get();
    }

    if (status == integralPart || status == digitAfterDot || status == digitAfterE)
        cout << "This is the double real number";
    else
        cout << "This is not the double real number";
    return 0;
}