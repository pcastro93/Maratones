#include<stdio.h>
#include<string.h>
#include <math.h>

#define MAXCITIES 500000

int numcities, numboxes;
int population[MAXCITIES + 1];
int x, remainboxes, izq, der, sol, med, maxi;

int lb(int p, int l, int b){//poblacion ciudad i, el limite y las cajas
	//encuentro el minimo x tal que p/x<=l con x<=b
	/*int low =1, high = b;
	int x=high;
	while(low<=high){
		int mid = (low+high)/2;//no hay overflow :)
		//printf("mid %d\n", mid);
		if(p/mid+(p%mid==0?0:1)<=l){
			x = (x>mid?mid:x);
			high = mid-1;
			}
		if(p/mid+(p%mid==0?0:1)>l)
			low= mid+1;
	}
	if(p/x+(p%x==0?0:1)>l)
		return (b+1);//no lo puede lograr con las cajas dadas
	return x;*/
	int x = 1;
	for(;p/x+(p%x==0?0:1)>l && x<=b;x++);
	return x;
}
//retorna si se puede obtener valores menores o iguales al limite establecido (limit)
int isPossible( int limit, int remainboxes ){
  int x, denominador, rb = remainboxes;
  for( x = 0; x < numcities; x++){
    denominador = lb(population[x], limit, rb);
    remainboxes -= denominador;
    if( remainboxes < 0) return 0;
  }
  return 1;
}

int main(){
   while( scanf("%d %d", &numcities, &numboxes) != EOF &&  ( numcities != -1 || numboxes != -1 ) ){
     for( x = 0; x < numcities ; x++){ scanf("%d", &population[ x ]);maxi = (maxi<population[x]?population[x]:maxi);}
     remainboxes = numboxes;
     sol = 500000;
     for( izq = 1, der = maxi; izq <= der ;  ){
        med = (izq + der) / 2;
        if( isPossible( med, remainboxes ) ){
           sol = med;
           der = med - 1; 
        }else izq = med + 1;
     }
     printf("%d\n", sol);
   } 
   return 0;
}

