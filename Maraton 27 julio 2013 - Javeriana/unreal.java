import java.io.*;
import java.util.*;
public class unreal{
	static int atoi(String n){return Integer.parseInt(n);}
	static double atod(String n){return Double.parseDouble(n);}
	static double calcArea(double xant, double xact, TreeSet<seg> tss){
		//System.out.println("Calculo area "+tss);
		double ans =0.0;
		if(tss.isEmpty())return ans;
		//System.out.println("xact "+xact+" xant "+xant);
		Iterator<seg> it = tss.iterator();
		double izq = -10000, der = -10000;
		ArrayList<double[]> inters = new ArrayList<double[]>();
		while(it.hasNext()){
			seg stmp = it.next();
			if(izq==-10000){//la primera vez
				izq = stmp.y1;//abajo
				der = stmp.y2;//arriba
			}else if(new seg(0.0, izq, 0.0, der, -1).cont(stmp) || stmp.cont(new seg(0.0, izq, 0.0, der,-1))){//union de segmentos
				izq = Math.min(izq, stmp.y1);//abajo
				der = Math.max(der, stmp.y2);//arriba
			}else{//segmento separado
				double tmp[] = {izq, der};
				inters.add(tmp);
				izq = stmp.y1;//abajo
				der = stmp.y2;//arriba
			}
		}
		double tmp[] = {izq, der};
		inters.add(tmp);
		for(double[] arr:inters)
			ans+=Math.abs(arr[0]-arr[1])*Math.abs(xant-xact);
		//System.out.println("retorno "+ans);
		return ans;
	}
	public static void main(String jfkd[])throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			int n = atoi(input);
			if(n==0)break;
			cuad[] cs = new cuad[n];
			seg[] ss = new seg[n*2];//izq y der de cada rectangulo
			int cont =0, cont2=0;
			while(n-->0){
				StringTokenizer st = new StringTokenizer(br.readLine());
				double x1 = atod(st.nextToken()), y1 = atod(st.nextToken()), x2 = atod(st.nextToken()), y2 = atod(st.nextToken());
				seg s1 = new seg(x1,y1,x1,y2,cont);//izquierda
				seg s2 = new seg(x2,y1,x2,y2,cont);//derecha
				seg s3 = new seg(x1,y2,x2,y2,cont);//arriba
				seg s4 = new seg(x1,y1,x2,y1,cont);//abajo
				seg sar[] = {s1, s2, s3, s4};
				cs[cont++] = new cuad(sar);
				ss[cont2++]= s1;
				ss[cont2++]= s2;
			}
			Arrays.sort(ss);
			boolean activos[] = new boolean[cs.length];
			double xant =0, ans =0;
			TreeSet<seg> tss = new TreeSet<seg>(new comp());
			for(int i=0;i<ss.length;i++){//ordenadamente por x
				ans+=calcArea(xant, ss[i].x1,tss);//calculo el area con los cuadrados activos
				do{
					if(activos[ss[i].pos]){//si acabo de descubrir un segmento derecho lo quito
						activos[ss[i].pos] = false;
						tss.remove(cs[ss[i].pos].s[0]);//busco el segmento izquierdo ya que tengo el derecho
						//System.out.println("quito "+cs[ss[i].pos].s[0]);
					}
					else{//si acabo de descubrir un segmento izquierdo lo agrego
						activos[ss[i].pos] = true;
						tss.add(ss[i]);
						//System.out.println("descubro "+ss[i]);
						//br.readLine();
					}
				}while(i+1<ss.length && ss[i].x1==ss[i+1].x1 && i++>-1);//estan en linea
				xant = ss[i].x1;
				//aumento a i solo si se cumplen las anteriores condiciones y repite
			}
			System.out.println(String.format("%.2f", ans));
			/*for(cuad ccc:cs)
				System.out.println(ccc);
			for(seg sss: ss)
				System.out.println(sss);*/
		}
	}
}

class comp implements Comparator<seg>{
	public int compare(seg s1, seg s2){
		if(s1.y1==s2.y1)
			return (int)Math.signum(s1.y2 - s2.y2);
		return (int)Math.signum(s1.y1-s2.y1);
	}
}
class seg implements Comparable<seg>{
	double x1, y1, x2, y2;
	int pos;
	public seg(double x1, double y1, double x2, double y2, int pos){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.pos = pos;
	}
	public boolean cont(seg s2){
		//s2 esta en este segmento
		return this.y1<=s2.y1 && this.y2 >=s2.y1;
	}
	public int compareTo(seg o){
		return (int)Math.signum(this.x1 - o.x1);
	}
	public String toString(){
		return x1+" "+y1+" "+x2+" "+y2+" "+pos;
	}
	@Override
	public boolean equals(Object o){
		seg s2 = (seg)o;
		return s2.pos==this.pos;
	}
}
class cuad{
	seg s[];
	public cuad(seg ss[]){
		this.s = Arrays.copyOf(ss, ss.length);
	}
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		for(seg ss:s)
			sb.append(ss.toString()).append("\n");
		return sb.toString();
	}
}
