// #include <iostream>
// #include<vector>
// #include<climits>
// #include<cstring>
#include<bits/stdc++.h>
using namespace std;

void INPUT()
{
#ifndef ONLINE_JUDGE
    freopen("C:/Users/arvin/Desktop/Current/input.txt", "r", stdin);
    freopen("C:/Users/arvin/Desktop/Current/output.txt", "w", stdout);
#endif
}

int n = 4;
//vector<vector<int>> dp;
// Adj Matrix which defines the graph
vector<vector<int>> dist;
list<int> sequence;

// unordered_map<int, list<int>> all_path;


//If all cities have been visited
int VISITED_ALL = (1 << n) - 1;

int ans_overall = INT_MAX;

int tsp(int mask, int pos, list<int> &temp, int cur_cost) {

    if (mask == VISITED_ALL) {
        if (ans_overall > cur_cost + dist[pos][0]) {
            sequence = temp;
            sequence.push_back(0);
        }
        //all_path[cur_cost + dist[pos][0]] = temp;
        return dist[pos][0];
    }
    //Lookup
    // if (dp[mask][pos] != -1) {
    //     return dp[mask][pos];
    // }

    int ans = INT_MAX;
    //visiting an unvisited city
    for (int city = 0; city < n; city++) {

        if ((mask & (1 << city)) == 0) {

            temp.push_back(city);

            int newAns = dist[pos][city] + tsp( mask | (1 << city), city, temp , cur_cost + dist[pos][city]);
            ans = min(ans, newAns);

            temp.pop_back();

        }


    }

    return ans;
    // return dp[mask][pos] = ans;

}

int main() {

    INPUT();

    cin >> n;
    VISITED_ALL = (1 << n) - 1;
    dist = vector< vector<int> > (n + 1, vector<int> (n + 1, INT_MAX));
    //dp = vector< vector<int> > (1 << n, vector<int> (n, -1));

    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
            cin >> dist[i][j];
        }
    }

    //sequence.push_back(0);

    list<int> temp;
    temp.push_back(0);

    int tsp_cost = tsp(1, 0, temp, 0);

    cout << "Min weight hamiltonian path costs : " << tsp_cost << endl;

    cout << "\n\nPath: ";

    // for (auto i : all_path[tsp_cost]) {
    //     cout << i << " -> ";
    // }

    // cout << 0 << endl;

    for (auto i : sequence) {
        cout << i << " -> ";
    }
    return 0;
}
