#include <bits/stdc++.h>
using namespace std;
int t = 0;
const int N = 100000;
vector<int> topsort;
int x[N];
int used[N];
void dfs (int v,vector <unordered_set<int> > &g, int s, int color){
    if( t == 1 ) return;
    used[v] = color;
    x[v] = s;
    for(auto i: g[v]){
      if(!used[i]){
        dfs(i,g,v, color);
        if(t == 1) return;
      } else if(used[i] == color){
        t = 1;
        return;
      }
    }
    topsort.push_back(v+1);
    used[v] += N;
}

int main(){
  ios_base::sync_with_stdio(false);
  cin.tie(0); cout.tie(0);
  int n,m;
  cin>>n>>m; 
  vector < unordered_set<int> > g(n);
  for(int i = 0, a, b; i<m; ++i){
    cin>>a>>b;
    a--;
    b--;
    g[a].insert(b);
  }
  for(int i = 0; i< n && t == 0; ++i){
    if(used[i] == 0){
      dfs(i,g,-1, i + 1);
    }
  }
  if(t == 1) cout<<-1;
  else if (t == 0){
    reverse (topsort.begin(), topsort.end());
    for(auto i: topsort) cout<<i<<" ";
  }
  return 0;