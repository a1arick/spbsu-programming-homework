#include <iostream>
#include "notation.h"

using namespace std;

int main() {
    Queue inStr;

    cout << "Enter expression in the prefix form and end with =" << endl;

    char curToken = ' ';
    cin >> curToken;
    while (curToken != '=') {
        push(inStr, curToken);
        cin >> curToken;
    }

    Queue postfixStr = toPostfix(inStr);

    cout << "Your expression in postfix notation:" << endl;
    while (!isEmpty(postfixStr))
        cout << (char)pop(postfixStr);
    
    return 0;
}