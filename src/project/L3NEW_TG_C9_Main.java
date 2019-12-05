package project;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class L3NEW_TG_C9_Main {

	public static void main(String[] args) throws IOException {
		try {
			//Demande a l'utilisateur de choisir le graphe
			System.out.println("Quel graphe voulez vous étudier? (-1 pour quitter)");
			//Recuperation de la saisie de l'utilisateur
			Scanner action = new Scanner(System.in);
			int nb = action.nextInt();

			while (nb > -1) {
				
				//Recupere le fichier txt du graphe a�etudier
				String file = new File("").getAbsolutePath() + "/src/graphs/L3NEW-TG-C9-g" + nb + ".txt";
				
				L3NEW_TG_C9_Graph graph = null;
				
				//On instancie le graphe
				try {
					graph = new L3NEW_TG_C9_Graph(file);
					
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
					
					//Etudier un autre graphe
					System.out.println("\n\n\nEtudier un autre graphe? (-1 pour quitter)");
					action = new Scanner(System.in);
					nb = action.nextInt();
					
				} catch(NumberFormatException e) {
					System.out.println("Le fichier .txt doit contenir que des entiers.");
					nb = -1;
				}
				
			}

			System.out.println("\nFin");
			action.close();

		} catch (Exception e) {

			System.err.println("Un probleme est survenu :" + e);

		}
	}

}
