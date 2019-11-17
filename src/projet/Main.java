package projet;

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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
				graph.readAdjacencyMatrix();
				
				// Lecture de la matrice de valeurs
				System.out.println();
				graph.readValuesMatrix();
				
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
	
	// Conversion du graphe.txt en un tableau à 2D
	public static ArrayList<Integer[]> convert(String M) throws IOException {
		ArrayList<Integer[]> converted = new ArrayList<Integer[]>();
		try (FileReader fr = new FileReader(M); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				Integer[] ints = new Integer[l.length];
				for (int i = 0 ; i < l.length ; i++) {
					ints[i] = Integer.parseInt(l[i]);
				}
				converted.add(ints);
			}
			br.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return converted;
	}

}
