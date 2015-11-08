#include <iostream>
#include <string.h>
#include <stdio.h>
#include <fstream>
#include <map>

using namespace std;
int main(int argc, char const *argv[])
{	
	// freopen("gatica.in", "r", stdin);	
	// freopen("output.txt", "w", stdout);	

	char cadena[1100];
	int casos; cin>> casos;
	for (int caso = 0; caso < casos; ++caso)
	{
		map<char, int> letras;
		letras['A'] = letras['G'] = letras['T'] = letras['C'] = 0;
		cin>>cadena;
		int len = strlen(cadena);
		for (int i = 0; i < len; ++i)
			letras[cadena[i]]++;
		//vamos a verificar que almenos se repite una letra..
		bool no_procesar = (letras['A'] <= 1) && (letras['G'] <= 1) && (letras['T'] <= 1)&& (letras['C'] <= 1);

		int repeticiones = 0;
		int maxLenRepete = 0;
		int inicioRepete = 0;

		for (int I = 0; I < len && !no_procesar; ++I)
		{
			for (int J = I+1; J < len; ++J)
				{	int k=0;
					// buscamos la long de la repeteticion
					for (;((k+J)<len && cadena[I+k]==cadena[J+k]);k++);
					if( k > maxLenRepete){
						maxLenRepete = k;
						inicioRepete = I;
						repeticiones = 2;
					}
					// si ya habia otra con la misma long toca escojer la 
					// primera en el orden lexicografico
					else if ( k == maxLenRepete){
						int dif = 0;
						for (; dif < maxLenRepete  && cadena[I+dif]==cadena[inicioRepete+dif]; dif++);
						if( cadena[I+dif] < cadena[inicioRepete+dif]){
							inicioRepete = I;
							repeticiones = 2;
						}
						if(dif==maxLenRepete && inicioRepete == I){
							//tenemos una repeticion mas
							repeticiones ++;
						}
					}
				}	
		}
		if (repeticiones > 0){
			for (int i = 0; i < maxLenRepete; i++)
				cout<<cadena[inicioRepete+i];
			cout<<" "<<repeticiones<<"\n";
		}else cout << "No repetitions found!\n";

	}
	return 0;
}