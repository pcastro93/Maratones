import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class outofcontext {

	static int atoi(String n) {
		return Integer.parseInt(n);
	}

	static boolean minus(char c) {
		return (c >= 97 && c <= 122);
	}

	static boolean mayus(char c) {
		return !minus(c);
	}

	static char nuevaRegla(Set<Character> conj) {
		// trato de que las reglas no sean letras minusculas
		char r = 'A';
		while (conj.contains(r))
			r += (minus((char) (r + 1))) ? 27 : 1;
		return r;
	}

	static void imp(HashMap<Character, ArrayList<String>> rules) {
		for (Character var : rules.keySet())
			for (String s : rules.get(var))
				System.out.println(var + "->" + s);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Character, ArrayList<String>> rules = new HashMap<Character, ArrayList<String>>();
		int numr = atoi(br.readLine());// numero de reglas que me van a dar
		String vars = "";
		char inicial = ' ', s0 = ' ';
		while (numr-- > 0) {// asi no tengo que crear otra variable :)
			// las reglas deben ser de la forma A -> ABCDEF
			String input = br.readLine().replaceAll(" ", "");
			char let = input.charAt(0);
			String rule = input.substring(3);
			// la primera que me dan es la inicial
			if (inicial == ' ')
				inicial = let;
			if (rule.equals(""))// si es epsilon lo marco como arroba
				rule = "@";
			if (!rules.containsKey(let)) {
				rules.put(let, new ArrayList<String>());
				vars += let;
			}
			rules.get(let).add(rule);
		}
		// System.out.println("cfg inicial");
		// imp(rules);
		// System.out.println();
		// trato los epsilon
		boolean seguir = true;
		while (seguir) {
			seguir = false;
			int indice = -1;
			char re = ' ';
			for (Character var : rules.keySet())
				for (int i = 0; i < rules.get(var).size(); i++)
					if (rules.get(var).get(i).equals("@")) {
						re = var;
						indice = i;
						seguir = true;
					}
			if (!seguir)
				break;
			// elimino la regla que apunta a epsilon y hago las combinaciones
			// posibles donde aparecia la regla
			rules.get(re).remove(indice);
			for (Character var : rules.keySet())
				for (int i = 0; i < rules.get(var).size(); i++)
					if (rules.get(var).get(i).contains("" + re)) {
						ArrayList<Integer> ocurr = new ArrayList<Integer>();
						String cad = rules.get(var).get(i);
						for (int j = 0; j < cad.length(); j++)
							if (cad.charAt(j) == re)
								ocurr.add(j);
						// 0 es que reemplazo el epsilon, no pongo la variable
						// no tengo en cuenta 1<<veces-1 porque ese ya esta
						int veces = ocurr.size();
						for (int j = 0; j < (1 << veces) - 1; j++) {
							String bin = Integer.toBinaryString(j);
							while (bin.length() < veces)
								bin = "0" + bin;
							String nueva = new String(cad);
							for (int k = veces - 1; k > -1; k--)
								if (bin.charAt(k) == '0')
									nueva = nueva.substring(0, ocurr.get(k))
											+ nueva.substring(ocurr.get(k) + 1);
							if (!rules.get(var).contains(nueva)
									&& nueva.length() > 0)// por si da otro
															// epsilon
								rules.get(var).add(nueva);
						}
					}
		}
		// System.out.println("despues de tratar los epsilons");
		// imp(rules);
		// System.out.println();
		// quito las minusculas o como se llamen en la gramatica
		seguir = true;
		while (seguir) {
			seguir = false;
			char re = ' ';
			char apu = ' ';// al que lo va a mandar la regla
			ciclo: for (Character var : rules.keySet()) {
				for (String s : rules.get(var))
					for (int i = 0; i < s.length(); i++)
						if (minus(s.charAt(i)) && s.length() > 1) {
							apu = s.charAt(i);
							seguir = true;
							break ciclo;
						}
			}
			if (!seguir)
				break;
			// reemplazo la minuscula por la nueva regla y agrego la nueva regla
			boolean existe = false;
			ciclo: for (Character var : rules.keySet())
				for (String s : rules.get(var))
					if (s.length() == 1 && s.charAt(0) == apu && var != inicial) {
						re = var;
						existe = true;
						break ciclo;
					}
			boolean ellamisma = false;
			if(existe){
				for(String s: rules.get(re))
					if(s.length()>1 && s.indexOf(apu)!=-1){//si pasa que A->Aa, A->a, entonces creo otra regla
						ellamisma = true;
						break;
						}
			}
			if (!existe || ellamisma)
				re = nuevaRegla(rules.keySet());
			// System.out.println("apu "+apu);
			// System.out.println("re "+re);
			for (Character var : rules.keySet())
				for (int i = 0; i < rules.get(var).size(); i++)
					if (!(rules.get(var).get(i).length() == 1 && rules.get(var)
							.get(i).charAt(0) == apu))// si es la regla que
														// apunta a la minuscula
														// no la toco
						rules.get(var).set(
								i,
								rules.get(var).get(i)
										.replaceAll(apu + "", re + ""));
			if (!existe || ellamisma) {
				vars += re;
				rules.put(re, new ArrayList<String>());
				rules.get(re).add(apu + "");
			}
		}
		// System.out.println("despues de reemplazar las minusculas");
		// imp(rules);
		// System.out.println();
		// antes de quitar las unitarias elimino las que se apuntan a si mismas
		for (Character var : rules.keySet()) {
			int i = rules.get(var).size() - 1;
			while (i > -1 && i < rules.get(var).size()) {
				if (rules.get(var).get(i).length() == 1
						&& rules.get(var).get(i).charAt(0) == var)
					rules.get(var).remove(i);
				i--;
			}
		}
//		System.out.println("despues de quitar las reglas que se apuntan a si mismas");
//		imp(rules);
//		System.out.println();
		// quito las reglas unitarias
		seguir = true;
		while (seguir) {
			seguir = false;
			char re = ' ', apu = ' ';
			int indice = -1;
			ciclo: for (Character var : rules.keySet()) {
				for (int i = 0; i < rules.get(var).size(); i++)
					if (rules.get(var).get(i).length() == 1
							&& mayus(rules.get(var).get(i).charAt(0))) {
						re = var;
						apu = rules.get(var).get(i).charAt(0);
						indice = i;
						seguir = true;
						break ciclo;
					}
			}
			if (!seguir)
				break;
			// System.out.println("re "+re);
			// System.out.println("apu "+apu);
			// elimino la regla unitaria y anado
			if (re == apu) {
				rules.get(re).remove(indice);
				continue;
			}
			rules.get(re).remove(indice);
			rules.get(re).addAll(rules.get(apu));
			// imp(rules);
			// System.out.println();
		}
		// System.out.println("despues de quitar las reglas unitarias");
		// imp(rules);
		// System.out.println();

		// lo dejo en forma de chomsky...de a dos
		seguir = true;
		while (seguir) {
			seguir = false;
			char re = ' ';
			String apu = "";
			for (Character var : rules.keySet())
				for (String s : rules.get(var))
					if (s.length() > 2) {
						// ojo...yo cojo los dos primeros y verifico que
						// si ya hay una regla que ya apunte a esas 2
						apu = s.substring(0, 2);
						seguir = true;
					}
			if (!seguir)
				break;
			boolean existe = false;
			ciclo: for (Character var : rules.keySet())
				for (String s : rules.get(var))
					if (s.length() == 2 && s.indexOf(apu) != -1 && var != s0) {
						re = var;
						existe = true;
						break ciclo;
					}
			if (!existe)
				re = nuevaRegla(rules.keySet());
			// reemplazo en donde aparece la regla y anado la regla
			for (Character var : rules.keySet())
				for (int i = 0; i < rules.get(var).size(); i++)
					if (existe
							&& !(rules.get(var).get(i).length() == 2 && var == re)
							&& rules.get(var).get(i).length() != 2)// en caso de
																	// que ya
																	// exista la
																	// regla, no
																	// toco la
																	// que la
																	// genera
						rules.get(var).set(i,
								rules.get(var).get(i).replaceAll(apu, "" + re));
			if (!existe) {
				vars += re;
				rules.put(re, new ArrayList<String>());
				rules.get(re).add(apu);
			}
		}
		int ttt = 0;
		for (Character var : rules.keySet())
			ttt += rules.get(var).size();
		// System.out.println("despues de dejarlo en parejas");
		// imp(rules);
		// System.out.println();
		// System.out.println("final");
		// System.out.println(inicial);
		// System.out.println(ttt);
		// imp(rules);
		// System.out.println();

		// CYK
		String str = "";
		while ((str = br.readLine()) != null) {
			int n = str.length();
			int r = rules.keySet().size();
			boolean P[][][] = new boolean[n][n + 1][r];
			for (Character var : rules.keySet())
				for (String s : rules.get(var)) {
					int pos = 0;
					while (s.length() == 1 && pos < str.length()
							&& (pos = str.indexOf(s, pos)) != -1)
						P[pos++][1][vars.indexOf(var)] = true;
				}
			for (int i = 2; i < n + 1; i++)
				for (int j = 0; j < n - i + 1; j++)
					for (int k = 0; k < i; k++)
						for (Character var : rules.keySet())
							for (String s : rules.get(var))
								if (s.length() > 1) {
									int A = vars.indexOf(var);
									int B = vars.indexOf(s.charAt(0));
									int C = vars.indexOf(s.charAt(1));
									P[j][i][A] = (P[j][k][B] && P[j + k][i - k][C]) ? true
											: P[j][i][A];
								}
			String res = "";
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n + 1; j++)
					if (P[i][j][vars.indexOf(inicial)] && i + j <= n
							&& j > res.length())
						res = str.substring(i, i + j);
			System.out.println((res.length() == 0 ? "NONE" : res));
		}

	}

}
