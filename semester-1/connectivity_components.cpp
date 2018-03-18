#include <bits/stdc++.h>
using namespace std;
int t = 0;
int *q;
bool *used;
void dfs (int v,vector <vector<int> > &g){
	  used[v] = true;
	  q[v] = t;
	  for (auto i: g[v]){
		  if (used[i] == 0){
			  dfs (i,g);
		  }
	  }
  }

int main(){
  ios_base::sync_with_stdio(false);
  cin.tie(0); cout.tie(0);
  int n,m,a,b;
  cin>>n>>m; 
  vector < vector<int> > g(n);
  used = new bool[n];
  for(int i = 0; i<n; ++i) {used[i] = 0;}
  q = new int[n];
  for(int i=0; i<m; ++i){
    cin>>a>>b;
    a--;
    b--;
    g[a].push_back(b);
    g[b].push_back(a);
  }
  for(int i = 0; i< n; ++i){
    if(used[i] == false){
     t++;
     dfs(i,g);
    }
  }
  cout<<t<<endl;
  for(int i = 0; i < n ; ++i){
    cout<<q[i]<<" ";
  }
  return 0;
}