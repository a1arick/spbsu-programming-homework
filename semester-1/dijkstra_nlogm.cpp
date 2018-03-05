#include<bits/stdc++.h>
using namespace std;
const int INF = 1000000000;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,a,b,w,m,s,f;
    cin>>n>>m>>s>>f;
    s = s - 1;
    f = f - 1;
    vector<int> d(n);
    vector<vector<pair<int,int> > >  g(n,vector<pair<int,int> >());
    for (int i = 0; i < m; ++i) {
        cin>>a>>b>>w;
        a--; b--;
        g[a].push_back(make_pair(w, b));
        g[b].push_back(make_pair(w, a));
    }
    for (int i = 0; i < n; ++i) {
        d[i] = INF;
    }
    d[s] = 0;
    set<pair<int,int> >q;
    q.insert(make_pair(d[s], s));
    while(!q.empty()){
        auto u = *q.begin();
        int head = u.second;
        q.erase(q.begin());
        if (d[head] < u.first) continue;
        for (auto e : g[head]) {
            int v = e.second;
            int dist = e.first;
            if(d[v] > d[head] + dist){
                d[v] = d[head] + dist;
                q.insert(make_pair(d[v], v));
            }
        }
    }
    cout<< (d[f] == INF ? -1 : d[f]);
    return 0;
}
