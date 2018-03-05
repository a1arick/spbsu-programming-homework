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

const int MIN = -100000000000;
int a[100001];
int t = 0;

void ins(int b){
    a[t] = b;
    if(t == 1) return;
    else{
        int r = t;
        int root = r / 2;
        while(a[root] < b){
            int y = a[r];
            a[r] = a[root];
            a[root] = y;
            r = root;
            root = root / 2;
            if(root < 1) break;
        }
    }


}
void extractMax() {
    if (t > 0) {
        cout << a[1] << endl;
        int y = a[1];
        a[1] = a[t];
        a[t] = y;
        t--;

        int root = 1;
        while (root <= 2 * t) {
            int l = 2 * root;
            int r = 2 * root + 1;
            int maxRoot = max(a[root],max(a[l], r <= t ? a[r] : MIN));
            if(maxRoot == a[root]) break;
            if (l <= t && a[l] == maxRoot) {
                y = a[root];
                a[root] = a[l];
                a[l] = y;
                root = l;
                continue;
            }
            if (r <= t && a[r] == maxRoot) {
                y = a[root];
                a[root] = a[r];
                a[r] = y;
                root = r;
                continue;
            }
            break;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,b;
    string s;
    cin>>n;
    forn(i,n){
        cin>>s;
        if(s == "Insert"){
            cin>>b;
            t++;
            ins(b);
        }
        else if(s == "p"){
           // cout<<a[3];
            for(int j = 1; j <= t; j++) cout<<a[j]<<" ";
        }
        else if(s == "ExtractMax"){
            extractMax();
        }
    }
    return 0;
}