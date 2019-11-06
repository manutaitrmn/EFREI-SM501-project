package projet;

import java.util.ArrayList;

public class zero {

	public static void main(String[] args) 
	{
		int circuit = 0;
		int v= 0;
		while ( Debut.size() != 0)
		{
			for(int i=0 ; i < Debut.size(); i++)
			{
				for(int j =0 ; j < Fin.size() ; j++)
				{
					if (Debut.get(i) == Fin.get(j))
					{
						Fin.remove(j);
						j = j-1;
						v=1;
					}
				}
				if(v==0)
				{
					Afficher.add(Debut.get(i));
					Debut.remove(i);
					i=i-1;
				}
			}
			System.out.println("Rang courant = " + circuit);
			System.out.println("Points d’entrée :");
			
			for ( int y = 0 ; y < Afficher.size() ; y++)
			{
				if(y == Afficher.size() -1 )
				{
					System.out.println(Afficher.get(y));
				}
				else
				{
					System.out.print(Afficher.get(y)+ "  ");
				}
			}
			circuit++;
			Afficher.clear();
		}
		if ( Fin.size() > 0)
		{
			for (int z = 0 ; z <= Fin.size(); z++)
			{
				Tab[Fin.get(z)][0]= circuit;
			}
			circuit ++;
			System.out.println("Rang courant = " + circuit);
			System.out.println("Points d’entrée :");
			System.out.print(Fin);
			System.out.println("Graphe vide");
		}
		else
		{
			System.out.println("Graphe vide");
		}
		
		System.out.println("Rangs calculés :");
		System.out.print("Sommet  ");
		for(int p = 0 ; p < s ; p++)
		{
			if(p == s-1)
			{
				System.out.println("   " + p );
			}
			else
			{
				System.out.print("   " + p );
			}
		}
		System.out.print("Rang    ");
		for ( int q =0 ; q < s ; q++)
		{
			if(q == s-1)
			{
				System.out.println("   " + Tab[q][0] );
			}
			else
			{
				System.out.print("   " + Tab[q][0] );
			}
		}
	}

}
