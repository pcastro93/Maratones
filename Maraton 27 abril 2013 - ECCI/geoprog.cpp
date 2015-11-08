#include<iostream>
#include<set>
#include <algorithm>
#include<vector>
#include<cmath>
using namespace std;
	
	void facto(int n, vector<int>* facs,vector<int>* p){
		facs->insert(facs->begin(),p->size(), 0);
		for(int i=0;i<p->size();i++){
			int cont = 0;
			while(n%p->at(i)==0){
				n/=p->at(i);
				cont++;
				}
			facs->at(i)=cont;
			}
	}
	
	bool raro(int a, int b){
		return (a==0 || b<=1);//genera una constante para ese conjunto.
	}
	void pos(int n, set<int>* ff){
		int fin = (int)sqrt(n);
		int n2 = 0;
		if(n==1 || n==0)return;
		while((n&1)==0){
			n2++;
			n>>=1;
		}
		if(n2!=0)
			ff->insert(2);
		int div = 3;
		int ccc = 0;
		while(n!=1 && div<=fin){
			if(n%div==0){
				n/=div;
				ccc++;
				}
			else{
				if(ccc!=0)
					ff->insert(div);
				ccc=0;
				div+=2;
			}
		}
		if(n==1 && ccc!=0)
			ff->insert(div);
		if(n!=1)
			ff->insert(n);
	}
	void agregar(vector<int>* pb1, vector<int>* pq1, set<vector<int> >* conj, int n1){
		for(int i=0;i<n1;i++){
					conj->insert((*pb1));
					//exponenciamos
					for(int k=0;k<pb1->size();k++)
						pb1->at(k)+= pq1->at(k);
				}
	}
	void ini(set<int>* ff, vector<int>* p){
		vector<int> pp(ff->begin(), ff->end());
		sort(pp.begin(), pp.end());
		(*p) = pp;
	}
	void imp(vector<int>* v){
		for(int i=0;i<v->size();i++)
			cout<<v->at(i)<<" ";
		cout<<endl;
	}
	void imps(set<int>* ff){
		for(set<int>::iterator it = ff->begin(); it != ff->end(); it++){
					cout<< *it <<" ";
				}
		cout<<endl;
	}
	void mod(vector<int>*m){
		m->insert(m->begin(), 3, 0);
	}
	int main(){
		/*vector<int>p;
		p.push_back(2);
		p.push_back(3);
		vector<int> rep;
		int n = 6;
		facto(n, &rep, &p);
		imp(&rep);*/
		/*vector<int> mm;
		mod(&mm);
		for(int i=0;i<mm.size();i++)
			cout<<mm[i]<<" ";
		cout<<endl;*/
		int cont = 0;
		int b1, q1, n1, b2, q2, n2;
		while(cin>>b1>>q1>>n1>>b2>>q2>>n2){
			vector<int>p;
			set<int> ff;
			if(b2==0 || q2<=1){
				//magic swap
				b2^=b1;b1^=b2;b2^=b1;
				q2^=q1;q1^=q2;q2^=q1;
				n2^=n1;n1^=n2;n2^=n1;
			}
			if(raro(b1, q1)){
				bool ya = false;
				set<long long> conj;
				conj.insert((long long)b1);
				if(n1>1)conj.insert((long long)b1*(long long)q1);
				long long act = b2;
				for(int i=0;i<n2 && !ya;i++){
					conj.insert(act);
					act*=q2;
					if(act>500000000){//la constante generada no es mas grande que 500 000 000
						cout<<(n2-i-1+conj.size())<<("\n");//los que faltan mas lo que llevaba
						ya = true;
						}
				}
				if(!ya){
					ya = true;
					cout<<(conj.size())<<("\n");
				}
			}else{
				set<vector<int> > conj;
				pos(b1, &ff);pos(q1, &ff);pos(n1, &ff);
				pos(b2, &ff);pos(q2, &ff);pos(n2, &ff);
				ini(&ff, &p);
				vector<int> pb1, pq1, pb2, pq2;
				facto(b1, &pb1 ,&p);
				facto(q1, &pq1 ,&p);
				facto(b2, &pb2 ,&p);
				facto(q2, &pq2 ,&p);
				
				agregar(&pb1, &pq1, &conj, n1);
				agregar(&pb2, &pq2, &conj, n2);
				cout<<(conj.size())<<("\n");
			}
		}
		return 0;
	}