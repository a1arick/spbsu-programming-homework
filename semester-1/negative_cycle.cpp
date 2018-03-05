#include<bits/stdc++.h>
using namespace std;
const int INF = 1000000000;
struct edge {
    int a,b,cost;
};
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,a,l = 0,x;
    cin>>n;
    vector<edge> e;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin>>a;
            if(a != 100000){
                edge u;
                u.a = i;
                u.b = j;
                u.cost = a;
                e.push_back(u);
            }
        }
    }
    if(l == 0) {
        vector<int> d(n,10001);
        vector<int> p(n, -1);
        int x, k = 0;
        for (int i = 0; i < n; ++i) {
            x = -1;
            for (int j = 0; j < e.size(); ++j) {
                if (d[e[j].b] > d[e[j].a] + e[j].cost) {
                    if (i == n - 1) k++;
                    d[e[j].b] = d[e[j].a] + e[j].cost;
                    p[e[j].b] = e[j].a;
                    x = e[j].b;
                }
            }
            if (x == -1) break;
        }

        if (x == -1)
            cout << "NO";
        else {
            vector<int> ans;
            for (int i=0; i<n; ++i)x = p[x];
            vector<int> path;
            for (int cur=x; ; cur=p[cur]) {
                if (cur == x && path.size() > 1)  break;
                path.push_back (cur);
            }
            reverse (path.begin(), path.end());
            cout<<"YES"<<endl<<path.size()<<endl;
            for (int i=0; i<path.size(); ++i)
                cout << path[i] + 1<<" ";
        }
    }
    return 0;
}
