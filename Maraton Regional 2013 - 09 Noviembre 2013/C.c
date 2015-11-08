#include<stdio.h>
#include<math.h>
typedef unsigned long long lli;

lli a,b,c,e = 1;
lli f(lli n){
	int log = log2(n);
	//printf("log %d\n", log);
	lli res = 0;
	for(b=0;b<=log;b++){
		//printf("%lld %lld\n", (((n>>b)>>e)<<b), ((n>>b)&e)*(n&((e<<b)-e)));
		res+=(((n>>b)>>e)<<b) +((n>>b)&e)*(n&((e<<b)-e));
	}
	//printf("retorna %lld\n", res);
	return res;
}

int main(){
	//printf("%lld\n", ((10000000000000000L>>33L)&1L));
	//printf("%lld\n", (10000000000000000L&((e<<33L)-1L)));
	printf("");
	while(scanf("%lld %lld", &a, &c)==2){
		//printf("%lld %lld\n", c+1L, f(a));
		printf("%lld\n", (f(c+e)-f(a)));
		}
	return 0;
}
