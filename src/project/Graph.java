package  project;

//Librairies nécessaires
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph extends Exception implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Variable principale contenant toutes les données du graphe.
	private ArrayList<String[]> data;

	
	//Constructeur
	public Graph(String file) {
		ArrayList<String[]> converted = new ArrayList<String[]>();
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				String[] ints = new String[l.length];
				for (int i = 0 ; i < l.length ; i++) {
					Integer.parseInt(l[i]);
					ints[i] = l[i];
				}
				converted.add(ints);
			}
			br.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		this.data = converted;
	}

	
	//METHODES DE LECTURE
	
	//Lecture du graphe
	public void read() {
		System.out.println("\n* Lecture du graphe \n");
		ArrayList<String[]> graphData = data;
		System.out.println(getNumberOfNodes() + " sommets");
		System.out.println(getNumberOfEdges() + " arcs");
		for (int i = 0; i < graphData.size(); i++) {
			System.out.println(graphData.get(i)[0] + " -> " + graphData.get(i)[1] + " = " + graphData.get(i)[2]);
		}
	}
	//Lecture de la matrice d'adjacence
	public void readAdjacencyMatrix() {
		System.out.println("\n* Lecture de la matrice d'adjacence \n");
		String[][] m = getAdjacencyMatrix();
		for (int row = 0; row < m.length+1; row++) {
            for (int col = 0; col < m.length+1; col++) {
            	if (row == 0) {
            		if (col == 0) {
            			System.out.printf("%6s", " ");
            		} else {
            			System.out.printf("%6s", col-1);
            		}
            	} else {
            		if (col == 0) {
            			System.out.printf("%6s", row-1);
            		} else {
            			System.out.printf("%6s", m[row-1][col-1]);
            		}
            	}
                
            }
            System.out.println();
        }
	}
	//Lecture de la matrice de valeurs
	public void readValuesMatrix() {
		System.out.println("\n* Lecture de la matrice de valeurs \n");
		String[][] m = getValuesMatrix();
		for (int row = 0; row < m.length+1; row++) {
            for (int col = 0; col < m.length+1; col++) {
            	if (row == 0) {
            		if (col == 0) {
            			System.out.printf("%6s", " ");
            		} else {
            			System.out.printf("%6s", col-1);
            		}	
            	} else {
            		if (col == 0) {
            			System.out.printf("%6s", row-1);
            		} else {
            			if (m[row-1][col-1] == null) {
                    		System.out.printf("%6s", "*");
                    	} else {
                    		System.out.printf("%6s", m[row-1][col-1]);
                    	}
            		}
            	}
            }
            System.out.println();
        }
	}
	
	
	//METHODES DES MATRICES
	
	//Recupere la MATRICE D'ADJACENCE sous forme de tableau d'entiers a 2 dimensions
	public String[][] getAdjacencyMatrix() {
		int nbv = getNumberOfNodes();
		String[][] m = new String[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = "0";
            }
        }
		for (int i = 0; i < data.size(); i++) {
			if (!data.get(i)[0].equals("*")) {
				m[Integer.parseInt(data.get(i)[0])][Integer.parseInt(data.get(i)[1])] = "1";
			}
		}
		return m;
	}
	//Recupere la MATRICE DE VALEURS sous formes de tableau d'entiers a 2 dimensions
	public String[][] getValuesMatrix() {
		int nbv = getNumberOfNodes();
		String[][] m = new String[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = null;
            }
        }
		for (int i = 0; i < data.size(); i++) {
			if (!data.get(i)[0].equals("*")) {
				m[Integer.parseInt(data.get(i)[0])][Integer.parseInt(data.get(i)[1])] = data.get(i)[2];
			}
		}
		return m;
	}
	//FIN METHODES POUR LES MATRICES
	
	
	//METHODES DES SOMMETS
	
	//Recupere le nombre de sommets total du graphe
	public int getNumberOfNodes() {
		return getAllNodes().length;
	}
	//Recupere tous les sommets du graphe dans une liste de string
	public String[] getAllNodes() {
        String[] array1 = getLeftNodes();
        String[] array2 = getRightNodes();
        int aLen = array1.length;
        int bLen = array2.length;
        String[] result = new String[aLen + bLen];
        System.arraycopy(array1, 0, result, 0, aLen);
        System.arraycopy(array2, 0, result, aLen, bLen);
        result = removeStars(removeDuplicates(result));
        return result;
    }
	//Recupere tous les sommets possedant des fleches sortantes dans une liste de string (les sommets de gauche dans le txt)
	public String[] getLeftNodes() {
		ArrayList<String> lv = new ArrayList<String>();
		int i;
		for ( i = 0; i < data.size(); i++) {
			lv.add(data.get(i)[0]);
		}
		String[] flv = new String[lv.size()];
		for (i = 0; i < lv.size(); i++) {
			flv[i] = lv.get(i);
		}
		return flv;
	}
	//Recupere tous les sommets possedant des fleches entrantes dans une liste de string (les sommets de droite dans le txt)
	public String[] getRightNodes() {
		ArrayList<String> rv = new ArrayList<String>();
		int i;
		for ( i = 0; i < data.size(); i++) {
			rv.add(data.get(i)[1]);
		}
		String[] frv = new String[rv.size()];
		for (i = 0; i < rv.size(); i++) {
			frv[i] = rv.get(i);
		}
		return frv;
	}
	//Recupere les points d'entrée sous forme de liste de string
	public String[] getSourceNodes() {
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		ArrayList<String> sv = new ArrayList<String>();
		for (int i = 0; i < lv.length; i++) {
			if (!Arrays.asList(rv).contains(lv[i]) && !lv[i].contains("*")) {
				sv.add(lv[i]);
			} else if (lv[i].contains("*")) {
				sv.add(rv[i]);
			}
		}
		String[] result = new String[sv.size()];
		for (int i = 0; i < sv.size(); i++) {
			result[i] = sv.get(i);
		}
		return removeDuplicates(result);	
	}
	//Recupere les points de sortie sous forme de liste de string
	public String[] getSinkNodes() {
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		ArrayList<String> sv = new ArrayList<String>();
		for (int i = 0; i < rv.length; i++) {
			if (!Arrays.asList(lv).contains(rv[i])) {
				sv.add(rv[i]);
			}
		}
		String[] result = new String[sv.size()];
		for (int i = 0; i < sv.size(); i++) {
			result[i] = sv.get(i);
		}
		return removeDuplicates(result);	
	}
	//Supprime un sommet
	public void removeVertexByName(String node) {
		ArrayList<String[]> toBeRemoved = new ArrayList<String[]>();
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		for (int i = 0; i < data.size(); i++) {
			if (rv[i].equals(node)) {
				toBeRemoved.add(data.get(i));
			} else if (lv[i].equals(node)) {
				data.set(i, new String[] {"*",data.get(i)[1],"*"});
			}
		}
		data.removeAll(toBeRemoved);
		checkNodes();
	}
	//Vérifie les liaisons inutiles et les supprime
	public void checkNodes() {
		ArrayList<String[]> toBeRemoved = new ArrayList<String[]>();
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		for (int i = 0; i < data.size(); i++) {
			if (lv[i].equals("*") && (Arrays.asList(lv).contains(rv[i]) || Arrays.asList(removeValueById(rv, i)).contains(rv[i]))) {
				toBeRemoved.add(data.get(i));
			}
		}
		data.removeAll(toBeRemoved);
	}
	//Recupere les sommets classés par rangs dans un tableau 2d
	public ArrayList<String[]> getAllNodesRanks() {
		// On recupere tous les sommets
		ArrayList<String[]> ranks = new ArrayList<String[]>();
		//Tant qu'il y a toujours des points d'entre
		while (getSourceNodes().length != 0) {
			// On recupere les points d'entree
			String[] sv = getSourceNodes();
			ranks.add(sv);
			// On supprime les points d'entree
			for (int i = 0; i < sv.length; i++) {
				removeVertexByName(sv[i]);
			}
		}
		return ranks;
	}
	//Recupere les predecesseurs d'un sommet
	public String[] getPreds(String node) {
		ArrayList<String> preds = new ArrayList<String>();
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		int size = data.size();
		int i;
		for (i = 0; i < size; i++) {
			if (rv[i].equals(node) && !lv[i].equals("*")) {
				preds.add(lv[i]);
			}
		}
		String[] fpreds = new String[preds.size()];
		for (i = 0; i < preds.size(); i++) {
			fpreds[i] = preds.get(i);
		}
		return fpreds;
		
	}
	//Recupere les successeurs d'un sommet
	public String[] getSucc(String node) {
		ArrayList<String> succ = new ArrayList<String>();
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		int size = data.size();
		int i;
		for (i = 0; i < size; i++) {
			if (lv[i].equals(node) && !lv[i].equals("*")) {
				succ.add(rv[i]);
			}
		}
		String[] fsucc = new String[succ.size()];
		for (i = 0; i < succ.size(); i++) {
			fsucc[i] = succ.get(i);
		}
		return fsucc;
		
	}

	
	//METHODES DES ARCS
	
	//Recupere le nombre d'arcs
	public int getNumberOfEdges() {
		String[] lv = getLeftNodes();
		int count = 0;
		for (int i = 0; i < lv.length; i++) {
			if (!lv[i].equals("*")) {
				count++;
			}
		}
		return count;
	}
	//Recupere toutes les valeurs des arcs
	public Integer[] getAllEdgesValues() {
		ArrayList<Integer> arrows = new ArrayList<Integer>();
		int i;
		for (i = 0; i < data.size(); i++) {
			if (!data.get(i)[2].equals("*")) {
				arrows.add(Integer.parseInt(data.get(i)[2]));
			}
		}
		Integer[] farrows = new Integer[arrows.size()];
		for (i = 0; i < arrows.size(); i++) {
			farrows[i] = arrows.get(i);
		}
		return farrows;
	}
	//Recupere les valeurs des arcs sortants d'un sommet sous la forme d'une liste d'entiers
	public Integer[] getOutgoingEdgesValuesOf(String node) {
		ArrayList<Integer> arrows = new ArrayList<Integer>();
		int i;
		for (i =0; i < data.size(); i++) {
			if (data.get(i)[0].equals(node)) {
				arrows.add(Integer.parseInt(data.get(i)[2]));
			}
		}
		Integer[] farrows = new Integer[arrows.size()];
		for (i = 0; i < arrows.size(); i++) {
			farrows[i] = arrows.get(i);
		}
		return farrows;
	}
	public String getArcValueBetween(String node1, String node2) {
		String arcValue = null;
		String[] lv = getLeftNodes();
		String[] rv = getRightNodes();
		
		for (int i = 0; i < data.size(); i++) {
			if(lv[i].equals(node1) && rv[i].equals(node2)) {
				arcValue = data.get(i)[2];
			}
		}
		return arcValue;
		
	}
	
	
	//METHODES DE DETECTION DE CYCLE
	
	// Detection de cycle d'un graphe
	public void detectCycle() {
		System.out.println("\n* Detection de circuit \n");
		// On recupere tous les sommets
		Graph tempGraph = deepClone();
		String[] pr = tempGraph.getAllNodes();
		//Tant qu'il y a toujours des points d'entre
		while (tempGraph.getSourceNodes().length != 0) {
			// On recupere les points d'entree
			String[] sv = tempGraph.getSourceNodes();
			System.out.println("Points d'entrée");
			System.out.println(Arrays.deepToString(sv));
			// On supprime les points d'entree
			System.out.println("Suppression des points d'entrée");
			for (int i = 0; i < sv.length; i++) {
				tempGraph.removeVertexByName(sv[i]);
			}
			// On recupere les points restants
			System.out.println("Points restants");
			pr = tempGraph.getAllNodes();
			if (pr.length != 0) {
				System.out.println(Arrays.deepToString(pr));
			} else {
				System.out.println("Aucun");
			}
		}
		System.out.println("Points d'entrée \nAucun");
		// Si il existe toujours des sommets, alors le graphe contient au moins un circuit sinon etc...
		if (pr.length == 0) {
			System.out.println("Le graphe contient aucun circuit.");
		} else {
			System.out.println("Le graphe contient au moins un circuit.");
		}
	}
	// Renvoie un booleen vrai ou faux si le graphe contient au moins un graphe ou pas
	public boolean isCyclic() {
		boolean ic = false;
		Graph tempGraph = deepClone();
		// On recupere tous les sommets
		String[] pr = tempGraph.getAllNodes();
		//Tant qu'il y a toujours des points d'entre
		while (tempGraph.getSourceNodes().length != 0) {
			// On recupere les points d'entree
			String[] sv = tempGraph.getSourceNodes();
			// On supprime les points d'entree
			for (int i = 0; i < sv.length; i++) {
				tempGraph.removeVertexByName(sv[i]);
			}
			// On recupere les points restants
			pr = tempGraph.getAllNodes();
		}
		// Si il existe toujours des sommets, alors le graphe contient au moins un circuit sinon etc...
		if (pr.length != 0) {
			ic = true;
		}
		return ic;
	}
	
	
	//METHODE DES RANGS
	
	//Affiche les rangs de chaque sommet
	public void ranksCalc() {
		System.out.println("\n* Calcul des rangs par élimination des points d'entrée \n");
		if (!isCyclic()) {
			ArrayList<String[]> ranks = deepClone().getAllNodesRanks();
			for (int i = 0; i < ranks.size(); i++) {
				System.out.println("Rang courant = " + i);
				System.out.println("Points d'entrée:");
				System.out.println(Arrays.toString(ranks.get(i)));
			}
			System.out.println("Graphe vide");
			System.out.println("Rangs calculés");
			for (int i = 0; i < getNumberOfNodes()+1; i++) {
				if (i == 0) {
					System.out.printf("%6s", "Sommet");
				} else {
					System.out.printf("%6s", i-1);
				}
			}
			System.out.println();
			for (int i = 0; i < getNumberOfNodes()+1; i++) {
				if (i == 0) {
					System.out.printf("%6s", "Rang");
				} else {
					System.out.printf("%6s", deepClone().getNodeRankByName(Integer.toString(i-1)));
				}
			}
		} else {
			System.out.println("On ne peut pas étudier les rangs des sommets car le graphe contient au moins un circuit.");
		}
	}
	//Recupere le rang d'un sommet en donnant le nom du sommet
	public String getNodeRankByName(String node) {
		ArrayList<String[]> ranks = getAllNodesRanks();
		String rank = null;
		int i = 0;
		while (rank == null && i < ranks.size()) {
			for (int j = 0; j < ranks.get(i).length; j++) {
				if (ranks.get(i)[j].equals(node)) {
					rank = Integer.toString(i);
				}
			}
			i++;
		}
		return rank;
	}
	
	//METHODES DE VERIFICATION DE GRAPHE D'ORDONNANCEMENT CORRECT
	
	//Permet d'afficher les conditions d'un graphe d'ordonnancement correct
	public void correctDigraphVerification() {
		Boolean[] conditions = getCorrectDigraphVerification();
		
		System.out.println();
		System.out.println("\n* Verification d'un graphe d'ordonnancement correct:\n");
		
		System.out.println("Un seul point d'entree: " + conditions[0]);
		System.out.println("Un seul point de sortie: " + conditions[1]);
		System.out.println("Pas de circuit: " + conditions[2]);
		System.out.println("Les arcs sortants de chaque sommet sont de valeur identique: " + conditions[3]);
		System.out.println("Les arcs sortants du point d'entree sont de valeur nulle: " + conditions[4]);
		System.out.println("Pas d'arcs a valeur negative: " + conditions[5]);
		
		if (Arrays.stream(conditions).allMatch(s -> s.equals(true))) {
			System.out.println("\nGraphe d'ordonnancement correct.");
		} else {
			System.out.println("\nGraphe d'ordonnancement incorrect.");
		}
		
	}
	//Retourne un true si c'est bien un graphe d'ordonnancement, sinon false
	public boolean isCorrectDigraph() {
		boolean isCorrect = false;
		Boolean[] conditions = getCorrectDigraphVerification();
		if (Arrays.stream(conditions).allMatch(s -> s.equals(true))) {
			isCorrect = true; 
		}
		return isCorrect;
	}
	//Recupere un tableau contenant les booleen de chaque condition.
	public Boolean[] getCorrectDigraphVerification() {
		Boolean[] conditions = new Boolean[6];
		Arrays.fill(conditions, Boolean.FALSE);
		
		int i;
		
		if (getSourceNodes().length == 1) {
			// Verifie qu'il n'y a qu'un point d'entree
			conditions[0] = true;
			
			// Verifie que les arcs sortant du point d'entree sont = 0 (null)
			String sourceVertex = getSourceNodes()[0];
			Integer[] outgoingArrows = getOutgoingEdgesValuesOf(sourceVertex);
			boolean arrowsEqualZero = true;
			i = 0;
			while (arrowsEqualZero == true && i < outgoingArrows.length) {
				if (outgoingArrows[i] != 0) {
					arrowsEqualZero = false;
				}
				i++;
			}
			conditions[4] = arrowsEqualZero;
		}
			

		//Verifie qu'il n'y a q'un point de sortie
		if (getSinkNodes().length == 1)
			conditions[1] = true;

		//Verifie qu'il n'y a pas de cycle
		if (!isCyclic())
			conditions[2] = true;
		
		//Verifie qu'il n'y a pas d'arcs a valeur negative
		Integer[] allArrows = getAllEdgesValues();
		boolean positiveArrows = true;
		i = 0;
		while (positiveArrows == true && i < allArrows.length) {
			if (allArrows[i] < 0) {
				positiveArrows = false;
			}
			i++;
		}
		conditions[5] = positiveArrows;
		
		//Verifie si les arcs sortants de chaque sommet sont de valeurs identique
		String[] lv = getLeftNodes();
		boolean same = true;
		i=0;
		while(same == true && i < lv.length) {
			Integer[] outgoingArrows = getOutgoingEdgesValuesOf(lv[i]);
			if(!Arrays.stream(outgoingArrows).allMatch(s -> s.equals(outgoingArrows[0]))) {
				same = false;
			}
			i++;
		}
		conditions[3] = same;
		return conditions;
		
	}

	
	//METHODES DATES AU PLUS TOT, AU PLUS TARD, ...

	//Affiche le calendrier des dates au plus tard, au plus tot, ainsi que les marges
	public void readSchedules() {
		System.out.println("\n* Calendrier au plus tard, au plus tot, et marges.\n");
		if (isCorrectDigraph()) {
			
			String[] allNodes = getAllNodes();
			int i;
			for (i = 0; i < allNodes.length+1; i++) {
				if (i == 0) {
					System.out.printf("%8s", "Tache");
				} else {
					System.out.printf("%8s", i-1);
				}
			}
			System.out.println();
			for (i = 0; i < allNodes.length+1; i++) {
				if (i == 0) {
					System.out.printf("%8s", "DAPTot");
				} else {
					System.out.printf("%8s", earliest(Integer.toString(i-1)));
				}
			}
			System.out.println();
			for (i = 0; i < allNodes.length+1; i++) {
				if (i == 0) {
					System.out.printf("%8s", "DAPTard");
				} else {
					System.out.printf("%8s", latest(Integer.toString(i-1)));
				}
			}
			System.out.println();
			for (i = 0; i < allNodes.length+1; i++) {
				if (i == 0) {
					System.out.printf("%8s", "Marges");
				} else {
					System.out.printf("%8s", latest(Integer.toString(i-1))-earliest(Integer.toString(i-1)));
				}
			}
			
		} else {
			System.out.println("Ce graphe ne respecte pas les conditions d'un graphe d'ordonnancement correct.");
		}
		
	}
	//Recupere la date au plus tot d'un sommet (tache)
	public int earliest(String node) {
		String[] preds = getPreds(node);
		int pl = preds.length;
		if (pl != 0) {
			String max;
			if (pl == 1) {
				max = preds[0];
			} else {
				max = preds[0];
				for (int i = 1; i < pl; i++) {
					if (earliest(preds[i]) > earliest(max)) {
						max = preds[i];
					}
				}
			}
			int arcValue = Integer.parseInt(getArcValueBetween(max, node));
			return arcValue + earliest(max);
		} else {
			return 0;
		}
	}
	//Recupere la date au plus tard d'un sommet (tache)
	public int latest(String node) {
		String[] succ = getSucc(node);
		int sl = succ.length;
		if (sl != 0) {
			String min;
			if (sl == 1) {
				min = succ[0];
			} else {
				min = succ[0];
				for (int i = 1; i < sl; i++) {
					if (latest(succ[i]) < latest(min)) {
						min = succ[i];
					}
				}
			}
			int arcValue = (-1)*Integer.parseInt(getArcValueBetween(node, min));
			return arcValue + latest(min);
		} else {
			return earliest(getSinkNodes()[0]);
		}
	}
	
	
	//GETTERS & SETTERS
	
	//Recupere les donnees du graphe sous forme d'un tableau 2D
	public ArrayList<String[]> getData() {
		return data;
	}
	//Set la valeur de la variable privee data ***
	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}
	
	
	//AUTRES METHODES UTILES
	
	//Supprime tous les doublons dans une liste de type Integer
	private static String[] removeDuplicates(String[] list) {
		return Arrays.stream(list).distinct().toArray(String[]::new);
	}
	//Retire les caracteres "*" d'une liste de string
	private String[] removeStars(String[] list) {
		ArrayList<String> av = new ArrayList<String>();
        int i;
        for (i = 0; i < list.length; i++) {
        	if (!list[i].equals("*")) {
        		av.add(list[i]);
        	}
        }
        String[] finalResult = new String[av.size()];
        for (i = 0; i < av.size(); i++) {
        	finalResult[i] = av.get(i);
        }
        return finalResult;
	}
	//Retire une valeur d'une liste de string en donnant son index
	private String[] removeValueById(String[] List, int index) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = 0; i < List.length; i++) {
			if (i != index) {
				newList.add(List[i]);
			}
		}
		String[] result = new String[newList.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = newList.get(i);
		}
		return result;
	}
	//Cloner un objet graph
	public Graph deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Graph) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	//Tri une liste de String dans l'ordre croisssant
	public String[] sortStringArray(String[] array) {
		int size = array.length;
		for(int i = 0; i<size-1; i++) {
			for (int j = i+1; j<array.length; j++) {
				if(array[i].compareTo(array[j])>0) {
					String temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}

}

