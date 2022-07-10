#include <iostream>
template <typename T>
struct Node {
	T data;
	Node* next;
};
template <typename T>

Node<T>* arrayToList(const T arr[], size_t size) {
	Node<T>* head = new Node<T>;
	Node<T>* headtmp = head;
	for (size_t i = 0; i < size; i++)
	{
		headtmp->data = arr[i];
		headtmp->next = new Node<T>;
		headtmp = headtmp->next;
	}
	headtmp->next = nullptr;
	return head;
}
template <typename T, typename Pred>

void removeBad(Node<T>*& head, Pred p) {
	while (head->next != nullptr) {
		if (p(head->data)) {
			std::cout << "DEL " << head->data << "; ";
			head = head->next;
		}
		else
			break;
	}
	Node<T>* temp = head;
	do {
		if (p(temp->next->data)) {
			std::cout << "DEL " << temp->next->data << "; ";
			temp->next = temp->next->next;
		}
		else
			temp = temp->next;
	} while (temp->next != nullptr && temp->next->next != nullptr);
	std::cout << "\n";
}

template <typename T>

void showList(const Node<T>* head) {
	if (head == nullptr)
		std::cout << "empty list";
	else
		while (head->next != nullptr) {
			std::cout << head->data << " ";
			head = head->next;
		}
	std::cout << std::endl;
}

template <typename T>
void deleteList(Node<T>*& head) {
	Node<T>* temp = head;
	while (temp->next != nullptr) {
		Node<T>* del = temp;
		temp = temp->next;
		std::cout << "deleting " << del->data << " ";
		delete del;
	}
	head = nullptr;
	std::cout << std::endl;
}

int main() {
	int arr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	size_t size = std::size(arr);
	Node<int>* head = arrayToList(arr, size);
	showList(head);
	removeBad(head, [](int n) {return n % 2 != 0; });
	showList(head);
	removeBad(head, [](int n) {return n < 5; });
	showList(head);
	removeBad(head, [](int n) {return n > 9; });
	showList(head);
	deleteList(head);
	showList(head);
}
