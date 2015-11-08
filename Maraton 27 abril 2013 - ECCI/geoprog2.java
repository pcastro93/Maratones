import java.util.*;
import java.io.*;
public class geoprog{
public static Set<Integer> dec;
public static HashMap<Integer,Integer> cad[];
public static void main(String args[]) throws IOException{
BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
String tmp = "";
while((tmp = lector.readLine())!=null){
String l[] = tmp.split(" ");
dec = new HashSet<Integer>();
cad = new HashMap[4];
for(int n = 0;n<cad.length;n++)cad[n] = new HashMap<Integer,Integer>();
for(int n = 0,c=0;n<6;n++,c++){
if(n==2 || n==5){c--;continue;}
int p = Integer.parseInt(l[n]);
int pp = p;
if((p&1)==0)dec.add(2);
int u = 0;
while((p&1)==0 && p>0){
u++;
p>>=1;
}
if(u>0)
cad[c].put(2,u);
int sq = (int)Math.sqrt(p);
for(int m = 3;m<=sq;m+=2){
u = 0;
if(p%m==0)dec.add(m);
while(p%m==0 && p>0){
u++;
p/=m;
}
if(u>0)cad[c].put(m,u);
if(p==1)break;
}
if(p!=1){cad[c].put(p,1);dec.add(p);}
if(pp==1){cad[c].put(1,1);dec.add(1);}
}
int n1 = Integer.parseInt(l[2]);
int n2 = Integer.parseInt(l[5]);
int mm = Math.max(n1,n2);
int mmm = Math.min(n1,n2);
int estoy = mm==n1?0:1;
int res = n1+n2;
//System.out.println(dec);
if(l[0].equals("0") || l[1].equals("0")){
if(l[3].equals("0") || l[4].equals("0")){
System.out.println("1");
continue;
}else{
if(l[4].equals("1")){
System.out.println("2");
}else{
System.out.println(n2+1);
}
continue;
}
}else if(l[3].equals("0") || l[4].equals("0")){
if(l[0].equals("0") || l[1].equals("0")){
System.out.println("1");
continue;
}else{
if(l[1].equals("1")){
System.out.println("2");
}else{
System.out.println(n1+1);
}
continue;
}
}
if(l[1].equals("1")){
if(l[4].equals("1")){
if(l[0].equals(l[3]))
System.out.println(1);
else
System.out.println(2);
}else{
System.out.println(n2+1);
}
continue;
}
if(l[4].equals("1")){
if(l[1].equals("1")){
if(l[0].equals(l[3]))
System.out.println(1);
else
System.out.println(2);
}else{
System.out.println(1+n1);
}
continue;
}
int ar[] = {Integer.parseInt(l[0]),Integer.parseInt(l[1]),Integer.parseInt(l[3]),Integer.parseInt(l[4])};
int mcd = gcd(ar[1],ar[3]);
//System.out.println(ar[1]+" "+ar[3]);

if(mcd == 1){//caso extremo en el que valga mierda elevar el i y el j..osease hay muchas posibilidades para j dado i, si no funciona mando a la mierda a todo el mundo, sí incluido usted.
double lo = Math.log(ar[0])/Math.log(ar[3]);

double lo1 = Math.log(ar[2])/Math.log(ar[1]);
//System.out.println("Entro "+lo+" "+lo1);
if(lo==lo1 && lo==Math.round(lo)){
System.out.println(n1+n2-1);
continue;
}
if(gcd(ar[1],ar[2])==1 && gcd(ar[0],ar[2])==1 && gcd(ar[0],ar[3])==1){
//System.out.println("todos cop");
System.out.println(n1+n2);
continue;
}
}
//algo para bajarle a mm..j es el minimo
double logjM = 0.0;
Iterator it1 = dec.iterator();
while(it1.hasNext()){
int o = (int)it1.next();
int ex1[]= new int[4];
for(int e = 0;e<cad.length;e++)ex1[e] = (cad[e].containsKey(o))?cad[e].get(o):0;
//System.out.println(o+"^("+ex1[estoy*2]+"+"+(mmm-1)+"*"+ex1[1+estoy*2]);
logjM+=(ex1[(estoy^1)*2]+(mmm-1)*ex1[1+(estoy^1)*2])*Math.log(o);
}
//System.out.println("El del maximo del minimo es "+logjM);
for(int i = 0;i<mm;i++){
Iterator it = dec.iterator();
int j = -1;
Vector<Integer> jo = new Vector<Integer>();
boolean te[] = new boolean[mmm];
boolean joder = false;
double logari = 0.0;
itt:
while(it.hasNext()){
int o= (Integer)it.next();
int ex[]= new int[4];
for(int e = 0;e<cad.length;e++)ex[e] = (cad[e].containsKey(o))?cad[e].get(o):0;
logari+=(ex[estoy*2]+i*ex[estoy*2+1])*Math.log(o);
if(j==-1){
if(ex[3]!=0){
double ec = (ex[estoy*2]+i*ex[estoy*2+1]-ex[(estoy^1)*2])/(double)ex[(estoy^1)*2+1];
if(!(ec==Math.round(ec) && ec<mmm && ec>-1)){
j = (int)ec;
joder = true;
break itt;
}else
j = (int)ec;
}else{//cuando b' es 0
j = 1;
if(ex[estoy*2]+i*ex[estoy*2+1]!=ex[(estoy^1)*2]){
joder = true;
break itt;
}else
j = 1;
}
/*
int mini = 0;
int maxi = mmm-1;
boolean enc = false;
while(mini<=maxi){
int b = (maxi+mini)>>1;
if(ex[0]+i*ex[1]==ex[2]+b*ex[3]){
j = b;
enc = true;
break;
}
if(ex[0]+i*ex[1]<ex[2]+b*ex[3])maxi = b-1;
else
mini = b+1;
}
if(!enc){
joder = true;
break itt;
}
//System.out.println(o+" "+i+" "+j);
//System.out.println(jo);
*/
}else{
if(ex[estoy*2]+i*ex[estoy*2+1]!=ex[(estoy^1)*2]+j*ex[(estoy^1)*2+1]){
joder= true;
break itt;
}

}
}
//aca va logaritmo
//System.out.println("En i="+i+" "+logari+" "+logjM);
if(logari>logjM){
//System.out.println("Salio en "+i+" de "+mm);
break;
}
if(!joder)res--;
}
System.out.println(res);
}

}
public static int gcd(int a,int b){
boolean joder = true;
while(joder){
if(b==0){joder = false;return a;}
int t = b;
b = a%b;
a = t;
}
return -1;
}
}
