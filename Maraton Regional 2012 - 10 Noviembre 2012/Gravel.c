#include <stdio.h>
#include <stdlib.h>
 
 
void validate(int a)
{
	if (!a)
		abort();
}
 
long long tree[2100000];
 
void addtree(int n, int k)
{
	int i = 20;
	int val = 0;
	while (n >=0){
		if (n & 1) {
			val += 1<<i;
			i--;
			n >>= 1;
		}
		else {
			tree[n + val] += k;
			n--;
		}
	}
}
 
 
long long value2(int idx){
	long long sum = 0;
	int k=0;
	for(k=0;k<10;k++)
		printf("%lld ",tree[k]);
	while (idx > 0){
		sum += tree[idx];
		idx -= (idx & -idx);
	}
	return sum;
}
long long value(int n)
{
	int i = 20;
	int val = 0;
	long long res = 0;
	while (i >=0){
		res +=tree[n + val];
		val += 1<<i;
		i--;
		n >>= 1;
	}
	return res;
 
}
int readint()
{
	int cc = getc(stdin);
	while (cc < '0' || cc > '9')
		cc = getc(stdin);
	int ret = 0;
	while (cc >= '0' && cc <= '9') {
		ret = ret * 10 + cc - '0';
		cc = getc(stdin);
	}
	return ret;
	
 
}
 
int main()
{
	int u, v, k, p, n, m, c;
    n= readint();
    m= readint();
    c= readint();
 
	validate(n > 0 && n <= 1000000);
	validate(m > 0 && m <= 250000);
	validate(c >= 0 && c <= 1000000000);
    int i;
	for (i = 0; i < (1<<21) - 2; i++)
		tree[i]=0;
	tree[i] = c;
 
	while (m--) {
		char cc;
		cc = getc(stdin);
		while (cc != 'Q' && cc != 'S' && cc != 'q' && cc != 's')
			cc = getc(stdin);
		if (cc == 'Q' || cc== 'q') {
			p = readint();
	        validate(p > 0 && p <= n);
			long long  t = value(p);
			printf("%lld\n", t);
			long long t2 = value2(p);
			printf("==>%lld\n", t2);
		}
		else {
			u = readint();
			v = readint();
			k = readint();
	        validate(k > 0 && k <= 1000000000);
	        validate(u > 0 && u <= n);
	        validate(v >= u && v <= n);
			addtree(u-1, -k);
			addtree(v, k);
		}
	}
   return 0;
}
