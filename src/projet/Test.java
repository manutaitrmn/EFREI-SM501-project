package projet;

import java.io.IOException;
import java.util.Scanner;

public class Test 
{

	public static void main(String[] args) throws IOException 
	{
		try
		{
		System.out.println("Quel graphe voulez vous étudier? ( 0 == NON )");
		Scanner action = new Scanner(System.in);
		int nb = action.nextInt();
		
		while (nb != 0)
		{
			String M = "G:\\Théorie des graphes\\Projet\\Graphe" + nb + ".txt"; // On cherche un graphe.txt
			
			System.out.println("Lecture du graphe sur fichier");
			Methode.Lire(M);
		
			System.out.println("Représentation du graphe sous forme matricielle");
			Methode.adjacence(M);
			Methode.valeurs(M);
			
			
			System.out.println("Calcul des rangs");
			

			
			System.out.println("Quel graphe voulez vous afficher?");
			Scanner number = new Scanner(System.in);
			nb = number.nextInt(); // Si nb== 0 On arrête la boucle
			
		}
		
		System.out.println("Fin");
		action.close();
		}
		
		catch (Exception e)
		{
		System.err.println(e+ "Un problème est survenu");
		}
	}

}
