import java.io.*;
import java.util.*;
public class aliens{
	static int atoi(String n){return Integer.parseInt(n);}
	static String s;
	static int suffixArray[];
	static void preprocesar(int M[][], int A[]){
		int i, j, N = A.length;
		//initialize M for the intervals with length 1
		for (i = 0; i < N; i++) M[i][0] = i;
		//compute values from smaller to bigger intervals
		for (j = 1; (1 << j) <= N; j++)
			for (i = 0; i + (1 << j) - 1 < N; i++)
				if (A[M[i][j - 1]] < A[M[i + (1 << (j - 1))][j - 1]])
					M[i][j] = 
					M[i][j - 1];
				else
					M[i][j] = M[i + (1 << (j - 1))][j - 1];
	}
	static int query(int i, int j, int M[][], int A[]){
		//retorna la POSICION del minimo valor en el intervalo i, j
		//M es la matriz preprocesada, A es el arreglo de valores
		if(i>j){i^=j;j^=i;i^=j;}
		int k = (int)(Math.log(j-i+1)/Math.log(2));
		int v1 = A[M[i][k]], v2 = A[M[j-(1<<k)+1][k]];
		return (v1<=v2?M[i][k]:M[j-(1<<k)+1][k]);
	}
	static int lcp(int i,int j,int m[][], int lcp[], String s, int suffixArray[]){
		if(i==j) return s.length()-suffixArray[i];
		return lcp[query(i+1,j, m, lcp)];
	}
	static int[] calculateadjacentsuffixes(){
		int n = s.length();
		int Rank[] = new int[s.length()];
		int Height[] = new int[s.length()];
	    for (int i=0; i<n; ++i) Rank[suffixArray[i]] = i;
	    for(int c: Rank)System.out.print(c+" ");
	    System.out.println();
	    for(int c: suffixArray)System.out.print(c+" ");
	    System.out.println();
	    Height[0] = 0;
	    for (int i=0, h=0; i<n; ++i){
	        if (Rank[i] > 0){
	            int j = suffixArray[Rank[i]-1];
	            while (i + h < n && j + h < n && s.charAt(i+h) == s.charAt(j+h)){
	                h++;
	            }
	            Height[Rank[i]] = h;
	            if (h > 0) h--;
	        }
	    }
	    return Height;
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		SuffixArray sa = new SuffixArray(s=br.readLine());
        sa.compute();
        suffixArray = new int[sa.suffixes.length];
        for(int i=0;i<suffixArray.length;i++)
        	suffixArray[i] = sa.suffixes[i];
        int lcp[] = calculateadjacentsuffixes();
        for(int i=0;i<s.length();i++){
        	System.out.println(s.substring(suffixArray[i])+" "+lcp[i]);
        }
		/*while((input = br.readLine())!=null && !input.equals("0")){
		int mt = atoi(input);
		s = br.readLine();
		SuffixArray sa = new SuffixArray(s);
        sa.compute();
        //int lcp[] = sa.lcps;
        suffixArray = new int[sa.suffixes.length];
        for(int i=0;i<suffixArray.length;i++)
        	suffixArray[i] = sa.suffixes[i];
        int lcp[] = calculateadjacentsuffixes();
        int m[][]= new int[lcp.length][(int)Math.ceil(Math.log(lcp.length)/Math.log(2))+1];
        preprocesar(m, lcp);
        int larga = 0;//longitud de la cadena mas larga que se repite minimo mt veces
        for(int i=0;i+mt<=s.length();i++)
        	larga = Math.max(larga, lcp(i, i+mt-1, m, lcp, s, suffixArray));
        if(larga==0){
        	System.out.println("none");
        	continue;
        }
        int pos = -1;
        for(int i=0;i<suffixArray.length;){//cada uno de los sufijos
        	if(suffixArray[i]+larga>s.length()){i++;continue;}
        	int j=i;
        	int max = 0;
        	while(j<suffixArray.length && lcp(i, j, m , lcp, s, suffixArray)>=larga){
        		max = Math.max(max, suffixArray[j]);//de todos los que se repiten minimo mt veces cual es la que esta a la derecha
        		j++;
        	}
        	if(j-i>=mt)//se repite el minimo numero de veces
        		pos = Math.max(pos, max);
        	i=j;
        }
	        System.out.println(larga+" "+pos);
	}*/
	}
}

