#include <iostream>
#include <algorithm>
using namespace std;
int main(){
	int n;
	while(1){
		cin>>n;
		if ( n == 0 ) break;
		double numeros[n];
		for ( int i =0; i<n; i++) cin>>numeros[i];
		sort(numeros, numeros + n);
		bool sirve = false;
		int actual = 1;
		double suma = numeros[0];
		while( !sirve && actual < n){
			if ( suma >= numeros[actual] )
				sirve = true;
			suma = suma + numeros[actual];
			actual = actual + 1;
			// cout<<"suma>"<<suma<<" "<<actual<<endl;
		}
		if (sirve) cout<<"YES"<<endl;
		else cout<<"NO"<<endl;
	}

}