#include <iostream>
#include "cycleList.h"

using namespace std;

int main() {
    CycleList cycleList;

    int n = 0;
    int m = 0;
    cout << "Enter quantity of warriors and killing index:" << endl;
    cin >> n >> m;

    for (int i = 0; i < n; ++i)
        push(i, cycleList);

    get(cycleList, 1);
    out(cycleList);
    while (cycleList.size != 1) {
        remove(cycleList, m - 1);
        out(cycleList);
    }

    cout << "Joseph should stay on position: ";
    out(cycleList);

    clear(cycleList);
    return 0;
}