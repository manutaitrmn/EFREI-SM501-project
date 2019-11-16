package projet;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Test 
{

	public static void main(String[] args) throws IOException {
		
		try {
			// Demande a l'utilisateur de choisir le graphe
			System.out.println("Quel graphe voulez vous etudier? ( -1 == NON )");
			
			// Récupération de la saisie de l'utilisateur
			Scanner action = new Scanner(System.in);
			int nb = action.nextInt();

			while (nb != -1) {
				
				// Déclaration des types de variables
				String filePath;
				ArrayList<Integer[]> graph;
				
				// Récupération du fichier txt du graphe à étudier
				filePath = new File("").getAbsolutePath() + "/src/projet/Graphe" + nb + ".txt";
				
				// Conversion du graphe en tableau 2D à partir du fichier txt
				graph = Methode.convert(filePath);
				
				System.out.println(graph.get(4)[2]);
				
				nb = -1;
				
				// System.out.println("Lecture du graphe sur fichier");
				// Methode.Lire(M);
			
				// System.out.println("");
				// System.out.println("Représentation du graphe sous forme matricielle");
				// Methode.adjacence(M);
				// Methode.valeurs(M);
			
				// System.out.println("");
				// System.out.println("Détection de circuit");
				// Methode.circuit(M);
			
				// System.out.println("");
				// System.out.println("Calcul des rangs");
				// Methode.rang(M);
			
				// System.out.println("Quel graphe voulez vous afficher?");
				// Scanner number = new Scanner(System.in);
				// nb = number.nextInt(); // Si nb == -1: Arret de la boucle
			
				
				
			}
		
			System.out.println("Fin");
			action.close();
			
		} catch (Exception e) {
			
			System.err.println(e + "Un problème est survenu");
			
		}
	}

}
