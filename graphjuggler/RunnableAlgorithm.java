import java.util.*;
import java.lang.*;
import java.lang.Math;

/**
 * \brief Dies ist eine Algorithmus-Klasse für die NetzListe.
 * Sie besteht zum einen aus statischen Methoden zur Anordnung der Knoten,
 * und zum anderen kann sie als Thread gestartet über ein VS-Objekt gesteuert
 * kräftebasierte Verfahren auf die Knoten ausüben.
 */

/**
 * @version 12 Jan 2002
 * @author Kornelius Walter
 * @see NetzListe
 */

class RunnableAlgorithm extends Thread {

    Node[] knoten;
    int[][] kanten;
    int anzahl;
    int kanzahl;
    VS vs;
    NetzListe nl;
    static Random zufall = new Random();
    static boolean change = false;

    public RunnableAlgorithm(Node[] knoten, int[][] kanten, int anzahl, int kanzahl, VS vs, NetzListe nl)
    {
	this.setPriority(MIN_PRIORITY);
	this.knoten = knoten;
	this.kanten = kanten;
	this.anzahl = anzahl;
	this.kanzahl = kanzahl;
	this.vs = vs;
	this.nl = nl;
    }

    boolean force;

    public void run() {
	while(true){
	    this.anzahl = nl.anzahl;
	    this.kanzahl = nl.kAnzahl;
	    change = false;
	    if (force) forceBasedSort();
	    moveInQuadrantOne(knoten, anzahl);
	    if (change) nl.refresh();
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {}
	}
    }

    /**
     * Berechnet den Abstand zwischen zwei Knoten
     * @param k1 Erster Knoten
     * @param k2 Zweiter Knoten
     * @return Abstand der beiden Knoten
     */

    public static double d(Node k1, Node k2) {
	return Math.sqrt((k1.x-k2.x)*(k1.x-k2.x)+(k1.y-k2.y)*(k1.y-k2.y));
    }

    /**
     * Verschiebt alle Knoten, so daß die Koordinaten positiv sind.
     * @param knoten Knoten des Graphen
     * @param anzahl Anzahl der Knoten
     */

    public static void moveInQuadrantOne(Node[] knoten, int anzahl) {
	if (anzahl > 0) {
	    long minX = knoten[0].x;
	    long minY = knoten[0].y;
	    for(int i = 0; i < anzahl; i++) { 
		if (knoten[i].x < minX) minX = knoten[i].x;
		if (knoten[i].y < minY) minY = knoten[i].y;
	    }
	    for(int i = 0; i < anzahl; i++) { 
		knoten[i].x += ((-1) * minX) + 10;
		knoten[i].y += ((-1) * minY) + 10;
	    }
	}
    }

    /**
     * Schüttelt die Knoten
     * @param knoten Knoten des Graphen
     * @param anzahl Anzahl der Knoten
     */

    public static void shake(Node[] knoten, int anzahl, int beben)
    {
	for(int i = 0; i < anzahl; i++) {
	    if (!knoten[i].fix && !knoten[i].drag) {	    
		knoten[i].x += zufall.nextInt(beben)-(beben/2);
		knoten[i].y += zufall.nextInt(beben)-(beben/2);
	    }
	}
	change = true;
    }
    
    /**
     * Kräftebasiertes Verfahren zur Anordnung der Knoten.
     * Dabei werden direkt die übergebenen Objekte manipuliert.
     * @param knoten Knoten des Graphen
     * @param kanten Kanten des Graphen
     * @param anzahl Anzahl der Knoten
     * @param kanzahl Anzahl der Kanten
     */
    
    public void forceBasedSort()
    {
	double k1 =  1.0;
	double k2 = 250000;
	double l = 120.0;
	double epsilon1 =  0.05;
	// Kraft: Vektor des R2 mit X und Y Komponente der auf einen Knoten wirkt.
	double[][] kraft = new double[anzahl][2];
	int count = 0;
	Node u;
	Node v;
	for(int i = 0; i < anzahl; i++){
	    kraft[i][0] = 0; kraft[i][1] = 0;
	} // Kräfte berechnen:
	for(int i = 0; i < anzahl; i++){
	    v = knoten[i];
	    for(int k = 0; k < anzahl; k++){
		u = knoten[k];
		if (u!=v&&d(u,v)<120){ // Zweite Bedingung, damit nicht zusammenhängende Graphenteile nicht uneendlich auseinander driften.
			// Kraft durch Abstoßung:
			kraft[k][0] += (-1) * (k2 / (d(u,v)*d(u,v))) * (v.x - u.x) / d(u,v);
			kraft[k][1] += (-1) * (k2 / (d(u,v)*d(u,v))) * (v.y - u.y) / d(u,v);
		    }
		}
		for (int k = 0; k < kanzahl; k++){
		    if (kanten[k][0] == i) {
			// Kraft durch Federn:
			u = knoten[kanten[k][1]];
			kraft[kanten[k][1]][0] += 1d/2d*(k1 * (d(u,v)-l)) * (v.x - u.x) / d(u,v);
			kraft[kanten[k][1]][1] += 1d/2d*(k1 * (d(u,v)-l)) * (v.y - u.y) / d(u,v);
			kraft[i][0] += -1d/2d*(k1 * (d(u,v)-l)) * (v.x - u.x) / d(u,v);
			kraft[i][1] += -1d/2d*(k1 * (d(u,v)-l)) * (v.y - u.y) / d(u,v);
		    }
		}
	    } // Bewegen:
	    for(int i = 0; i < anzahl; i++){
		if (!knoten[i].fix && !knoten[i].drag) {
		    v = knoten[i];
		    v.x = v.x + (int) (kraft[i][0]*epsilon1);
		    v.y = v.y + (int) (kraft[i][1]*epsilon1);
		}
	    }
	change = true;
    }
	    
