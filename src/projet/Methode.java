package projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Methode 
{
	public static void Lire(String M) throws IOException
	{
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1)
			{
				System.out.println(Lecteur + " sommets");
				compteur++;
			}
			else if (compteur == 2)
			{
				System.out.println(Lecteur + " arcs");
				compteur++;
			}
			else
			{
				String[] Ligne = Lecteur.split(" ");
				System.out.println(Ligne[0] + " -> " + Ligne[1]+ " = " + Ligne[2]);
			}
		}
		Bread.close();
	}
	
	public static int sommet(String M) throws IOException
	{
		int n = 0;
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1)
			{
				n = Integer.parseInt(Lecteur);
				compteur++;
			}
		}
		Bread.close();
		return n;
	}
	
	public static int arc(String M) throws IOException
	{
		int n = 0;
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1)
			{
				compteur++;
			}
			else if (compteur == 2)
			{
				n = Integer.parseInt(Lecteur);
				compteur++;
			}
		}
		Bread.close();
		return n;
	}
	
	
	public static void adjacence(String M) throws IOException
	{
		int n = Methode.arc(M);
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		String[][] T = new String[n][n];
		for (int i = 0 ; i < T.length ; i++)
		{
			for (int j=0 ; j < T[i].length ; j++)
			{
				T[i][j] = "    F";
			}
		}
		
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1 || compteur== 2)
			{
				compteur++;
			}
			else
			{
				String[] Ligne = Lecteur.split(" ");
				T[Integer.parseInt(Ligne[0])][Integer.parseInt(Ligne[1])]= "    V";
			}

		}
		int k=n;
		System.out.println("  Matrice d'adjacence");
		System.out.print(" ");
		while (k > 1 )
		{
			for( int z = 0 ; z < n ; z++)
			{
				if( z != n-1)
				{
					int a= n-k;
					System.out.print("    "+ a);
					k=k-1;
				}
				else
				{
					int a= n-k;
					System.out.println("    "+ a);
					k=k-1;
				}
			}
		}
		int u = n;
		while (u > 0)
		{
			int r= n-u;
			System.out.print(r);
			u=u-1;
			for (int x = 0 ; x < n ; x++)
			{
				if (x != n-1)
				{
				System.out.print(T[r][x]);
				}
				else
				{
					System.out.println(T[r][r]);
				}
			}
		}
		Bread.close();
	}
	
	
	
	public static void valeurs(String M) throws IOException
	{
		int n = Methode.arc(M);
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		String[][] T = new String[n][n];
		for (int i = 0 ; i < T.length ; i++)
		{
			for (int j=0 ; j < T[i].length ; j++)
			{
				T[i][j] = "    *";
			}
		}
		
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1 || compteur== 2)
			{
				compteur++;
			}
			else
			{
				String[] Ligne = Lecteur.split(" ");
				if (Ligne[2].length() == 1)
				{
					T[Integer.parseInt(Ligne[0])][Integer.parseInt(Ligne[1])]= "    "+ Ligne[2];
				}
				else
				{
					T[Integer.parseInt(Ligne[0])][Integer.parseInt(Ligne[1])]= "   "+ Ligne[2];
				}
			}
		}
		int k=n;
		System.out.println("");
		System.out.println("  Matrice des valeurs");
		System.out.print(" ");
		while (k > 1 )
		{
			for( int z = 0 ; z < n ; z++)
			{
				if( z != n-1)
				{
					int a= n-k;
					System.out.print("    "+ a);
					k=k-1;
				}
				else
				{
					int a= n-k;
					System.out.println("    "+ a);
					k=k-1;
				}
			}
		}
		int u = n;
		while (u > 0)
		{
			int r= n-u;
			System.out.print(r);
			u=u-1;
			for (int x = 0 ; x < n ; x++)
			{
				if (x != n-1)
				{
				System.out.print(T[r][x]);
				}
				else
				{
					System.out.println(T[r][r]);
				}
			}
		}
		Bread.close();
	}
	
	
	public static void circuit(String M) throws IOException
	{
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		ArrayList<Integer> Fin = new ArrayList<Integer>();
		ArrayList<Integer> Debut = new ArrayList<Integer>();
		
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1 || compteur== 2)
			{
				compteur++;
			}
			else
			{
				String[] Ligne = Lecteur.split(" ");
				Fin.add(Integer.parseInt(Ligne[1]));
				Debut.add(Integer.parseInt(Ligne[0]));
			}
		}
		
		ArrayList<Integer> val = new ArrayList<Integer>();
		for(int w = 0 ; w < Methode.sommet(M) ; w++)
		{
			val.add(w);
		}
		
		int v=0;
		
		while(Methode.entree(Debut, Fin).size() > 0)
		{
			System.out.println("Points d'entr�e:");
			System.out.println(Methode.entree(Debut, Fin));
			
			for(int i = 0 ; i < Methode.entree(Debut, Fin).size() ; i++)
			{
				for(int j =0 ; j < Debut.size(); j++)
				{
					for(int x=0 ; x< val.size(); x++)
					{
						if(val.get(x) == Methode.entree(Debut, Fin).get(i))
						{
							val.remove(x);
						}
					}
					if(Methode.entree(Debut, Fin).get(i) == Debut.get(j))
					{
						Debut.remove(j);
						Fin.remove(j);
					}
				}
			}
			System.out.println("Suppression des points d�entr�e");
			System.out.println("Sommets restant :");
			System.out.println(val);
		}
		
		if(val.size() != 0)
		{
			System.out.println("Points d'entr�e:");
			System.out.println("Aucun");
			System.out.println("Le graphe contient au moins un circuit.");
		}
		else
		{
			System.out.println("Points d'entr�e:");
			System.out.println("Aucun");
			System.out.println("Le graphe ne contient pas au moins un circuit.");
		}
	
		Bread.close();

	}
	
	
	
	
	public static void rang(String M) throws IOException
	{
		int s = Methode.sommet(M);
		int a = Methode.arc(M);
		int compteur=1;
		BufferedReader Bread = null;
		FileReader Fread = null;
		String Lecteur;
		Fread = new FileReader(M);
		Bread = new BufferedReader(Fread);
		
		ArrayList<Integer> Fin = new ArrayList<Integer>();
		ArrayList<Integer> Debut = new ArrayList<Integer>();
		ArrayList<Integer> NoA = new ArrayList<Integer>();
		
		Integer[][] Tab = new Integer[s][a];
		
		while ((Lecteur = Bread.readLine()) != null) 
		{
			if (compteur==1 || compteur== 2)
			{
				compteur++;
			}
			else
			{
				String[] Ligne = Lecteur.split(" ");
				
				Fin.add(Integer.parseInt(Ligne[1]));
				
				Debut.add(Integer.parseInt(Ligne[0]));
			}
		}
		
		ArrayList<Integer> Idsupp = new ArrayList<Integer>();
		Idsupp = Methode.suppId(Debut, Fin);
		//On supprime le circuit
		int z = 0 ;
		for ( int delete = 0 ; delete < Debut.size() ; delete ++)
		{
			int u = Idsupp.get(z);
			if ( u == delete)
			{
				Debut.remove(delete);
				Fin.remove(delete);
				z++;
			}
		}
		
		int count =0;
		ArrayList<Integer> val = new ArrayList<Integer>();
		for(int w = 0 ; w < Methode.sommet(M) ; w++)
		{
			val.add(w);
		}
		
		System.out.println(" M�thode d��limination des points d�entr�e");
		while(Debut.size() != 0 )
		{
			ArrayList<Integer> entree = new ArrayList<Integer>();
			entree = Methode.entree(Debut, Fin);
			System.out.println("Rang courant = " + count);
			System.out.println("Points d'entr�e :");
			System.out.println(entree);
			System.out.println("");
			count ++;
			for (int x = 0 ; x < Debut.size(); x++)
			{
				for ( int y = 0 ; y < entree.size(); y++)
				{
					if ( Debut.get(x) == entree.get(y))
					{
						for(int p = 0 ; p < val.size( ); p++)
						{
							if(Debut.get(x) == val.get(p))
							{
								val.remove(p);
							}
						}
						Debut.remove(x);
						Fin.remove(x);
						
						if (x != 0)
						{
							x = x - 1;
						}
					}
				}
			}
			
		}
		System.out.println("Rang courant = " + count);
		System.out.println("Points d'entr�e :");
		System.out.println(val);
		System.out.println("");
		
		
		Bread.close();
	}
	public static ArrayList<Integer> entree(ArrayList<Integer> Debut , ArrayList<Integer> Fin)
	{
		ArrayList<Integer> Afficher = new ArrayList<Integer>();
		int v = 0;
		for(int i = 0 ; i < Debut.size() ; i++)
		{
			for(int j = 0 ; j < Fin.size(); j++)
			{
				if (Debut.get(i) == Fin.get(j))
				{
					v = 1;
				}
			}
			if (v == 0)
			{
				Afficher.add(Debut.get(i));
			}
			else
			{
				v = 0;
			}
		}
		for ( int r = 0 ; r < Afficher.size(); r++)
		{
			for ( int s = 0 ; s < Afficher.size() ; s++)
			{
				if( r != s)
				{
					if ( Afficher.get(r) == Afficher.get(s))
					{
						Afficher.remove(s);
					}
				}
			}
		}
		return Afficher;
	}
	
	public static ArrayList<Integer> id(ArrayList<Integer> Debut , ArrayList<Integer> Fin)
	{
		ArrayList<Integer> Afficher = new ArrayList<Integer>();
		int v = 0;
		for(int i = 0 ; i < Debut.size() ; i++)
		{
			for(int j = 0 ; j < Fin.size(); j++)
			{
				if (Debut.get(i) == Fin.get(j))
				{
					v = 1;
				}
			}
			if (v == 0)
			{
				Afficher.add(i);
			}
		}
		return Afficher;
	}
	
	public static ArrayList<Integer> suppId(ArrayList<Integer> Debut , ArrayList<Integer> Fin) throws IOException
	{
		ArrayList<Integer> A = new ArrayList<Integer>();
		for(int i = 0 ; i < Debut.size(); i++)
		{
			for ( int j = 0 ; j < Debut.size(); j++)
			{
				if (Debut.get(i)==Fin.get(j))
				{
					if(Fin.get(i) == Debut.get(j))
					{
						A.add(j);
					}
				}
			}
		}
		int c = 0;
		for(int a = 0 ; a < A.size();a++)
		{
			if ( c == 1)
			{
				A.remove(a);
				a = a -1;
				c = 0;
			}
			c=1;
		}
		return A;
	}
}
