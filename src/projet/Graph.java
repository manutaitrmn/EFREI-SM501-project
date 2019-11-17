package projet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {
	
	ArrayList<Integer[]> graph;
	
	public Graph(String file) {
		ArrayList<Integer[]> converted = new ArrayList<Integer[]>();
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
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
		
		graph = converted;
	}
	
	
	public void read() {
		System.out.println("* Lecture du graphe");
		for (int i = 0; i < graph.size(); i++) {
			if (i == 0) {
				System.out.println(graph.get(i)[0] + " sommets");
			} else if (i == 1) {
				System.out.println(graph.get(i)[0] + " arcs");
			} else {
				System.out.println(graph.get(i)[0] + " -> " + graph.get(i)[1] + " = " + graph.get(i)[2]);
			}
		}
	}
	
	
	
	// METHODES POUR LA MATRICE D'ADJACENCE
	public void readAdjacencyMatrix() {
		System.out.println("Lecture de la matrice d'adjacence");
		Integer[][] m = getAdjacencyMatrix();
		for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[row].length; col++) {
                System.out.printf("%6s", m[row][col]);
            }
            System.out.println();
        }
	}
	
	public Integer[][] getAdjacencyMatrix() {
		int nbv = getNumberVertices();
		Integer[][] m = new Integer[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = 0;
            }
        }
		for (int i = 2; i < graph.size(); i++) {
			m[graph.get(i)[0]][graph.get(i)[1]] = 1;
		}
		return m;
	}
	// FIN MATRICE D'ADJACENCE
	
	
	// METHODE POUR LA MATRICE DE VALEURS
	public void readValuesMatrix() {
		System.out.println("Lecture de la matrice de valeurs");
		Integer[][] m = getValuesMatrix();
		for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[row].length; col++) {
            	if (m[row][col] == null) {
            		System.out.printf("%6s", "*");
            	} else {
            		System.out.printf("%6s", m[row][col]);
            	}
                
            }
            System.out.println();
        }
	}
	
	public Integer[][] getValuesMatrix() {
		int nbv = getNumberVertices();
		Integer[][] m = new Integer[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = null;
            }
        }
		for (int i = 2; i < graph.size(); i++) {
			m[graph.get(i)[0]][graph.get(i)[1]] = graph.get(i)[2];
		}
		return m;
	}
	// FIN MATRICE DE VALEURS
	
	
	public int getNumberVertices() {
		return graph.get(0)[0];
	}
	
	
	public int getNumberArcs() {
		return graph.get(1)[0];
	}
	
	
	public ArrayList<Integer[]> getValues() {
		return graph;
	}
	
	
}
