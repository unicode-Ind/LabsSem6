#include <iostream>
#include <conio.h>
#include <iomanip>
#include <cmath>
using namespace std;

const int maxClusters = 2;
const int vectors = 4;
const int vectorLen = 4;
const double decayRate = 0.96;
const double minAlpha = 0.01;

double alpha = 0.5;
double d[maxClusters];

double w[maxClusters][vectorLen] = {{0.2, 0.6, 0.5, 0.9},
	{0.8, 0.4, 0.7, 0.3}
};

int pattern[vectors][vectorLen] = {{1, 1, 0, 0},
	{0, 0, 0, 1},
	{1, 0, 0, 0},
	{0, 0, 1, 1}
};

int tests[vectors][vectorLen] = {{0, 0, 1, 1},
	{1, 0, 0, 0},
	{0, 1, 1, 0},
	{0, 0, 0, 1}
};

int minimum(double valueA, double valueB) {
	if (valueA > valueB) {
		return 1;
	} else {
		return 0;
	}
}

void computeInput(int vectorNumber) {

	d[0] = 0.0;
	d[1] = 0.0;

	for (int i = 0; i <= (maxClusters - 1); i++) {
		for (int j = 0; j <= (vectors - 1); j++) {
			d[i] += pow((w[i][j] - tests[vectorNumber][j]), 2);

		}
	}
}

void training() {
	int iterations = 0;
	int dMin = 0;

	do {
		iterations += 1;

		for (int vecNum = 0; vecNum <= (vectors - 1); vecNum++) {
			computeInput(vecNum);
			dMin = minimum(d[0], d[1]);

			//Update the weights on the winning unit.
			for (int i = 0; i <= (vectors - 1); i++) {
				w[dMin][i] = w[dMin][i] + (alpha * (pattern[vecNum][i] -
				                                    w[dMin][i]));

			}
		}
		//Reduce the learning rate.
		alpha = decayRate * alpha;

	} while (alpha > minAlpha);

	cout << "Iterations: " << iterations << "\n\n";
}

void showResult() {
	int dMin;

//Print clusters created.
	cout << "-------------------------------------------------------------\n";
	cout << "Clusters for training input:" << endl;

	for (int vecNum = 0; vecNum <= (vectors - 1); vecNum++) {
		computeInput(vecNum);
		dMin = minimum(d[0], d[1]);
		cout << "\nVector (";
		for (int i = 0; i <= (vectors - 1); i++) {
			cout << pattern[vecNum][i] << ", ";
		}
		cout << ") fits into cluster " << dMin << endl;

	}

//Print weight matrix.
	cout << "-------------------------------------------------------------\n";
	for (int i = 0; i <= (maxClusters - 1); i++) {
		cout << "Weights for Node " << i << " connections:" << endl;
		for (int j = 0; j <= (vectorLen - 1); j++) {
			cout << w[i][j] << ", ";
		}
		cout << "\n\n";
	}

	cout << "-------------------------------------------------------------\n";
}



int main() {
	cout << fixed << setprecision(3) << endl;
	training();
	showResult();

	getch();

	return 0;
}