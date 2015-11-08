#include<iostream>
#include<stdio.h>
using namespace std;
int n,k,i;
int read(int idx, int *tree){
	int sum = 0;
	while (idx > 0){
		sum += tree[idx];
		idx -= (idx & -idx);
	}
	return sum;
}
void update(int idx ,int val, int *tree){
	while (idx < n+1){
		if(tree[idx]+val>=0)//mio
			tree[idx] += val;
		idx += (idx & -idx);
	}
}
int readSingle(int idx, int *tree){
	int sum = tree[idx]; // sum will be decreased
	if (idx > 0){ // special case
		int z = idx - (idx & -idx); // make z first
		idx--; // idx is no important any more, so instead y, you can use idx
		while (idx != z){ // at some iteration idx (y) will become z
			sum -= tree[idx]; 
		// substruct tree frequency which is between y and "the same path"
			idx -= (idx & -idx);
		}
	}
	return sum;
}
int signum(int a){
	if(a==0)
		return 0;
	if(a>0)
		return 1;
	return -1;
}
int main(){
	while(cin>>n>>k){
//	if(n==0 && n==k)break;
	int treec[n+1];
	int treem[n+1];
	for(i=0;i<n+1;i++)
		treec[i] = treem[i] = 0;
	for(i=0;i<n;i++){
		int ll;
		cin>>ll;
		ll= signum(ll);
		if(ll==0)
			update(i+1, 1, treec);
		if(ll==-1)
			update(i+1, 1,treem);
		}
	char op;
	int a,b,bb;
	while(k-->0){
		cin>>op>>a>>bb;
		b = signum(bb);
		int val = readSingle(a, treem);
		int valc = readSingle(a, treec);
		if(op=='C'){
			if(b==0){
				if(val==valc && valc==0){//era positivo
				update(a,1,treec);
				}
				else if(val>0){//era menos
					update(a,-1,treem);
					update(a,1,treec);
				}else{}//ya era cero, no hace nada
			}else if(b==-1){
				if(val==valc && valc==0){//era positivo
					update(a,1,treem);
				}else if(val>0){}//era negativo, no hace nada
				else{//era cero
					update(a,-1, treec);
					update(a,1,treem);
				}
			}else{
				if(val==0 && valc==0){}//no naga nada, ya era positivo
				else{
					if(val>0)//era menos
						update(a, -1, treem);
					else//era cero
						update(a, -1, treec);
				}
			}
		}
		if(op=='P'){
			int ceros;
			ceros = read(bb, treec)-read(a-1,treec);
			if(ceros>0)
				cout<<"0";
			else{
				int menos = read(bb, treem)-read(a-1,treem);
				if((menos&1)==0)
					cout<<"+";
				else
					cout<<"-";
			}
		}
	}
	cout<<endl;
	}
	return 0;
}
