#include <iostream>
#include <vector>
using namespace std;


void INPUT()
{
#ifndef ONLINE_JUDGE
	freopen("C:/Users/arvind/Desktop/Current/input.txt", "r", stdin);
	freopen("C:/Users/arvind/Desktop/Current/output.txt", "w", stdout);
#endif
}

void printSelectedElements(vector<vector<int>> &dp, const vector<int> &weights,
                           const vector<int> &profits, int capacity) {

	//dp index 1,2 .... mapped to wt index 0,1,2
	cout << "Selected weights:";
	int totalProfit = dp[weights.size()][capacity];
	for (int i = weights.size(); i > 0; i--) {
		if (totalProfit != dp[i - 1][capacity]) {
			cout << " " << weights[i - 1];
			capacity -= weights[i - 1];
			totalProfit -= profits[i - 1];
		}
	}

	cout << endl;
}

int solveKnapsack(const vector<int> &profits, const vector<int> &weights, int capacity) {
	// basic checks
	if (capacity <= 0 || profits.empty() || weights.size() != profits.size()) {
		return 0;
	}

	int n = profits.size();
	vector<vector<int>> dp(n + 1, vector<int>(capacity + 1, 0));

	//first column and row = 0 , (capacity =0  and no items resp.)

	//dp index 1,2 .... mapped to wt index 0,1,2
	for (int i = 1; i <= n; i++) {
		for (int c = 1; c <= capacity; c++) {
			int profit1 = 0, profit2 = 0;
			// include the item, if it is not more than the capacity
			if (weights[i - 1] <= c) {
				profit1 = profits[i - 1] + dp[i - 1][c - weights[i - 1]];
			}
			// exclude the item
			profit2 = dp[i - 1][c];
			// take maximum
			dp[i][c] = max(profit1, profit2);
		}
	}

	printSelectedElements(dp, weights, profits, capacity);
	// maximum profit will be at the bottom-right corner.
	return dp[n][capacity];
}

int solveKnapsack_SpaceOptimised(const vector<int> &profits, vector<int> &weights, int capacity) {

	if (weights.size() == 0 or capacity <= 0 or weights.size() != profits.size())
		return 0;

	vector<vector<int>> dp(2, vector<int>(capacity + 1, 0));

	int i = 1;
	for (; i <= weights.size(); i++) {
		for (int c = 1; c <= capacity; c++) {
			dp[i % 2][c] = dp[(i - 1) % 2][c];
			if (weights[i - 1] <= c) {
				int op2 = dp[(i - 1) % 2][c - weights[i - 1]] + profits[i - 1];

				dp[i % 2][c] = max(dp[i % 2][c], op2);
			}
		}
	}
	return dp[(i - 1) % 2][capacity];
}

int main(int argc, char *argv[]) {

	INPUT();

	int n, maxCapacity;
	cin >> n;

	vector<int> profits(n);
	vector<int> weights(n);

	for (int i = 0; i < n; ++i)
		cin >> profits[i];
	for (int i = 0; i < n; ++i)
		cin >> weights[i];

	cin >> maxCapacity;

	int maxProfit = solveKnapsack(profits, weights, maxCapacity);
	cout << "Max knapsack profit :" << maxProfit << endl;

}

/*

4
1 6 10 16
1 2 3 5
6

*/