class SuffixArray {
    public  String str;
    public  int[] curbuckets;
    public  Integer[] suffixes;
    public int[] lcps;
    public  int len;
    public  int numBuckets;
    public  SuffixComparator cmp;
    public  List<int[]> allBuckets;
     
    public SuffixArray(String str) {
        this.str = str;
        this.len = str.length();
        this.suffixes = new Integer[len];
        this.lcps = new int[len];
        this.cmp = new SuffixComparator();
        this.allBuckets = new ArrayList<int[]>();
    }
     
    public void compute() {
        computeSuffixArray();
        //computeLcp();
    }
    private void computeSuffixArray() {
        //construct suffix array
        for(int i = 0; i < len; i++) {
            suffixes[i] = i;
        }
        cmp.setT(0);
        Arrays.sort(suffixes, cmp);
        updateBuckets();
        //System.out.println(this);
        for(int t = 1; numBuckets < len ; t *= 2) {
            cmp.setT(t);
            Arrays.sort(suffixes, cmp);
            //System.out.println(t+" "+numBuckets);
            updateBuckets();
            //System.out.println("---------------------");
            //System.out.println(this);
        }
    }
     
    private void updateBuckets() {
        numBuckets = 1;
        int[] newbuckets = new int[len];
        allBuckets.add(newbuckets);
        newbuckets[suffixes[0]] = numBuckets-1;
        //System.out.println("---");
        for(int i = 1; i < len; i++) {
        	//System.out.println(i);
            if(cmp.compare(suffixes[i-1], suffixes[i]) != 0) {
                numBuckets++;
            }
            newbuckets[suffixes[i]] = numBuckets-1;
        }
        curbuckets = newbuckets;
    }
    //nlgnlgn
    private void computeLcp() {
        for(int i = 1; i < len; i++) {
        	System.out.println(i);
            lcps[i] = computeLcp(suffixes[i-1],suffixes[i]);
        }
    }
    //lgnlgn
    private int computeLcp(int s1, int s2) {
    	//System.out.println("--- "+s1+" "+s2);
        if(s1 >= len || s2 >= len) {
            return 0;
        }
        int lcp = 0;
        int preT = 0;
        int t = 1;
        for(int i = 0; i < allBuckets.size(); i++) {
            int[] buckets = allBuckets.get(i);
            //System.out.println(i+" "+(buckets[s1] == buckets[s2]));
            if(buckets[s1] == buckets[s2]) {
                lcp += t-preT;
                preT = t;
                t *= 2;
            }else if (i == 0) {
                break;
            }else{
                //the search will be within t/2 -- t characters
                //because s1+t/2 and s2+t/2 are garanteed to be different
                lcp += computeLcp(s1+t/2, s2+t/2);
            }
        }
        return lcp;
    }
     
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sb.append(suffixes[i]);
            sb.append(":");
            sb.append(str.substring(suffixes[i]));
            sb.append('\n');
            sb.append("lcp: ");
            sb.append(lcps[i]);
            sb.append('\n');
        }
        return sb.toString();
    }
     
    class SuffixComparator implements Comparator<Integer> {
        private int t;
         
        public void setT(int t) {
            this.t = t;
        }
         
        @Override
        public int compare(Integer s1, Integer s2) {
            if(t == 0)
                return str.charAt(s1) - str.charAt(s2);
            else {
                if(curbuckets[s1] == curbuckets[s2]) {
                    if(s1+t >= len)
                        return -1;
                    else if(s2+t >= len)
                        return 1;
                    else 
                        return curbuckets[s1+t]-curbuckets[s2+t];
                    
                }else
                    return curbuckets[s1]-curbuckets[s2];
                
            }
        }
    }
}