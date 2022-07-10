#include <iostream>

using namespace std;

struct Node {
    int   data;
    Node* next;
};

bool add(Node*& head, int data) {
    Node* node = head;
    while (node) {
        if (data == node->data) {
            return false;
        }
        node = node->next;
    }
    Node* node1 = new Node();
    node1->data = data;
    node1->next = head;
    head = node1;
    return true;
}

size_t size(const Node* head) {
    size_t numOfNodes = 0;
    while (head) {
        numOfNodes++;
        head = head->next;
    }
    return numOfNodes;
}

void printList(const Node* head) {
    while (head) {
        cout << head->data << " ";
        head = head->next;
    }
    cout << endl;
}

void clear(Node*& head) {
    while (head) {
        Node* del = head;
        head = head->next;
        cout << "DEL: " << del->data << " ";
        delete del;
    }
}


int main() {
    using std::cout; using std::endl;
    int tab[] = { 1,4,1,3,5 };
    Node* head = 0;
    for (size_t i = 0, e = std::size(tab); i != e; ++i) {
        bool b = add(head, tab[i]);
        cout << tab[i] << (b ? "     " : " NOT ")
            << "added" << endl;
    }
    cout << "Size of the list: " << size(head) << endl;
    printList(head);
    clear(head);
}