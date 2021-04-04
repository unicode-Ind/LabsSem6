#include <bits/stdc++.h>
using namespace std;
void INPUT();

class Operations
{
	int n1, n2, ans, len;

	void printBinary(int n) {
		stack<bool> s;
		int i = 0;

		while (i < len) {
			s.push(n % 2);
			n /= 2;
			i++;
		}

		while (!s.empty()) {
			cout << s.top();
			s.pop();
		}
	}
public:

	void inp() {
		n1 = n2 = 0;
		string s1, s2;
		cout << "\nEnter two binary numbers :\n";
		cin >> s1 >> s2;

		len = max(s1.size(), s2.size());

		int i = 0;
		while (i < s1.size()) {
			n1 = s1[i] + n1 * pow(2, i);
			i++;
		}

		i = 0;
		while (i < s2.size()) {
			n2 = s2[i] + n2 * pow(2, i);
			i++;
		}

	}

	void Oper() {
		int choice;
		cout << "\n** Operations **\n";
		cout << "1. AND\n";
		cout << "2. OR\n";
		cout << "3. XOR\n";
		cout << "4. NAND";
		cout << "5. NOR\n";
		cout << "6. NOT\n";
		cout << "7. XNOR\n";

		cin >> choice;

		switch (choice) {
		case 1:
			ans = n1 & n2;
			break;
		case 2:
			ans = n1 | n2;
			break;
		case 3:
			ans = n1 ^ n2;
			break;
		case 4:
			ans = ~(n1 & n2);
			break;
		case 5:
			ans = ~(n1 | n2);
			break;
		case 6:
			break;
		case 7:
			break;
		default :
			break;
		}

		cout << endl << "ANS = ";
		printBinary(ans) ;
		cout << endl;
	}

};


int main()
{
	//INPUT();

	int choice = -1;
	Operations op;

	do
	{
		cout << "****  MENU ****\n";
		cout << "0. Exit\n";
		cout <<  "1. Input numbers\n";
		cout <<  "2. Perform Operations";
		cin >> choice;

		switch (choice) {
		case 1 :
			op.inp();
			break;
		case 2 :
			op.Oper();
			break;
		default :
			cout << "\n**********************************************\n\n";
			break;
		}
	} while (choice != 0);



	return 0;
}


void INPUT()
{
#ifndef ONLINE_JUDGE
	freopen("C:/Users/arvind/Desktop/Current/input.txt", "r", stdin);
	freopen("C:/Users/arvind/Desktop/Current/output.txt", "w", stdout);
#endif
}