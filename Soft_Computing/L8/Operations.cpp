#include <bits/stdc++.h>
using namespace std;
void INPUT();

class Operations
{
	bool n1, n2, ans;

public:

	void inp() {
		cout << "\nEnter two bits as input :\n";
		cin >> n1 >> n2;
	}

	void Oper() {
		cout << "\n** Operations **";
		cout << "\n1. AND :" << (n1 & n2);
		cout << "\n2. OR :" << (n1 | n2);
		cout << "\n3. XOR :" << (n1 ^ n2);
		cout << "\n4. NAND :" << !(n1 & n2);
		cout << "\n5. NOR :" << !(n1 & n2);
		cout << "\n6. NOT :" << !n1;
		//cout << "\n7. XNOR :"<<(n1 == n2); // same bits ==> 1 ,
		cout << "\n7. XNOR :" <<	((n1 | !n2) & (!n1 + n2)) << "\n";

	}

};


int main()
{
	int choice = -1;
	Operations op;

	do
	{
		cout << "\n****  MENU ****\n";
		cout << "\n0. Exit";
		cout << "\n1. Input numbers";
		cout << "\n2. Perform Operations\n";
		cin >> choice;

		switch (choice) {
		case 1 :
			op.inp(); break;
		case 2 :
			op.Oper(); break;
		default :
			cout << "\n**********************************************\n\n"; break;
		}
	} while (choice != 0);





// void Oper() {
// 	int choice;
// 	cout << "\n** Operations **";
// 	cout << "\n0. Exit";
// 	cout << "\n1. AND";
// 	cout << "\n2. OR";
// 	cout << "\n3. XOR";
// 	cout << "\n4. NAND";
// 	cout << "\n5. NOR";
// 	cout << "\n6. NOT";
// 	cout << "\n7. XNOR\n"; // same bits ==> 1 ,

// 	do
// 	{
// 		cin >> choice;

// 		switch (choice) {
// 		case 1:
// 			ans = n1 & n2; break;
// 		case 2:
// 			ans = n1 | n2; break;
// 		case 3:
// 			ans = n1 ^ n2; break;
// 		case 4:
// 			ans = !(n1 & n2); break;
// 		case 5:
// 			ans = !(n1 | n2); break;
// 		case 6:
// 			ans = !n1; break;
// 		case 7:
// 			ans  = (n1 == n2);
// 		//ans = (n1 | !n2) & (!n1 + n2) break;
// 		default : break;
// 		}

// 		cout << "\n\nANS = " << ans << "\n";
// 	} while (choice);

// }


