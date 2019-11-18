package projet;

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		try {
			
			// Demande a l'utilisateur de choisir le graphe
			System.out.println("Quel graphe voulez vous etudier? (-1 pour quitter)");
			
			// Récupération de la saisie de l'utilisateur
			Scanner action = new Scanner(System.in);
			int nb = action.nextInt();

			while (nb != -1) {
				
				// Déclaration des types de variables
				String file;
				Graph graph;
				
				// Récupération du fichier txt du graphe à étudier
				file = new File("").getAbsolutePath() + "/src/projet/Graphe" + nb + ".txt";
				
				// Conversion du graphe en tableau 2D à partir du fichier txt
				// graph = convert(file);
				graph = new Graph(file);
				
				// Lecture du graphe
				System.out.println();
				graph.read();
				
				// Lecture de la matrice d'adjacence
				System.out.println();
				//graph.readAdjacencyMatrix();

				// Lecture de la matrice de valeurs
				System.out.println();
				//graph.readValuesMatrix();
				
				// Detection de circuit
				System.out.println();
				detectCycle(graph);
				
				nb = -1;				
				
			}
			
			System.out.println();
			System.out.println("Fin");
			action.close();
			
		} catch (Exception e) {
			
			System.err.println(e + "Un problème est survenu");
			
		}
	}
	
	public static void detectCycle(Graph graph) {
		System.out.println("* Detection de circuit");
		Graph tempGraph = graph;
		// On recupere tous les sommets
		String[] pr = tempGraph.getAllVertices();
		//Tant qu'il y a toujours des points d'entre
		while (tempGraph.getSourceVertices().length != 0) {
			// On recupere les points d'entree
			String[] sv = tempGraph.getSourceVertices();
			System.out.println("Points d'entrée");
			System.out.println(Arrays.deepToString(sv));
			// On supprime les points d'entree
			System.out.println("Suppression des points d'entrée");
			for (int i = 0; i < sv.length; i++) {
				tempGraph.removeVertex(sv[i]);
			}
			// On recupere les points restants
			System.out.println("Points restants");
			pr = tempGraph.getAllVertices();
			if (pr.length != 0) {
				System.out.println(Arrays.deepToString(pr));
			} else {
				System.out.println("Aucun");
			}
			// graph.checkVertices();
		}
		System.out.println("Points d'entrée \nAucun");
		// Si il existe toujours des sommets, alors le graphe contient au moins un circuit sinon etc...
		if (pr.length == 0) {
			System.out.println("Le graphe contient aucun circuit.");
		} else {
			System.out.println("Le graphe contient au moins un circuit.");
		}
		
	}
}
