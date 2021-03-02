#include <iostream>
#include<vector>
#include<climits>
#include<cstring>

using namespace std;

void INPUT()
{
#ifndef ONLINE_JUDGE
    freopen("C:/Users/arvin/Desktop/Current/input.txt", "r", stdin);
    freopen("C:/Users/arvin/Desktop/Current/output.txt", "w", stdout);
#endif
}

int n = 4;
vector<vector<int>> dp;
// Adj Matrix which defines the graph
vector<vector<int>> dist;


//If all cities have been visited
int VISITED_ALL = (1 << n) - 1;

int tsp(int mask, int pos) {

    if (mask == VISITED_ALL) {
        return dist[pos][0];
    }
    //Lookup
    if (dp[mask][pos] != -1) {
        return dp[mask][pos];
    }

    int ans = INT_MAX;
    //visiting an unvisited city
    for (int city = 0; city < n; city++) {

        if ((mask & (1 << city)) == 0) {

            int newAns = dist[pos][city] + tsp( mask | (1 << city), city);
            ans = min(ans, newAns);

        }


    }

    return dp[mask][pos] = ans;

}

int main() {

    INPUT();
    cin >> n;
    VISITED_ALL = (1 << n) - 1;
    dist = vector< vector<int> > (n + 1, vector<int> (n + 1, INT_MAX));
    dp = vector< vector<int> > (1 << n, vector<int> (n, -1));

    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
            cin >> dist[i][j];
        }
    }


    cout << "Min weight hamiltonian path costs " << tsp(1, 0) << endl;
    return 0;
}

/*
4

0 20 42 25
20 0 30 34
42 30 0 10
25 34 10 0
*/