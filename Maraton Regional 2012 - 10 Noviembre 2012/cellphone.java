import java.io.*;
import java.util.*;
public class cellphone{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfd[]) throws Exception{
		//uva cellphone typing 12526
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder rrr = new StringBuilder();
		while((input = br.readLine())!=null && !input.equals("")){
		tri t = new tri();
		int tam = atoi(input.trim());
		ArrayList<String> dic = new ArrayList<String>();
		for(int i=0;i<tam;i++){
			input = br.readLine().trim();
			dic.add(input);
			StringBuilder s = new StringBuilder(input);
			t.addWord(s);
		}
		int suma = 0;
		for(int i=0;i<dic.size();i++){
			StringBuilder s = new StringBuilder(dic.get(i));
			suma+=t.strokes(s,0);
		}
		String res = String.format("%.2f",((double)suma/(double)dic.size())).replaceAll(",",".");
		rrr.append(res).append("\n");
		}
		System.out.print(rrr);
	}
}

class tri{
	tri child[];
	int words, prefixes;
	public tri(){
		this.child = new tri[26];//letras abecedario
		this.words = 0;
		this.prefixes = 0;
	}
	void addWord(StringBuilder s){
		if(s.toString().equals(""))
			this.words ++;
		else{
			int k = s.charAt(0)-'a';//minusculas!
			s.deleteCharAt(0);
			if(child[k]==null)
				child[k] = new tri();
			child[k].prefixes++;
			child[k].addWord(s);
		}
	}
	int countWords(StringBuilder s){
		if(s.toString().equals(""))
			return this.words;
		int k = s.charAt(0)-'a';
		s.deleteCharAt(0);
		if(child[k]==null)
			return 0;
		return child[k].countWords(s);
	}
	int countPrefixes(StringBuilder s){//num words have given prefix
		if(s.toString().equals(""))
			return this.prefixes;
		int k = s.charAt(0)-'a';
		s.deleteCharAt(0);
		if(child[k]==null)
			return 0;
		return child[k].countPrefixes(s);
	}
	int strokes(StringBuilder s, int cont){
		if(s.toString().equals(""))
			return cont;
		int k = s.charAt(0)-'a';
		s.deleteCharAt(0);
		if(child[k]==null)
			return 0;
		return child[k].strokes(s, cont+
			((prefixes==child[k].prefixes)?0:1)
		);//debo presionar una tecla
	}
}