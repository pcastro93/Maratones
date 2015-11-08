#include<iostream>
#include<vector>
#include<stdlib.h>
typedef unsigned int ll;
using namespace std;
	ll gcd(ll a, ll b){
		return(b==0?a:gcd(b,a%b));
	}
	ll max(ll a, ll b){
		return (a>b?a:b);
	}
	int main(){
		ll w;
		int n,c;
		vector<ll> ai;//profit items
		vector<ll> pi;//peso
		while(cin>>w){
			cin>>n>>c;
			int precomp[n][3];//precio compra
			for(int i=0;i<n;i++){
				cin>>precomp[i][0]>>precomp[i][1];//peso
				precomp[i][2] = precomp[i][1]-precomp[i][0];//profit
			}
			ai.clear();
			pi.clear();
			int dcg = 0;
			for(int i=0;i<c;i++){
				int fff;
				cin>>fff;
				long int peso = 0;
				long int profit = 0;
				for(int j=0;j<fff;j++){
					int pos, veces;
					cin>>pos>>veces;
					pos--;
					peso += (veces*precomp[pos][0]);
					profit+= (veces*precomp[pos][2]);
				}
				if(profit>0 && peso<=w){
					dcg = gcd(dcg, peso);
					ai.push_back(profit);
					pi.push_back(peso);
				}
			}
			//for(int i=0;i<ai.size();i++)
			//	cout<<ai[i]<<" "<<pi[i]<<endl;
			w = w/dcg;
			for(int i=0;i<ai.size();i++)
				pi[i]/=dcg;
			//ll dp[2][(int)(w+1)];
			int **dp = (int**)malloc((2)*sizeof(int*));
			for(int i=0;i<2;i++)dp[i] = (int*)(malloc)((w+1)*sizeof(int));
			for(int i=0;i<2;i++)
				for(int j=0;j<(int)(w+1);j++)
					dp[i][j] = 0;
			for(int i=1;i<=ai.size();i++){
				for(int j=1;j<=w;j++)
					if(j>=pi[i-1])
						dp[(i&1)][j]= max(dp[(i-1)&1][j],
								dp[(i-1)&1][j-pi[i-1]]+ai[i-1]);
					else
						dp[i&1][j] = dp[(i-1)&1][j];
			}
			cout<<(dp[ai.size()&1][(int)(w)])<<endl;
		}
		return 0;
	}