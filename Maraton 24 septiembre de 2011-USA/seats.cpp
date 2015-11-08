#include<vector>
#include<iostream>
#define max 20
using namespace std;
	char mat[max][max],matt[max][max];
	bool termine = false;
	int si;
	void imprimir(char a[max][max]){
		for(int n = 0;n<si;n++){
			for(int m= 0;m<si;m++)
				cout<<a[n][m];
			cout<<endl;
		}
	}
	int listo(){
		for(int n = 0;n<si;n++)
			for(int m = 0;m<si;m++)
				if(mat[n][m]>='0' && mat[n][m]<='9')return n;
		return -1;
	}
	void hagale(char letra){
		int r = listo();
		if(termine)return;
		if(r==-1){
			termine = true;
			return;
		}
		for(int n = r;n<si;n++){
			for(int m = 0;m<si;m++){
				bool entro1 = false;
				if(mat[n][m]>='0' && mat[n][m]<='9'){
					entro1 = true;
					int a = mat[n][m]-'0';
					for(int k = 1;k<=a;k++){
						if(a%k==0){
							for(int o = 0;o<a;o++){
								bool puedo = true;
								int esY = n-o/k;
								int esX = m-o%k;
								for(int x = 0;x<k && puedo;x++)
									for(int y = 0;y<a/k && puedo;y++)
										if((x+esX<0 || x+esX>=si || y+esY<0 || y+esY>=si) || ((x+esX!=m || y+esY!=n) && mat[y+esY][x+esX]!='.'))
											puedo = false;
								if(puedo && !termine){
									vector<int> cambio3;
									for(int x = 0;x<k && puedo;x++)
										for(int y = 0;y<a/k && puedo;y++){
											cambio3.push_back((x+esX)*10000+(y+esY)*100+(int)mat[y+esY][x+esX]);
											mat[y+esY][x+esX] = letra;
										}
									hagale((char)((int)letra+1));
									if(!termine){
										for(int u = 0;u<cambio3.size();u++)
											mat[(cambio3[u]/100)%100][cambio3[u]/10000] = (char)(cambio3[u]%100);
									}else return;
								}
							}

						}
					}
				}
				if(entro1)return;
			}
		}
	}
	
	int main(){
	int ott;
		while(cin>>si>>ott){
		if(si==0 && ott==0)break;
			termine = false;
			for(int i=0;i<si;i++)
				for(int j=0;j<si;j++)
					mat[i][j] = '.';
			for(int i=0;i<si;i++)
				for(int j=0;j<si;j++)
					matt[i][j] = '.';
			for(int n = 0;n<si;n++){
				for(int k=0;k<si;k++){
					char c;
					cin>>c;
				mat[n][k]= c;
				matt[n][k]=c;
				}
			}
			hagale('A');
			imprimir(mat);
		}

	}
