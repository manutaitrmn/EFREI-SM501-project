package projet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Methode 
{
	// Lecture du graphe
	public static void read(ArrayList<Integer[]> graph) throws IOException {
		System.out.println("* Lecture du graphe");
		for (int i = 0; i < graph.size(); i++) {
			if (i == 0) {
				System.out.println(graph.get(i)[0] + " sommets");
			} else if (i == 1) {
				System.out.println(graph.get(i)[0] + " arcs");
			} else {
				System.out.println(graph.get(i)[0] + " -> " + graph.get(i)[1] + " = " + graph.get(i)[2]);
			}
		}
	}
	
	public static int getVertices(ArrayList<Integer[]> graph) throws IOException {
		return graph.get(0)[0];
	}
	
	public static int getArcs(ArrayList<Integer[]> graph) throws IOException {
		return graph.get(1)[0];
	}
	
	
	// Calcul des sommets du graphe
	public static int sommet(String M) throws IOException {
		int n = 0;
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		while ((Lecteur = Bread.readLine()) != null) {
			if (compteur==1) {
				n = Integer.parseInt(Lecteur);
				compteur++;
			}
		}
		
		Bread.close();
		return n;
	}
	
	// Calcul des arcs du graphe
	public static int arc(String M) throws IOException {
		int n = 0;
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		while ((Lecteur = Bread.readLine()) != null) {
			if (compteur==1) {
				compteur++;
			} else if (compteur == 2) {
				n = Integer.parseInt(Lecteur);
				compteur++;
			}
		}
		
		Bread.close();
		return n;
	}
	
	// Creation de la matrice d'adjacence du graphe
	public static void adjacence(String M) throws IOException {
		int n = Methode.arc(M);
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		String[][] T = new String[n][n];
		for (int i = 0 ; i < T.length ; i++) {
			for (int j=0 ; j < T[i].length ; j++) {
				T[i][j] = "    F";
			}
		}
		
		while ((Lecteur = Bread.readLine()) != null) {
			if (compteur==1 || compteur== 2) {
				compteur++;
			} else {
				String[] Ligne = Lecteur.split(" ");
				T[Integer.parseInt(Ligne[0])][Integer.parseInt(Ligne[1])]= "    V";
			}

		}
		
		int k=n;
		System.out.println("  Matrice d'adjacence");
		System.out.print(" ");
		while (k > 1 ) {
			for( int z = 0 ; z < n ; z++) {
				if( z != n-1) {
					int a= n-k;
					System.out.print("    "+ a);
					k=k-1;
				} else {
					int a= n-k;
					System.out.println("    "+ a);
					k=k-1;
				}
			}
		}
		
		int u = n;
		while (u > 0) {
			int r= n-u;
			System.out.print(r);
			u=u-1;
			for (int x = 0 ; x < n ; x++) {
				if (x != n-1) {
				System.out.print(T[r][x]);
				} else {
					System.out.println(T[r][r]);
				}
			}
		}
		
		Bread.close();
	}
	
	// Creation de la matrice des valeurs du graphe
	public static void valeurs(String M) throws IOException {
		int n = Methode.arc(M);
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		String[][] T = new String[n][n];
		for (int i = 0 ; i < T.length ; i++) {
			for (int j=0 ; j < T[i].length ; j++) {
				T[i][j] = "    *";
			}
		}
		
		while ((Lecteur = Bread.readLine()) != null)  {
			if (compteur==1 || compteur== 2) {
				compteur++;
			} else {
				String[] Ligne = Lecteur.split(" ");
				if (Ligne[2].length() == 1) {
					T[Integer.parseInt(Ligne[0])][Integer.parseInt(Ligne[1])]= "    "+ Ligne[2];
				} else {
					T[Integer.parseInt(Ligne[0])][Integer.parseInt(Ligne[1])]= "   "+ Ligne[2];
				}
			}
		}
		
		int k=n;
		System.out.println("");
		System.out.println("  Matrice des valeurs");
		System.out.print(" ");
		while (k > 1 ) {
			for( int z = 0 ; z < n ; z++) {
				if( z != n-1) {
					int a= n-k;
					System.out.print("    "+ a);
					k=k-1;
				} else {
					int a= n-k;
					System.out.println("    "+ a);
					k=k-1;
				}
			}
		}
		
		int u = n;
		while (u > 0) {
			int r= n-u;
			System.out.print(r);
			u=u-1;
			for (int x = 0 ; x < n ; x++) {
				if (x != n-1) {
					System.out.print(T[r][x]);
				} else {
					System.out.println(T[r][r]);
				}
			}
		}
		
		Bread.close();
	}
	
	// Detection de circuits
	public static void circuit(String M) throws IOException {
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		ArrayList<Integer> Fin = new ArrayList<Integer>();
		ArrayList<Integer> Debut = new ArrayList<Integer>();
		
		while ((Lecteur = Bread.readLine()) != null) {
			if (compteur==1 || compteur== 2) {
				compteur++;
			}
			else {
				String[] Ligne = Lecteur.split(" ");
				Fin.add(Integer.parseInt(Ligne[1]));
				Debut.add(Integer.parseInt(Ligne[0]));
			}
		}
		
		ArrayList<Integer> val = new ArrayList<Integer>();
		for(int w = 0 ; w < Methode.sommet(M) ; w++) {
			val.add(w);
		}
		
		// int v=0;
		
		while(Methode.entree(Debut, Fin).size() > 0) {
			ArrayList<Integer> La = new ArrayList<Integer>();
			System.out.println("Points d'entree:");
			La = Methode.entree(Debut, Fin);
			System.out.println(Methode.entree(Debut, Fin));
			
			for(int i = 0 ; i < La.size() ; i++) {
				for(int j =0 ; j < Debut.size(); j++) {
					for(int x=0 ; x< val.size(); x++) {
						if(val.get(x) == La.get(i)) {
							val.remove(x);
						}
					}
					
					if(La.get(i) == Debut.get(j)) {
						Debut.remove(j);
						Fin.remove(j);
					}
				}
			}
			
			System.out.println("Suppression des points d'entree");
			System.out.println("Sommets restant :");
			System.out.println(val);
		}
		
		if(val.size() != 0) {
			System.out.println("Points d'entree:");
			System.out.println("Aucun");
			System.out.println("Le graphe contient au moins un circuit.");
		} else {
			System.out.println("Points d'entree:");
			System.out.println("Aucun");
			System.out.println("Le graphe NE contient PAS au moins un circuit.");
		}
	
		Bread.close();
	}
		
	// Calcul du rang des sommets
	public static void rang(String M) throws IOException {
		//int s = Methode.sommet(M);
		//int a = Methode.arc(M);
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		ArrayList<Integer> Fin = new ArrayList<Integer>();
		ArrayList<Integer> Debut = new ArrayList<Integer>();
		// ArrayList<Integer> NoA = new ArrayList<Integer>();
		
		// Integer[][] Tab = new Integer[s][a];
		
		while ((Lecteur = Bread.readLine()) != null) {
			if (compteur==1 || compteur== 2) {
				compteur++;
			} else {
				String[] Ligne = Lecteur.split(" ");
				Fin.add(Integer.parseInt(Ligne[1]));
				Debut.add(Integer.parseInt(Ligne[0]));
			}
		}
		
		System.out.println("Methode.suppId(Debut, Fin)" + Methode.suppId(Debut, Fin));
		ArrayList<Integer> Idsupp = new ArrayList<Integer>();
		Idsupp = Methode.suppId(Debut, Fin);
		//On supprime le circuit
		
		if (Idsupp.size() != 0 ) {
			int z = 0 ;
			for ( int delete = 0 ; delete < Debut.size() ; delete ++) {
				int u = Idsupp.get(z);
				if ( u == delete) {
					Debut.remove(delete);
					Fin.remove(delete);
					if (z != 0) {
						z = z - 1;
					}
				}
			}
		}
		
		System.out.println("Id" + Idsupp );
		
		int count =0;
		ArrayList<Integer> val = new ArrayList<Integer>();
		for(int w = 0 ; w < Methode.sommet(M) ; w++) {
			val.add(w);
		}
		
		System.out.println(" Méthode d'élimination des points d'entrée");
		
		while(Debut.size() != 0 ) 
		{
			ArrayList<Integer> entree = new ArrayList<Integer>();
			entree = Methode.entree(Debut, Fin);
			System.out.println("Rang courant = " + count);
			System.out.println("Points d'entree :");
			System.out.println(entree);
			System.out.println("");
			count ++;
			
			// int check =0;
			for (int x = 0 ; x < entree.size(); x++)
			{
				for ( int y = 0 ; y < Debut.size(); y++)
				{
					if ( Debut.get(y) == entree.get(x))
					{
						for(int p = 0 ; p < val.size( ); p++)
						{
							if(Debut.get(y) == val.get(p))
							{
								val.remove(p);
							}
						}
						Debut.remove(y);
						Fin.remove(y);
						y--;
					}
				}
		}
		}
		
		System.out.println("Rang courant = " + count);
		System.out.println("Points d'entree :");
		System.out.println(val);
		System.out.println("");
		Bread.close();
	}
	

	public static ArrayList<Integer> entree(ArrayList<Integer> Debut , ArrayList<Integer> Fin) {
		ArrayList<Integer> Afficher = new ArrayList<Integer>();
		int v = 0;
		for(int i = 0 ; i < Debut.size() ; i++) {
			for(int j = 0 ; j < Fin.size(); j++) {
				if (Debut.get(i) == Fin.get(j)) {
					v = 1;
				}
			}
			
			if (v == 0) {
				Afficher.add(Debut.get(i));
			} else {
				v = 0;
			}
		}
		
		for ( int r = 0 ; r < Afficher.size()-1; r++) {
			for ( int s = r + 1; s < Afficher.size() ; s++) {//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				
					if ( Afficher.get(r) == Afficher.get(s)) {
						Afficher.remove(s);
					}
				
			}
		}
		
		return Afficher;
	}
	
	public static ArrayList<Integer> sortie(ArrayList<Integer> Debut , ArrayList<Integer> Fin) {
		ArrayList<Integer> Afficher = new ArrayList<Integer>();
		int v = 0;
		for(int i = 0 ; i < Fin.size() ; i++) {
			for(int j = 0 ; j < Debut.size(); j++) {
				if (Debut.get(i) == Fin.get(j)) {
					v = 1;
				}
			}
			
			if (v == 0) {
				Afficher.add(Fin.get(i));
			} else {
				v = 0;
			}
		}
		
		for ( int r = 0 ; r < Afficher.size()-1; r++) {
			for ( int s = r+1 ; s < Afficher.size() ; s++) {
				
					if ( Afficher.get(r) == Afficher.get(s)) {
						Afficher.remove(s);
					}
	
			}
		}
		
		return Afficher;
	}
	
	
	public static ArrayList<Integer> id(ArrayList<Integer> Debut , ArrayList<Integer> Fin) {
		ArrayList<Integer> Afficher = new ArrayList<Integer>();
		int v = 0;
		for(int i = 0 ; i < Debut.size() ; i++) {
			for(int j = 0 ; j < Fin.size(); j++) {
				if (Debut.get(i) == Fin.get(j)) {
					v = 1;
				}
			}
			
			if (v == 0) {
				Afficher.add(i);
			}
		}
		
		return Afficher;
	}
	
	
	public static ArrayList<Integer> suppId(ArrayList<Integer> Debut , ArrayList<Integer> Fin) throws IOException {
		ArrayList<Integer> A = new ArrayList<Integer>();
		for(int i = 0 ; i < Debut.size(); i++) {
			for ( int j = 0 ; j < Debut.size(); j++) {
				if (Debut.get(i)==Fin.get(j)) {
					if(Fin.get(i) == Debut.get(j)) {
						A.add(j);
					}
				}
			}
		}
		
		int c = 0;
		for(int a = 0 ; a < A.size();a++) { // On supp les doublons
			if ( c == 1) {
				A.remove(a);
				a = a -1;
				c = 0;
			}
			c=1;
		}
		return A;
	}
	
	public boolean ordonnance(ArrayList<Integer> a)
	{
		if(a.size()==1)
			return true;
		return false;
	}
	


	
}
