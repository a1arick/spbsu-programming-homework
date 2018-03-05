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


struct Node{
    int x;
    Node *next, *prev;
};

class List{
private:
    Node *head, *tail;
    void remove(Node *temp){
        if(head == tail){
            head = tail = NULL;
        }
        else if(temp == head){
            Node *tm = head -> next;
            tm -> prev = NULL;
            head = tm;
        }
        else if(temp == tail){
            Node *tm = tail -> prev;
            tm -> next = NULL;
            tail = tm;
        }
        else{
            Node *next = temp -> next;
            Node *prev = temp -> prev;
            next -> prev = prev;
            prev -> next = next;
        }
        delete temp;
    }

public:
    List(): head(NULL), tail(NULL){}
    void addLast(int a){
        Node *temp = new Node;
        temp -> x = a;
        temp -> next = NULL;
        temp -> prev = tail;
        if(tail != NULL) tail -> next = temp;
        else head = temp;
        tail = temp;
    }
    void addFirst(int a){
        Node *temp = new Node;
        temp -> x = a;
        temp -> next = head;
        temp -> prev = NULL;
        if(head != NULL) head -> prev = temp;
        else tail = temp;
        head = temp;
    }
    void show(){
        Node *temp = head;
        while(temp != NULL){
            cout<<temp -> x<<" ";
            temp = temp -> next;
        }
        cout<<endl;
    }
    void remove(int a){
        if(head != NULL){
            Node *temp = head;
            while (temp != NULL && temp -> x != a){
                temp = temp -> next;
            }
            if(temp != NULL) remove(temp);
        }
    }
    int removeFirst(){
        int ans = head -> x;
        remove(head);
        return ans;
    }
    int removeLast(){
        int ans = tail -> x;
        remove(tail);
        return ans;
    }

};


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    ///////////////////////// очередь
    List f;

    return 0;
}