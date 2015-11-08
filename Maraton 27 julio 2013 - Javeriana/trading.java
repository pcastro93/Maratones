import java.io.*;
import java.util.*;

public class trading {
	static int atoi(String n) {
		return Integer.parseInt(n);
	}

	static void preprocesar(int M[][], int A[]) {
		int i, j, N = A.length;
		for (i = 0; i < N; i++)
			M[i][0] = i;
		for (j = 1; (1 << j) <= N; j++)
			for (i = 0; i + (1 << j) - 1 < N; i++)
				if (A[M[i][j - 1]] < A[M[i + (1 << (j - 1))][j - 1]])
					M[i][j] = M[i][j - 1];
				else
					M[i][j] = M[i + (1 << (j - 1))][j - 1];
	}

	static int query(int i, int j, int M[][], int A[]) {
		int k = (int) (Math.log(j - i + 1) / Math.log(2));
		int v1 = A[M[i][k]], v2 = A[M[j - (1 << k) + 1][k]];
		return (v1 <= v2 ? M[i][k] : M[j - (1 << k) + 1][k]);
	}

	static int lcp(int i, int j, int m[][], int lcp[], String s, int suffixArray[]) {
		if (i > j) {
			i ^= j;
			j ^= i;
			i ^= j;
		}
		if (i == j)
			return s.length() - suffixArray[i];
		return lcp[query(i + 1, j, m, lcp)];
	}

	public static void main(String fdjkfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while ((input = br.readLine()) != null && !input.equals("*")) {
			String str = input;
			int q = atoi(br.readLine());
			SuffixArray sufa = new SuffixArray(str);
			sufa.compute();
			Integer sattt[] = sufa.suffixes;
			int sa[]= new int[sattt.length];
			for(int i =0;i<sa.length;i++)
				sa[i] = sattt[i];
			int[] lcp = sufa.lcps;
			int m[][] = new int[lcp.length][(int)Math.ceil(Math.log(lcp.length)/Math.log(2))+1];
			preprocesar(m, lcp);
			int Rank[] = new int[str.length()];
			for (int i = 0; i < sa.length; i++)
				Rank[sa[i]] = i;
//			for(int i=0;i<sa.length;i++){
//				System.out.println(str.substring(sa[i]) + " "+lcp[i]);
//			}
			while(q-->0){
				String div[] = br.readLine().split(" ");
				int a = atoi(div[0]), b=  atoi(div[1]);
//				System.out.println(Rank[a]+" "+Rank[b]);
				int res = lcp(Rank[a], Rank[b], m, lcp, str,sa);
				//System.out.println(res);
				sb.append(res).append("\n");
			}
			
		}
		System.out.print(sb);
	}
}

class SuffixArray {
	public String str;
	public int[] curbuckets;
	public Integer[] suffixes;
	public int[] lcps;
	public int len;
	public int numBuckets;
	public SuffixComparator cmp;
	public List<int[]> allBuckets;

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
		computeLcp();
	}

	private void computeSuffixArray() {
		for (int i = 0; i < len; i++)
			suffixes[i] = i;
		cmp.setT(0);
		Arrays.sort(suffixes, cmp);
		updateBuckets();
		for (int t = 1; numBuckets < len; t *= 2) {
			cmp.setT(t);
			Arrays.sort(suffixes, cmp);
			updateBuckets();
		}
	}

	private void computeLcp() {
		int n = str.length();
		int Rank[] = new int[str.length()];
		int Height[] = new int[str.length()];
		for (int i = 0; i < n; i++)
			Rank[suffixes[i]] = i;
		Height[0] = 0;
		for (int i = 0, h = 0; i < n; i++) {
			if (Rank[i] > 0) {
				int j = suffixes[Rank[i] - 1];
				while (i + h < n && j + h < n
						&& str.charAt(i + h) == str.charAt(j + h)) {
					h++;
				}
				Height[Rank[i]] = h;
				if (h > 0)
					h--;
			}
		}
		lcps = Height;
	}

	private void updateBuckets() {
		numBuckets = 1;
		int[] newbuckets = new int[len];
		allBuckets.add(newbuckets);
		newbuckets[suffixes[0]] = numBuckets - 1;
		for (int i = 1; i < len; i++) {
			if (cmp.compare(suffixes[i - 1], suffixes[i]) != 0) {
				numBuckets++;
			}
			newbuckets[suffixes[i]] = numBuckets - 1;
		}
		curbuckets = newbuckets;
	}

	class SuffixComparator implements Comparator<Integer> {
		private int t;

		public void setT(int t) {
			this.t = t;
		}

		public int compare(Integer s1, Integer s2) {
			if (t == 0)
				return str.charAt(s1) - str.charAt(s2);
			else {
				if (curbuckets[s1] == curbuckets[s2]) {
					if (s1 + t >= len)
						return -1;
					else if (s2 + t >= len)
						return 1;
					else
						return curbuckets[s1 + t] - curbuckets[s2 + t];
				} else
					return curbuckets[s1] - curbuckets[s2];
			}
		}
	}
}