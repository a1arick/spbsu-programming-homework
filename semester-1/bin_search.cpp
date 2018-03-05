#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef long double db;
typedef pair<int,int> pii;
typedef vector<int> vi;
#define forn(i,n) for(int (i) = 0; (i) < (int)(n); ++(i))
#define dbg(x) cout<<#x" = "<<((x))<<endl;
#define all(x) (x).begin(),(x).end();
#define pb(x) push_back(x);
#define fi first;
#define se second;
#define ds() puts("---stop here---")
#define mset(a,i) memset(a,i,sizeof(a))
#define rep(i,a,b) for(int i=a;i<(b);++i)
#define rrep(i,j,k) for(int i=j;i>=k;i--)
#define sz(x) (int)(x).size()
#define mp make_pair

int binfind(int b, vi &a){
    int l = 0, r = sz(a) - 1, m;
    while(l <= r){
        m = (l + r)/ 2;
        if(a[m] == b) return m+1;
        else if(a[m] > b) r = m - 1;
        else  l = m + 1;
    }
    return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,b,k;
    cin>>n;
    vi a;
    forn(i,n){
        cin>>b;
        a.pb(b);
    }
    cin>>k;
    forn(i,k){
        cin>>b;
        cout<<binfind(b,a)<<" ";
    }
    return 0;
}