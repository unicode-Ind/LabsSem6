#include <iostream>
#include <vector>
#include<climits>
using namespace std;

void INPUT()
{
#ifndef ONLINE_JUDGE
    freopen("C:/Users/arvind/Desktop/Current/input.txt", "r", stdin);
    freopen("C:/Users/arvind/Desktop/Current/output.txt", "w", stdout);
#endif
}

void printSubsets(const vector<vector<bool>> &dp, const vector<int> &numbers, int firstSum) {

    int n = numbers.size();
    vector<bool> SubsetIndex(n, false);
    //true in first subset else n second (probale greater sum subset)

    int numberIndex = n;
    while (firstSum and numberIndex > 0) {

        if (dp[numberIndex][firstSum]) {
            numberIndex--;
            if (dp[numberIndex][firstSum])
                continue;

            SubsetIndex[numberIndex] = true;
            firstSum = firstSum - numbers[numberIndex] + 1;
        }
        firstSum--;

        // if (dp[numberIndex][firstSum] == true and dp[numberIndex - 1][firstSum] == true) {
        //     numberIndex--;
        //     continue;
        // }

        // if (dp[numberIndex][firstSum] == true and dp[numberIndex - 1][firstSum] == false) {
        //     SubsetIndex[numberIndex - 1] = true;

        //     firstSum = firstSum - numbers[numberIndex - 1] + 1;

        //     numberIndex--;
        // }
        // firstSum--;
    }


    cout << "\nFirst Subset  :";
    for (int i = 0; i < n; ++i)
    {
        if (SubsetIndex[i])
            cout << " " << numbers[i];
    }

    cout << "\nSecond Subset :";
    for (int i = 0; i < n; ++i)
    {
        if (!SubsetIndex[i])
            cout << " " << numbers[i];
    }
    cout << endl;
}

int findMin(vector<int> &numbers, int n) {

    int sum = 0, total = 0;
    for (int i : numbers)
        total += i;

    sum = total / 2;

    //Subset Sum Problem
    vector<vector<bool>> dp(n + 1, vector<bool>(sum + 1, false));
    //rows - num
    //col = sum [0-tatal/2]

    for (int i = 0; i <= n; ++i)
        dp[i][0] = true;


    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= sum; ++j)
        {
            dp[i][j] = dp[i - 1][j];

            if (j - numbers[i - 1] >= 0)
                dp[i][j] = dp[i][j] || dp[i - 1][j - numbers[i - 1]];

        }
    }

    int diff = INT_MAX;
    int firstSum = total, secondSum = 0;
    for (int i = sum; i >= 0 ; --i)
    {
        if (dp[n][i]) {
            firstSum = i;
            secondSum = total - firstSum;
            diff = secondSum - firstSum;
            break;
        }
    }

    printSubsets(dp, numbers, firstSum);

    cout << endl;

    // for (int i = 0; i < n + 1; ++i)
    // {
    //     for (int j = 0; j <= sum; ++j)
    //     {
    //         /* code */
    //         cout << dp[i][j] << " ";
    //     }
    //     cout << endl;
    // }

    return diff;
}

/*
int findMin(vector<int> &A, int n) {

    int sum = 0;
    for (int i = 0; i < n; ++i)
        sum += A[i];

    //Subset Sum Problem
    bool dp[n + 1][sum + 1];

    for (int i = 0; i <= n; ++i)
        for (int j = 0; j <= sum; ++j)
        {
            if (j == 0)
                dp[i][j] = true;
            else if (i == 0)
                dp[i][j] = false;
            else if (A[i - 1] > j)
                dp[i][j] = dp[i - 1][j];
            else
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
        }

    int diff = INT_MAX;
    for (int i = 0; i <= sum / 2; ++i)
    {
        int first = i;
        int second = sum - i;
        if (dp[n][i] == true and diff > abs(first - second))
            diff = abs(first - second);
    }

    return diff;
}
*/

int main()
{
    INPUT();

    int n;
    cin >> n;
    vector<int> input(n);
    for (int i = 0; i < n; ++i)
        cin >> input[i];

    int ans = findMin(input, n);

    cout << "The minimum difference = " << ans;
    return 0;
}


/*

6
3 15 4 2 2 6
*/