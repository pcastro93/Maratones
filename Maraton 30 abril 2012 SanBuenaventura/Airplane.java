import java.util.*;
public class Airplane{
	public static int arr = 0,ab=0;
	public static void main(String args[]){
		prob(1,Integer.parseInt(args[0]),new Vector<Integer>());
		int g = gcd(ab-arr,ab);
		System.out.println(((ab-arr)/g)+"/"+(ab/g));
	}
	public static int gcd(int a,int b){
		return(b==0)?a:gcd(b,a%b);
	}
	public static void prob(int v,int lim,Vector<Integer> tengo){
		if(v==(lim+1)){
			ab++;
			if(tengo.get(tengo.size()-1)==lim)arr++;
			//System.out.println(tengo);
			return;
		}
		if(!tengo.contains(v) && tengo.size()>0){
			Vector<Integer> tmp = new Vector<Integer>();
			for(int n =0;n<tengo.size();n++)tmp.add(tengo.get(n));
			tmp.add(v);
			prob(v+1,lim,tmp);
			return;
		}
		for(int n = 1;n<=lim;n++)
			if(!tengo.contains(n)){
				Vector<Integer> tmp = new Vector<Integer>();
				for(int k = 0;k<tengo.size();k++)tmp.add(tengo.get(k));
				tmp.add(n);
				prob(v+1,lim,tmp);
			}
	}
}
