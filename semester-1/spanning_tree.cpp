#include<bits/stdc++.h>
using namespace std;
bool cmp(pair<pair<int,int>,int> a , pair<pair<int,int>,int> b){
    return a.second < b.second;
}
vector<int> p;
vector<int> siz;
int dsu_get(int u){
    if(p[u] == u) return u;
    else return p[u] = dsu_get(p[u]);
}
void dsu_merge(int u, int v){
    u = dsu_get(u);
    v = dsu_get(v);
    if(siz[u] < siz[v]) swap(u,v);
    p[v] = u;
    if(siz[u] == siz[v]) siz[u]++;
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,m,a,b,cost;
    pair<pair<int,int>,int> k;
    cin>>n>>m;
    vector<pair<pair<int,int>,int > > g;
    for (int i = 0; i < m; ++i) {
        cin>>a>>b>>cost;
        a--;
        b--;
        k.first.first = a;
        k.first.second = b;
        k.second = cost;
        g.push_back(k);
    }
    sort(g.begin(),g.end(),cmp);
    int res = 0;
    p.resize(n);
    siz.resize(n);
    for (int i = 0; i < n; ++i) {
        p[i] = i;
        siz[i] = 1;
    }
    for (int i = 0; i < m; ++i) {
        a = g[i].first.first;
        b = g[i].first.second;
        cost = g[i].second;
        if(dsu_get(a) != dsu_get(b)){
            res += cost;
            dsu_merge(a,b);
        }
    }
    cout<<res;
    return 0;
}