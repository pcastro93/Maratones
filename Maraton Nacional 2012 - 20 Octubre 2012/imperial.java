import java.util.*;
import java.io.*;
public class imperial{
	public static HashMap<Character, Integer> mapeo = new HashMap<Character, Integer>();
	public static HashMap<Integer, String> mapeo2 = new HashMap<Integer,String>();
	public static int romanoDecimal(String input){
		int num= 0;
		int i=1;
		for(i=0;i<input.length()-1;)
			if(mapeo.get(input.charAt(i))>=mapeo.get(input.charAt(i+1)))
				num+=mapeo.get(input.charAt(i++));
			else{
				num+=(mapeo.get(input.charAt(i+1))-mapeo.get(input.charAt(i)));
				i+=2;
			}
		if(i!=input.length())
			num+=mapeo.get(input.charAt(input.length()-1));
		return num;
	}

	public static String decimalRomano(int num){
		String res = "";
		if(num>=1000)
			for(int i=0;i<num/1000;i++)
				res += "M";
		num%=1000;
		res+=mapeo2.get((num/100)*100);
		num%=100;
		res+=mapeo2.get((num/10)*10);
		num%=10;
		res+=mapeo2.get(num);
		return(res.equals(""))?"Nan":res;
	}
	public static void llenarMap(){
		mapeo.put('M', 1000); mapeo.put('D', 500); mapeo.put('C', 100); mapeo.put('L', 50); mapeo.put('X', 10); mapeo.put('V', 5); mapeo.put('I', 1); mapeo2.put(0, ""); mapeo2.put(1, "I"); mapeo2.put(2, "II"); mapeo2.put(3, "III"); mapeo2.put(4, "IV"); mapeo2.put(5, "V"); mapeo2.put(6, "VI"); mapeo2.put(7, "VII"); mapeo2.put(8, "VIII"); mapeo2.put(9, "IX"); mapeo2.put(10, "X"); mapeo2.put(20, "XX"); mapeo2.put(30, "XXX"); mapeo2.put(40, "XL"); mapeo2.put(50, "L"); mapeo2.put(60, "LX"); mapeo2.put(70, "LXX"); mapeo2.put(80, "LXXX"); mapeo2.put(90, "XC"); mapeo2.put(100, "C"); mapeo2.put(200, "CC"); mapeo2.put(300, "CCC"); mapeo2.put(400, "CD"); mapeo2.put(500, "D"); mapeo2.put(600, "DC"); mapeo2.put(700, "DCC"); mapeo2.put(800, "DCCC"); mapeo2.put(900, "CM"); mapeo2.put(1000, "M");
	}

	public static int[] solve(String mal, String bien){
		String or = new String(mal);
		String cor = new String(bien);
		int emin = 100000;//el maximo numero en el problema es 3999 entonces
		int cmin = 100000;//se puede poner este valor como infinito
		do{
			int e = or.length()-cor.length();
			int c = 0;
			for(int i=0;i<cor.length();i++){
				if(cor.charAt(i)==' ')
					e++;
				else if(cor.charAt(i)!=or.charAt(i)){
					e++;
					c++;
				}
			}
			if(e+c==emin+cmin && e<emin){
				emin = e;
				cmin = c;
			}
			else if(e+c < emin+cmin){
				emin = e;
				cmin = c;
			}
			cor=" "+cor;
		}while(or.length()>=cor.length());
		int res[] = {emin, cmin};
		return res;
	}

	public static void main(String jfkdfd[])throws Exception{
		llenarMap();
		//		BufferedReader br = new BufferedReader(new FileReader("imperial.in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("*")){
			String cad = decimalRomano(romanoDecimal(input));//bien escrito
			int[] res = solve(input, cad);
			System.out.println(res[0]+" "+res[1]);
		}
	}
}
