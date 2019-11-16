package projet;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Test 
{

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Quel graphe voulez vous étudier? ( -1 == NON )");
			Scanner action = new Scanner(System.in);
			int nb = action.nextInt();
			
			while (nb != -1) {
				String M = new File("").getAbsolutePath()+"/src/projet/Graphe"+nb+ ".txt"; // On cherche un graphe.txt
			
				System.out.println("Lecture du graphe sur fichier");
				Methode.Lire(M);
			
				System.out.println("");
				System.out.println("Représentation du graphe sous forme matricielle");
				Methode.adjacence(M);
				Methode.valeurs(M);
			
				System.out.println("");
				System.out.println("Détection de circuit");
				Methode.circuit(M);
			
				System.out.println("");
				System.out.println("Calcul des rangs");
				Methode.rang(M);
			
				System.out.println("Quel graphe voulez vous afficher?");
				Scanner number = new Scanner(System.in);
				nb = number.nextInt(); // Si nb == -1 On arr�te la boucle
			
			}
		
			System.out.println("Fin");
			action.close();
		} catch (Exception e) {
			System.err.println(e + "Un problème est survenu");
		}
	}

}
