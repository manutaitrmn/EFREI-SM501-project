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
		int n = Methode.sommet(M);
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
		System.out.println("Matrice d'adjacence");
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
		int n = Methode.sommet(M);
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
		System.out.println("Matrice des valeurs");
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
	
	
	public static void circuit(String M)
	{
		
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
		
		for ( int delete = 0 ; delete < Idsupp.size() ; delete ++)
		{
			Debut.remove(Idsupp.get(delete));
			Fin.remove(Idsupp.get(delete));
		}
		
		
		int count =0;
		
		int verif =0;
		
		while(Debut.size() != 0 )
		{
			System.out.println(" Méthode d’élimination des points d’entrée");
			System.out.println("Rang courant = " + compteur);
			System.out.println(Methode.entree(Debut, Fin));
			
			int xyz=Methode.id(Debut, Fin).size();
			
			for (int i=0 ; i < Debut.size(); i++)
			{
				for(int j =0 ; j < xyz ; j++)
				{
					if ( i == Methode.id(Debut, Fin).get(j))
					{
						verif = 1;
					}
				}
				if( verif == 0)
				{
					Debut.remove(i);
					Fin.remove(i);
					i=i-1;
				}
				else
				{
					verif = 0;
				}
			}
			
			compteur++;
			
		}
		
		
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
		return A;
	}
}
