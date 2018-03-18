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

void qsort (int b, int e, vector<int> &arr)
{
    int l = b, r = e;
    int piv = arr[(l + r) / 2];
    while (l <= r)
    {
        while (arr[l] < piv)
            l++;
        while (arr[r] > piv)
            r--;
        if (l <= r)
            swap(arr[l++], arr[r--]);
    }
    if (b < r)
        qsort (b, r, arr);
    if (e > l)
        qsort (l, e, arr);
}  


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,a;
    cin>>n;
    vector<int> arr;
    forn(i,n){
        cin>>a;
        arr.push_back(a);
    }
    qsort(0, n-1, arr);
    for (auto i  : arr) {
        cout<<i<<" ";
    }
    return 0;
}