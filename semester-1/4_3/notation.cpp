#include "notation.h"
#include "stack.h"

bool isDigit(char token) {
    return token >= '0' && token <= '9';
}

Queue toPostfix(Queue &inStr) {
    Stack operations;
    Queue outStr;

    while (!isEmpty(inStr)) {
        auto curToken = static_cast<char>(front(inStr));
        if (isDigit(curToken)) {
            push(outStr, curToken);
            pop(inStr);
            continue;
        }

        if (isEmpty(operations)) {
            push(operations, curToken);
            pop(inStr);
            continue;
        }

        int last = head(operations);
        switch (curToken) {
            case '+':
            case '-':
                if (last == '(') {
                    push(operations, curToken);
                    pop(inStr);
                }
                else
                    push(outStr, pop(operations));
                break;

            case '*':
            case '/':
                if (last == '(' || last == '+' || last == '-') {
                    push(operations, curToken);
                    pop(inStr);
                }
                else
                    push(outStr, pop(operations));
                break;

            case '(':
                push(operations, curToken);
                pop(inStr);
                break;

            case ')':
                while (head(operations) != '(')
                    push(outStr, pop(operations));
                pop(operations);
                pop(inStr);
                break;
        }
    }

    while (!isEmpty(operations))
        push(outStr, pop(operations));

    clear(operations);
    return outStr;
}