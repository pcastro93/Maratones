#include<iostream>
#include<stdio.h>
#include<queue>
#include<math.h>
using namespace std;

class cucu{
  
public:
  int num;
  int den;
  double res;
  
  cucu(int a, int b){
  	num = a;
  	den = b;
    res = a/(double)b;
  }
  const bool operator <(const cucu& b) const{
    return res<b.res;
  }
};

priority_queue<cucu> *pq;

int main(){
  int c, b, hab, ballot;
  while( true )
  {
    //cin >> c >> b;
    scanf("%d %d", &c, &b);
    if(c==-1 && b==-1)break;
      
    pq = new priority_queue<cucu>; 
    for(int i=0;i<c;i++)
    {
      scanf("%d", &hab);
      cucu d(hab,1);
      pq->push(d);
    }
    //cin.getline (buf,LEN); //Skips the blank line
    
    b-=c;
    while(b--){
      cucu na = pq->top();
      pq->pop();
      cucu n(na.num, na.den+1);
      pq->push(n);
    }
    
    cout<< ceil(pq->top().res)<<"\n";
    delete pq;
  }
  
  return 0;
}
