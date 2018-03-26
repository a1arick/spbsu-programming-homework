#include "cycleList.h"
using namespace std;

void push(int value, CycleList &list) {
    if (isEmpty(list)) {
        list.current = new ListElement;
        list.current->value = value;

        list.current->next = list.current;
        list.current->prev = list.current;
    } else {
        ListElement *newCurrent = new ListElement;
        newCurrent->value = value;

        newCurrent->next = list.current->next;
        (list.current->next)->prev = newCurrent;
        newCurrent->prev = list.current;
        list.current->next = newCurrent;

        list.current = newCurrent;
    }
    list.size++;
}

void remove(CycleList &list, int i) {
    if (isEmpty(list))
        return;

    for (int k = 0; k < i; ++k)
        list.current = list.current->next;

    list.current->prev->next = list.current->next;
    list.current->next->prev = list.current->prev;

    ListElement *temp = list.current->next;
    delete list.current;
    list.current = temp;

    list.size--;

    if (list.size == 0)
        list.current = nullptr;
}

int get(CycleList &list, int i) {
    if(i < list.size) {
        for (int k = 0; k < i; ++k)
            list.current = list.current->next;

        return list.current->value;
    } else return -404;
}

int getSize(CycleList &list) {
    return list.size;
}

bool isEmpty(CycleList list) {
    return list.current == nullptr;
}

void out(CycleList &list) {
    for (int i = 0; i < list.size; ++i) {
        cout << list.current->value + 1<< " ";
        list.current = list.current->next;
    }
    cout << endl;
}

void clear(CycleList &list) {
    while (!isEmpty(list))
        remove(list, 0);
}

