//
// Created by algsa on 17.03.2018.
//

#ifndef UNTITLED4_POSTFIXNOTATION_H
#define UNTITLED4_POSTFIXNOTATION_H

#pragma once
#include "queue.h"

int calculate(Queue &expression, bool isPostfix = false);

Queue toPostfix(Queue &inStr);


#endif //UNTITLED4_POSTFIXNOTATION_H
