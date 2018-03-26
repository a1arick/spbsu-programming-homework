//
// Created by algsa on 17.03.2018.
//

#ifndef UNTITLED4_QUEUE_H
#define UNTITLED4_QUEUE_H


struct QueueElement {
    int value;
    QueueElement *next = nullptr;
};

class Queue {
public:
    QueueElement *head = nullptr;
    QueueElement *tail = nullptr;
};


void clear(Queue &queue);

bool isEmpty(Queue &queue);

void push(Queue &queue, int value);

int pop(Queue &queue);

int front(Queue &queue);
#endif //UNTITLED4_QUEUE_H
