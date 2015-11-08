import java.io.*;
import java.util.*;

public class dusk {
	static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static void main(String jfkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int test = atoi(br.readLine());
		int cas=0;
		while (test-- > 0) {
			int aristas = atoi(br.readLine());
			ArrayList<String> nods = new ArrayList<String>();
			ArrayList<ArrayList<Arista>> g = new ArrayList<ArrayList<Arista>>();
			for (int i = 0; i < aristas; i++) {
				String imparr[] = br.readLine().split(" ");
				int desde = -1, hasta = -1, hora = atoi(imparr[2]), dura = atoi(imparr[3]);
				//if ((hora > 6 && hora < 18) || ((hora + dura) % 24 > 6 && (hora + dura) % 24 < 18))
				//	continue;
				int hora2 = hora+(hora<=6?24:0);
				if(!(hora2>=18 && (hora2 + dura)<=30))
					continue;
				if (!nods.contains(imparr[0])) {
					nods.add(imparr[0]);
					desde = nods.size() - 1;
					g.add(new ArrayList<Arista>());
				} else
					desde = nods.indexOf(imparr[0]);

				if (!nods.contains(imparr[1])) {
					nods.add(imparr[1]);
					hasta = nods.size() - 1;
					g.add(new ArrayList<Arista>());
				} else
					hasta = nods.indexOf(imparr[1]);
				g.get(desde).add(new Arista(desde, hasta, hora%24, dura));
			}
			int min[][] = new int[24][g.size()];
			for(int i=0;i<min.length;i++)
				Arrays.fill(min[i], Integer.MAX_VALUE);
			String imparr[] = br.readLine().split(" ");
			int desde = atoi(nods.indexOf(imparr[0]) + "");
			int hasta = atoi(nods.indexOf(imparr[1]) + "");
			//System.out.println("Grafo "+g);
			int res = Integer.MAX_VALUE;
			if (!(desde == -1 || hasta == -1)) {
				PriorityQueue<Nodo> q = new PriorityQueue<Nodo>();
				min[18][desde] = 0;
				q.offer(new Nodo(desde, 18, 0));
				//System.out.println("desde "+desde);
				while (!q.isEmpty()) {
					Nodo act = q.poll();
					//System.out.println("Saca "+nods.get(act.nod));
					if (act.nod == hasta){
						res = act.dist;
						break;
						}
					for (Arista a : g.get(act.nod)) {
						//System.out.println("Arista a "+nods.get(a.hasta));
						int h1 = act.hora+(act.hora<=6?24:0);
						int h2 = a.hora+(a.hora<=6?24:0);
						int pasaotro = (h1 > h2) ? 1 : 0;
						//System.out.println("act.hora " +act.hora +" a.hora "+a.hora +" pasaotro "+pasaotro);
						if (min[(a.hora+a.dura)%24][a.hasta] > min[act.hora][act.nod] + pasaotro && min[act.hora][act.nod]!=Integer.MAX_VALUE) {
							min[(a.hora+a.dura)%24][a.hasta] = min[act.hora][act.nod] + pasaotro;
							//System.out.println("Cambia dist a "+ nods.get(a.hasta)+" "+min[(a.hora+a.dura)%24][a.hasta]);
							q.add(new Nodo(a.hasta, (a.hora+a.dura)%24, min[(a.hora+a.dura)%24][a.hasta]));
						}
					}
				}
			}
			//for(int i=0;i<min.length;i++)
			//	System.out.println("min a "+nods.get(i)+" "+min[i]);
			System.out.println("Test Case "+ ++cas +".");
			if(imparr[0].equals(imparr[1]))
				System.out.println("Vladimir needs 0 litre(s) of blood.");
			else if (desde==-1|| hasta==-1 || res == Integer.MAX_VALUE)
				System.out.println("There is no route Vladimir can take.");
			else
				System.out.println("Vladimir needs " + res + " litre(s) of blood.");
		}
	}
}

class Arista {
	int desde, hasta, hora, dura;

	Arista(int desde, int hasta, int hora, int dura) {
		this.desde = desde;
		this.hasta = hasta;
		this.hora = hora;
		this.dura = dura;
	}

	public String toString() {
		return this.desde + " " + hasta + " " + hora + " " + dura;
	}
}

class Nodo implements Comparable<Nodo> {
	int nod, hora, dist;

	public Nodo(int nod, int hora, int dist) {
		this.nod = nod;
		this.hora = hora;
		this.dist = dist;
	}

	public int compareTo(Nodo o) {
		return this.dist - o.dist;
	}
	public String toString(){
		return nod+" "+hora+" "+dist;
	}
}