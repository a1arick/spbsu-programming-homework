#include "AVLTree.h"
#include <iostream>

using namespace std;

Node *createNode(int value) {
    Node *newNode = new Node;

    newNode->key = value;
    newNode->height = 0;
    newNode->left = nullptr;
    newNode->right = nullptr;

    return newNode;
}

int height(Node *node) {
    return node ? node->height : 0;
}

int balanceFactor(Node *node) {
    return height(node->right) - height(node->left);
}

void updateHeight(Node *node) {
    int heightLeft = height(node->left);
    int heightRight = height(node->right);
    node->height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
}

Node *rotateRight(Node *root) {
    Node *pivot = root->left;
    root->left = pivot->right;
    pivot->right = root;

    updateHeight(root);
    updateHeight(pivot);

    return pivot;
}

Node *rotateLeft(Node *root) {
    Node *pivot = root->right;
    root->right = pivot->left;
    pivot->left = root;

    updateHeight(root);
    updateHeight(pivot);

    return pivot;
}

Node *balance(Node *p) {
    updateHeight(p);

    if (balanceFactor(p) == 2) {
        if (balanceFactor(p->right) < 0)
            p->right = rotateRight(p->right);

        return rotateLeft(p);
    }

    if (balanceFactor(p) == -2) {
        if (balanceFactor(p->left) > 0)
            p->left = rotateLeft(p->left);

        return rotateRight(p);
    }

    return p;
}

void add(int value, Node *&p) {
    if (p == nullptr) {
        p = createNode(value);
        p = balance(p);
        return;
    }

    if (value < p->key)
        add(value, p->left);
    else
        add(value, p->right);

    p = balance(p);
}

void add(int value, AvlTree &tree) {
    add(value, tree.root);
}

Node *findMin(Node *p) {
    return p->left ? findMin(p->left) : p;
}

Node *removeMin(Node *p) {
    if (p->left == nullptr)
        return p->right;
    p->left = removeMin(p->left);
    return balance(p);
}

Node *remove(int k, Node *p) {
    if (p == nullptr)
        return nullptr;
    if (k < p->key)
        p->left = remove(k, p->left);
    else {
        if (k > p->key)
            p->right = remove(k, p->right);
        else {
            Node *left = p->left;
            Node *right = p->right;
            delete p;
            if (!right)
                return left;
            Node *min = findMin(right);
            min->right = removeMin(right);
            min->left = left;
            return balance(min);
        }
    }
    return balance(p);
}

void remove(int value, AvlTree &tree) {
    tree.root = remove(value, tree.root);
}

void abcOut(Node *p) {
    if (p != nullptr) {
        cout << " (" << p->key;
        abcOut(p->left);
        cout << "";
        abcOut(p->right);
        cout << ")";
    } else
        cout << " null";
}

void abcOutRoot(AvlTree &tree) {
    abcOut(tree.root);
    cout << endl;
}

bool isContains(int value, Node *p) {
    if (p == nullptr)
        return false;

    if (p->key == value)
        return true;

    if (value < p->key)
        return isContains(value, p->left);
    else
        return isContains(value, p->right);
}

bool isContains(int value, AvlTree &tree) {
    return isContains(value, tree.root);
}

bool isEmpty(AvlTree &tree) {
    return tree.root == nullptr;
}

void incOut(Node *p) {
    if (p == nullptr)
        return;

    incOut(p->left);
    cout << " " << p->key << " ";
    incOut(p->right);
}

void incOutRoot(AvlTree &tree) {
    incOut(tree.root);
    cout << endl;
}

void decOut(Node *p) {
    if (p == nullptr)
        return;

    decOut(p->right);
    cout << " " << p->key << " ";
    decOut(p->left);
}

void decOutRoot(AvlTree &tree) {
    decOut(tree.root);
    cout << endl;
}

void clear(Node *&p) {
    if (p == nullptr)
        return;

    clear(p->left);
    clear(p->right);

    delete p;
    p = nullptr;
}

void clear(AvlTree &tree) {
    clear(tree.root);
}