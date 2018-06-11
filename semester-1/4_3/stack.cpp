#include "stack.h"

const int error = -404;

StackElement* createStackElement(int value) {
    auto *newStackElement = new StackElement;

    newStackElement->value = value;
    newStackElement->next = nullptr;

    return newStackElement;
}

void clear(Stack &stack) {
    while (!isEmpty(stack))
        pop(stack);
}

bool isEmpty(Stack &stack) {
    return stack.head == nullptr;
}

void push(Stack &stack, int value) {
    StackElement *newStackElement = createStackElement(value);

    newStackElement->next = stack.head;
    stack.head = newStackElement;
}

int pop(Stack &stack) {

    if (isEmpty(stack))
        return error;

    int result = stack.head->value;

    StackElement *tempElement = stack.head;
    stack.head = stack.head->next;
    delete tempElement;

    return result;
}

int head(Stack &stack) {
    if (isEmpty(stack))
        return error;

    return stack.head->value;
}