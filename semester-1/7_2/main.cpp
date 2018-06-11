#include <iostream>
#include "AVLTree.h"

using namespace std;

void hint(){
    cout << "0 - exit" << endl;
    cout << "1 <value> - add value" << endl;
    cout << "2 <value> - remove value" << endl;
    cout << "3 - tree is empty?" << endl;
    cout << "4 <value> - check whether element is" << endl;
    cout << "5 - print in decreasing order" << endl;
    cout << "6 - print in increasing order" << endl;
    cout << "7 - print in ABC order" << endl;
    cout << "8 - clear tree" << endl;
    cout << "9 - hint" << endl;
}

int main() {
    AvlTree tree;
    hint();
    cout << endl << "Choose your command to binary search tree: " << endl;
    int command = 0;
    cin >> command;
    while (command != 0) {
        int value = 0;
        switch (command) {
            case 1:
                cout << "Input value to add:" << endl;
                cin >> value;
                add(value, tree);
                break;

            case 2:
                cout << "Input value to remove:" << endl;
                cin >> value;
                remove(value, tree);
                break;
            case 3:
                if(isEmpty(tree)) cout << "True" <<endl;
                else cout << "False" <<endl;
                break;

            case 4:
                cout << "Input value to check:" << endl;
                cin >> value;
                if (isContains(value, tree))
                    cout << "True" << endl;
                else
                    cout << "False" << endl;
                break;

            case 5:
                decOutRoot(tree);
                break;

            case 6:
                incOutRoot(tree);
                break;

            case 7:
                abcOutRoot(tree);
                break;

            case 8:
                clear(tree);
                break;

            case 9:
                hint();
                break;
        }
        cout << "Choose your command to binary search tree: " << endl;
        cin >> command;
    }

    clear(tree);
    return 0;
}