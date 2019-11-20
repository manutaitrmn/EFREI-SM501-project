package project;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
			//Demande a l'utilisateur de choisir le graphe
			System.out.println("Quel graphe voulez vous etudier? (-1 pour quitter)");
			//Récupération de la saisie de l'utilisateur
			Scanner action = new Scanner(System.in);
			int nb = action.nextInt();

			while (nb > -1) {
				//Recupere le fichier txt du graphe à étudier
				String file = new File("").getAbsolutePath() + "/src/graphs/" + nb + ".txt";
				//On instancie le graphe
				Graph graph = new Graph(file);

				//Lecture du graphe
				graph.read();
				
				//Lecture de la matrice d'adjacence
				graph.readAdjacencyMatrix();

				//Lecture de la matrice de valeurs
				graph.readValuesMatrix();
				
				//Detection de circuit				
				graph.detectCycle();

				//Calcul de rangs
				graph.ranksCalc();
			
				//Verification d'un graphe d'ordonnancement correct
				graph.correctDigraphVerification();
				
				//Affiche les calendriers
				graph.readSchedules();
				
				System.out.println("\n\n\nEtudier un autre graphe? (-1 pour quitter)");
				
				action = new Scanner(System.in);
				nb = action.nextInt();
			}
			
			System.out.println("\nFin");
			action.close();
			
		} catch (Exception e) {
			
			System.err.println("Un problème est survenu :" + e);
			
		}
	}
	
}
