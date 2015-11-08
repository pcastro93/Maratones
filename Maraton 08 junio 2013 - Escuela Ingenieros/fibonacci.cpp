//WTF team

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <cstring>
#include <cstdlib>
#include <cstdio>
#include <cmath>

using namespace std;
#define MAXSIZEP 29
#define MAXN 110
typedef unsigned long long ll;

int N;
string p;
vector<ll> fibo(MAXSIZEP);
string palabra[MAXN];
ll dp[MAXN] = {0};

void fibonacci(){
   fibo[0] = 1; fibo[1] = 1;
   palabra[0] = "0";
   palabra[1] = "1"; 

   int n = 2;
    while ( n < MAXSIZEP ){
        fibo[n] = fibo[n-2] + fibo[n-1];
        palabra[n] = palabra[n-1] + palabra[n-2];  
        n = n + 1;
    }
}

vector<int> KMP(string S, string K)
{
        vector<int> T(K.size() + 1, -1);
        for(int i = 1; i <= K.size(); i++)
        {
                int pos = T[i - 1];
                while(pos != -1 && K[pos] != K[i - 1]) pos = T[pos];
                T[i] = pos + 1;
        }
 
        vector<int> matches;
        for(int sp = 0, kp = 0; sp < S.size(); sp++)
        {
                while(kp != -1 && (kp == K.size() || K[kp] != S[sp]))
                     kp = T[kp];
                kp = kp + 1;
                if(kp == K.size()) matches.push_back(sp + 1 - K.size());
        }
        return matches;
}

void imprimirDP(){
    for ( int i = 0; i < MAXN; i++)
        cout << i  << "\t" << dp[i] << "\n";
    cout << "\n";
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
    fibonacci();
    int caso = 0;
    while ( cin>>N ){
        if ( N == -1 ) break;
        caso = caso + 1;
        cin>>p;
        int posFibo = upper_bound( fibo.begin(), fibo.end(), p.size() ) - fibo.begin();
        if ( N <= 1){
            int ri = KMP(palabra[N], p).size();
            cout << "Case "<< caso << ": " << ri << "\n";
        }
        else{
	
	        string F = palabra[ posFibo];
	        string G = palabra[ posFibo + 1];
	        
	        int ocurr[2] = {0};
	
	        string cad[2];
	        cad[0] = G+F;
	        cad[1] = F+G;
	        ll o0=KMP(cad[0], p).size(),o1=KMP(cad[1], p).size();
	        dp[posFibo - 1] = KMP(palabra[posFibo-1],p).size() ;
	        dp[posFibo] = KMP(F,p).size() ;
	        dp[posFibo + 1] = KMP(G,p).size();
	        
	        //en el overlap sera: ocurrencias en posFibo+1,posFibo(o al reves) menos ocurrencias en posFibo+1 menos ocurrencias en posFibo
	        ocurr[0] = o0-dp[posFibo+1]-dp[posFibo];
	        ocurr[1] = o1-dp[posFibo+1]-dp[posFibo];
	        int ccc = 0;
	        for ( int i = posFibo + 2; i < 101; i++,ccc++ )
	                dp[i] = dp[ i-1 ] + dp[ i-2 ] + ocurr[ccc&1];
	        cout << "Case "<< caso << ": " << dp[N] << "\n";
        }
    }
}