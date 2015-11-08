#include<math.h>
#include<iostream>
using namespace std;
typedef unsigned long long lli;
lli a,b,c,e = 1;
lli f(lli n){
	int log = log2(n);
	lli res = 0;
	for(b=0;b<=log;b++)
		res+=(((n>>b)>>e)<<b) +((n>>b)&e)*(n&((e<<b)-e));
	return res;
}
int main(){
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	while(cin>>a>>c)
		cout<<(f(c+e)-f(a))<<'\n';
	return 0;
}
