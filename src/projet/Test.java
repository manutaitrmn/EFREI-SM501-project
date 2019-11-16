package projet;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Test 
{

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Quel graphe voulez vous etudier? ( -1 == NON )"); // Demande a l'utilisateur de choisir le graphe
			Scanner action = new Scanner(System.in); // Permet de recuperer les informations ecrites par l'utilisateur
			int nb = action.nextInt();

			while (nb != -1) {
				
				String M = new File("").getAbsolutePath() + "/src/projet/Graphe" + nb + ".txt"; // Permet de recuperer le chemin du dossier dans lequel se trouve les graphes a etudier
			
				ArrayList<Integer[]> graphe = Methode.convert(M);
				
				System.out.println(graphe.get(4)[2]);
				
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
