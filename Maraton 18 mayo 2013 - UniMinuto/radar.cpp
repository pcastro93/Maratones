/* d555 */
#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>
using namespace std;

int main(){
	int N,R;
	while(1){
		cin>>N>>R;
		if ( N == 0 && R == 0) break;
		int M[110][360];
		for(int i = 0; i < 110; i++)
			for(int j = 0; j < 360; j++)
				M[i][j] = 0;
		int r,t;
		for(int i = 0; i<N; i++){
			cin>>r>>t;
			M[r][t]++;
		}

		int E,H,A; cin>>E;

		for( int i = 0; i < E; i++){
			cin>>H>>A;

			int AUX[ R + 1 ]; 

			for(int h = 0; h <= R ; h++) AUX[h] = 0;

			for(int a  = 0; a <= A ; a++)
				for( int r = 0; r <	 H; r++)
						AUX[0] = AUX[0] + M[r][a];

			int max_fila =  AUX[0];
			int max  = 0;

			for( int a = 1; a < 360 ; a++){
					for( int r = 0; r < H; r++){
						max_fila = max_fila - M[r][ (a - 1) % 360 ];
						max_fila = max_fila + M[r][ (a + A) % 360 ];
					}
					if ( max_fila >  max) 
						max = max_fila;
				}

			// cout<<"max #0("<<H<<","<<A<<") = "<<max<<endl;

			for(int h = 1; h <= R - H; h++){
				AUX[h] = AUX[h-1];
				for(int a  = 0; a <= A ; a++){
					AUX[h] = AUX[h] - M[ h - 1 ][a]; // resto la fila h-1
					AUX[h] = AUX[h] + M[ h + H - 1][a]; // suma la fila h+H
				}

				// kadane..
				if ( AUX[h] >  max) 
						max = AUX[h];

				max_fila = AUX[h]; //temporalmente.

				for( int a = 1; a < 360 ; a++){
					for( int r = 0; r < H; r++){
						max_fila = max_fila - M[h+r][ (a - 1) % 360 ];
						max_fila = max_fila + M[h+r][ (a + A) % 360 ];
					}
					if ( max_fila >  max ) 
						max = max_fila;
				}
				// cout<<"max #"<<h<<"("<<H<<","<<A<<") = "<<max<<endl;


			}
			cout<<max<<endl;
		}

	}
}