    /**
     * Kräftebasiertes Verfahren zur Anordnung der Knoten mit externer Kraft nach Südosten und fixierten ersten Knoten.
     * Dabei werden direkt die übergebenen Objekte manipuliert.
     * @param knoten Knoten des Graphen
     * @param kanten Kanten des Graphen
     * @param anzahl Anzahl der Knoten
     * @param kanzahl Anzahl der Kanten
     */

    public void forceBasedSortSO()
    {
	double f = 50;
	double epsilon1 = 0.05;
	// Kraft: Vektor des R2 mit X und Y Komponente der auf einen Knoten wirkt.
	double[][] kraft = new double[anzahl][2];
	int count = 0;
	Node v;
	for(int i = 0; i < anzahl; i++){
	    kraft[i][0] = f; kraft[i][1] = f;
	    if (!knoten[i].fix && !knoten[i].drag) {
		v = knoten[i];
		v.x = v.x + (int) (kraft[i][0]*epsilon1);
		v.y = v.y + (int) (kraft[i][1]*epsilon1);
	    }
	}
	change = true;
    }

    /**
     * Hierarchisches Verfahren zur Anordnung der Knoten.
     * Vorrausgesetzt wird, daß es ein gerichteter azyklischer Graph ist.
     * Dabei werden direkt die übergebenen Objekte manipuliert.
     * @param knoten Knoten des Graphen
     * @param kanten Kanten des Graphen
     * @param anzahl Anzahl der Knoten
     * @param kanzahl Anzahl der Kanten
     */

    public static void hierarchicallySort(Node[] knoten, int[][] kanten, int anzahl, int kanzahl, VS vs)
    {
	int[] pos = new int[anzahl];
	int[] newpos = new int[anzahl];
	for(int i = 0; i < anzahl; i++){ // Alle auf Null setzen:
	    newpos[i] = 0; 
	}
	for (int k = 0; k < kanzahl; k++) { // Oberste bestimmen:
	    newpos[kanten[k][1]] = 1;
	}
	for(int i = 1; i <= anzahl; i++){
	    pos = copy(newpos); 
	    for (int k = 0; k < kanzahl; k++) { // Wenns noch einen auf gleicher Höhe gibt, von dem er abhängig ist:
		if (pos[kanten[k][1]] == i && pos[kanten[k][0]] == i) newpos[kanten[k][1]] = i+1;
	    }
	}

	pos = copy(newpos); 
	int y = 0;
	for(int p = 0; p <= anzahl; p++) {
	    int x = 0;
	    y += 100;
	    for(int i = 0; i < anzahl; i++) {
		if (pos[i]==p) {
		    x += 100;
		    knoten[i].x = x;
		    knoten[i].y = y;
		}
	    }
	}
	moveInQuadrantOne(knoten, anzahl);
    }
    
   public static int[] copy (int[] a) {
	int[] b = new int[a.length];
	for (int i = 0; i < a.length; i++) b[i] = a[i];
	return b;
    }

    public static int getFirstInt(Node[] knoten, int anzahl, int[][] kanten,int kanzahl){
	int[] pos = new int[anzahl];
	int[] newpos = new int[anzahl];
	for(int i = 0; i < anzahl; i++){ // Alle auf Null setzen:
	    newpos[i] = 0;
	}
	for (int k = 0; k < kanzahl; k++) { // Oberste bestimmen:
	    newpos[kanten[k][1]] = 1;
	}
	int first = -1;
	for(int i = 0; i < anzahl; i++){
	    if (first == -1 && newpos[i] == 0) first=i;
	    //if (first != -1 && knoten[i].critic && newpos[i] == 0) first=i;
	}
	return first;
    }   
}






