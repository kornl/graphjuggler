import java.util.Vector;
import java.awt.Color;

public class VS {
    public Vector nl = new Vector();
    public void addNL(Object mif){this.nl.add(mif);}
    //public void removeNL(int nr){	
    //	nl.remove();
    //}
    // In nl1, nl2 werden die Netzlisten der betrachteten
    // Graphen gespeichert.
    public NetzListe nl1, nl2;
    //    public void setNL1(NetzListe nl){this.nl1 = nl;}
    //    public void setNL2(NetzListe nl){this.nl2 = nl;}
    public boolean moreInfo = false;
    public boolean directed = false;
    public boolean shownr = false;
    public double nz = 1.00; // NetzListeZoom
    public void saveZoom(){;}
    public int graphdrawalgo = 1;
    public int getGraphDrawAlgo() {return graphdrawalgo;}
    public void setGraphDrawAlgo(int p) {graphdrawalgo = p;}
    public double zoom = 1.00;
    public double getZoom() {return zoom;}
    public void setZoom(double p) { zoom = p;}
    public boolean force;
    public boolean getforce() {return force;}
    public void setforce(boolean p) {force = p;}
    public boolean forceSO;
    public boolean getforceSO() {return forceSO;}
    public void setforceSO(boolean p) {forceSO = p;}
    public boolean shake = true;
    public void setShake(boolean s) {shake = s;}
    public boolean getShake() {return shake;} 
    public FrameViewer frameViewer;
    public void setFrameViewer(FrameViewer frameViewer){this.frameViewer = frameViewer;}
    boolean newVertex = false;
    boolean newEdge = false;
    boolean endomorphisms = true;
    public void setEnd(boolean p){ 
	endomorphisms=p;
	//nl2.setBackground(p?Color.darkGray:Color.lightGray);
    }
    public Vector aut = new Vector();
    public Vector send = new Vector();
    public Vector end = new Vector();
    public Vector hend = new Vector();
    public Vector lend = new Vector();
    public Vector qend = new Vector();
    int[][] e1, e2;
    int[][] k1, k2;
    String[] b1, b2;
    int v1, v2;
    int[] rmap;

    public void repaint() {
	for (int i=0; i< nl.size(); i++) {
	    (((MyInternalFrame)(nl.get(i))).nl).repaint();
	}	
    }

    public void recMorph(int wo) {
	boolean[] possible = new boolean[v2];
	for (int i=0; i < v2; i++) {
	    possible[i]=true;
	}
	for (int i=0; i < wo; i++) {
	    for (int j=0; j < v2; j++) {
		if (e1[i][wo]==1 && e2[rmap[i]][j]!=1)
		    possible[j]=false;
	    }
	}	
	for (int j=0; j < v2; j++) {
	    if (possible[j]) {
		rmap[wo]=j;
		if (wo+1<v1) {
		    recMorph(wo+1);
		} else
		    examine(rmap);
	    }
	}
    }

    public int getSelected() {
	int nr = -1;
	for (int i=0; i< nl.size(); i++) {
	    if(((MyInternalFrame)(nl.get(i))).isSelected()) {
		nr = i;
	    }
	}
	return nr;
    }

    public void restoreMatrices() {
	frameViewer.createNLW();
	nl1 = ((MyInternalFrame)nl.get(nl.size()-1)).nl;
	v1 = e1.length;
	nl1.anzahl=0;	
	nl1.kAnzahl=0;
	nl1.knoten = new Node[nl1.MAX];
	nl1.kanten = new int[nl1.MAX][];
	for(int i=0; i<v1; i++) {
	    nl1.addNode(i+1, b1[i], 0, 0, 0, k1[i][0], k1[i][1], false);
	}
	for(int i=0; i<v1; i++) {
	    for(int j=0; j<v1; j++) {
		if (e1[i][j]!=0) nl1.addEdge(i,j);
	    }
	}
	
    }

    public void createMatrices(int g1, int g2) {
	nl1 = ((MyInternalFrame)(nl.get(g1))).nl;
	nl2 = ((MyInternalFrame)(nl.get(g2))).nl;
	e1 = nl1.getAMatrix();
	v1 = nl1.anzahl;
	k1 = new int[v1][2];
	b1 = new String[v1];
	for (int i=0; i < v1; i++) {
	    k1[i][0] = (int) nl1.knoten[i].x;
	    k1[i][1] = (int) nl1.knoten[i].y;
	    b1[i] = nl1.knoten[i].name;
	}	
	e2 = nl2.getAMatrix(); v2 = nl2.anzahl; 
        k2 = new int[v2][2];
	b2 = new String[v2];
	for (int i=0; i < v2; i++) {
	    k2[i][0] = (int) nl2.knoten[i].x;
	    k2[i][1] = (int) nl2.knoten[i].y;
	    b2[i] = nl2.knoten[i].name;
	}	
// 	if (endomorphisms) { e2 = e1; v2 = v1; k2 = k1;} 
// 	else { 
// 	    e2 = nl2.getAMatrix(); v2 = nl2.anzahl; 
// 	    k2 = new int[v2][2];
// 	    for (int i=0; i < v2; i++) {
// 		k2[i][0] = (int) nl2.knoten[i].x;
// 		k2[i][1] = (int) nl2.knoten[i].y;
// 	    }	
// 	}
    }

    public void findMorphisms() {
	aut = new Vector();
	send = new Vector();
	end = new Vector();
	hend = new Vector();
	lend = new Vector();
	qend = new Vector();
	e1 = nl1.getAMatrix();
	v1 = nl1.anzahl;
	if (endomorphisms) { e2 = e1; v2 = v1;} 
	else { e2 = nl2.getAMatrix(); v2 = nl2.anzahl; }
	if (v1==0||v2==0) return;
	// Generiere Morphismen:
        rmap = new int[v1];
	for (int i=0; i < v1; i++) {
	    rmap[i]=0;
	}
	recMorph(0);
	//if (endomorphisms) test();
    }

    public boolean examine(int[] map){
	boolean isCnd = true; boolean isEnd = true;
	boolean isI; boolean bijective = false;
	if (v1==v2) bijective = true;
	boolean isHEnd = false; boolean herehend;
	boolean isLEnd = false; boolean herelend;
	boolean isQEnd = false; boolean hereqend;
	for (int a = 0; a<v1; a++){
	    for (int b = 0; b<v1; b++){
		if ((e2[map[a]][map[b]] == 1) && !(e1[a][b]==1))
		    isCnd = false;
		if (!(e2[map[a]][map[b]] == 1) && (e1[a][b]==1))
		    return false; //isEnd = false;
		for (int i=0; i<v2; i++) {
		    isI=false;
		    for (int j=0; j<v1; j++) {
			if (i==map[j]) isI=true;
		    }
		    if (isI==false) bijective = false;		
		}
	    }
	}
	//if (!isEnd) return false;
	isHEnd = isHStr(map);isLEnd = isLStr(map);isQEnd = isQStr(map);
	int[] smap = new int[map.length];
	for (int i=0; i<map.length; i++) {
	    smap[i]=map[i];
	}
	//if (isEnd) 
	end.add(smap);
	if (isEnd && isCnd) send.add(smap);
	if (bijective && 
	    //isEnd && 
	    isCnd) aut.add(smap);
	if (isHEnd) hend.add(smap);
	if (isLEnd) lend.add(smap);
	if (isQEnd) qend.add(smap);
	return true;	
    }

    public boolean isHStr(int[] map){
	boolean isEnd = true;	
	boolean isHEnd = false; boolean herehend;
	for (int a = 0; a<v1; a++){
	    for (int b = 0; b<v1; b++){
		if (!(e2[map[a]][map[b]] == 1) && (e1[a][b]==1))
		    isEnd = false;	    
	    }
	}
	if (isEnd) {
	    isHEnd = true;
	    for (int a = 0; a<v1; a++){
		for (int b = 0; b<v1; b++){
		    // half-, locally- and quasistrong:
		    if (e2[map[a]][map[b]] == 1) {
		    // halfstrong
			herehend = false;
			for (int i=0; i<v1; i++) {
			    if (map[i]==map[a]) 
				for (int j=0; j<v1; j++) {
				    if (map[j]==map[b] && e1[i][j]==1) {
					herehend=true;
				    }
				}		
			}
			if (!herehend) isHEnd=false;
		    }
		}
	    }
	}
	return isHEnd;
    }

    public boolean isLStr(int[] map){
	boolean isEnd = true;	
	boolean isLEnd = false; boolean herelend;
	for (int a = 0; a<v1; a++){
	    for (int b = 0; b<v1; b++){
		if (!(e2[map[a]][map[b]] == 1) && (e1[a][b]==1))
		    isEnd = false;	    
	    }
	}
	if (isEnd) {
	    isLEnd = true;
	    for (int a = 0; a<v1; a++){
		for (int b = 0; b<v1; b++){
		    // half-, locally- and quasistrong:
		    if (e2[map[a]][map[b]] == 1) {
			// locallystrong			
			herelend = false;
			for (int i=0; i<v1; i++) {
			    if (map[i]==map[a]) {
				herelend = false;
				for (int j=0; j<v1; j++) {
				    if (map[j]==map[b] && e1[i][j]==1) {
					herelend=true;
				    }
				}		
				if (!herelend) isLEnd=false;
			    }	
			}
		    }
		}
	    }
	}
	return isLEnd;
    }

    public boolean isQStr(int[] map){
	boolean isEnd = true;	
	boolean isQEnd = false; boolean hereqend;
	for (int a = 0; a<v1; a++){
	    for (int b = 0; b<v1; b++){
		if (!(e2[map[a]][map[b]] == 1) && (e1[a][b]==1))
		    isEnd = false;	    
	    }
	}
	boolean isQEnd1;
	if (isEnd) {
	    isQEnd = true;
	    for (int a = 0; a<v1; a++){
		for (int b = 0; b<v1; b++){
		    // half-, locally- and quasistrong:
		    if (e2[map[a]][map[b]] == 1) {
			// quasistrong			
			isQEnd1 = false;	    
			for (int i=0; i<v1; i++) {
			    if (map[i]==map[a]) { // exists x_0
				hereqend = true;
				for (int j=0; j<v1; j++) { // forall y
				    if (map[j]==map[b] && !(e1[i][j]==1)) {
					hereqend=false;
				    }
				}		
				if (hereqend) isQEnd1=true;
			    }	
			}
			if (!isQEnd1) {isQEnd=false;}
		    }
		}
	    }
	}
	return isQEnd;
    }

    public boolean isSEnd(int[] map){
	boolean isCnd = true; boolean isEnd = true;
	for (int a = 0; a<v1; a++){
	    for (int b = 0; b<v1; b++){
		if ((e2[map[a]][map[b]] == 1) && !(e1[a][b]==1))
		    isCnd = false;
		if (!(e2[map[a]][map[b]] == 1) && (e1[a][b]==1))
		    isEnd = false;
	    }
	}
	return (isEnd && isCnd);
    }

    public int[] comp(int[] map1, int[] map2) {
	int[] comp = new int[map2.length];
	for (int i=0; i<map2.length; i++) {
	    comp[i] = map1[map2[i]];
	}
	return comp;
    }

    public void test() {
	int[] map, map1, map2;
	System.out.println("\\\\HEnd Abgeschlossen?\\\\");
	weiter1:
	 for (int i=0; i< hend.size(); i++) {
	     for (int j=0; j< hend.size(); j++) {
 		if (!(isHStr(comp((int[])hend.get(i),(int[])hend.get(j))))) {
 		    map1 = (int[])hend.get(i);
 		    map2 = (int[])hend.get(j);
 		    map = comp((int[])hend.get(i),(int[])hend.get(j));
 		    Latex.printMap(map1); 
		    System.out.print("$\\bullet$");Latex.printMap(map2);
		    System.out.print("$=$");Latex.printMap(map);
		    (new DialogMessage("HEnd not closed.")).show();
		    break weiter1;
 		}
 	    }
 	}
	System.out.println("\\\\LEnd Abgeschlossen?\\\\");
	weiter2:
	for (int i=0; i< lend.size(); i++) {
	    for (int j=0; j< lend.size(); j++) {
		if (!(isLStr(comp((int[])lend.get(i),(int[])lend.get(j))))) {
		    map1 = (int[])lend.get(i);
		    map2 = (int[])lend.get(j);
		    map = comp((int[])lend.get(i),(int[])lend.get(j));
 		    Latex.printMap(map1); 
		    System.out.print("$\\bullet$");Latex.printMap(map2);
		    System.out.print("$=$");Latex.printMap(map);
		    (new DialogMessage("LEnd not closed.")).show();
		    break weiter2;
		}
	    }
	}
	System.out.println("\\\\QEnd Abgeschlossen?\\\\");
	weiter3:
	for (int i=0; i< qend.size(); i++) {
	    for (int j=0; j< qend.size(); j++) {
		if (!(isQStr(comp((int[])qend.get(i),(int[])qend.get(j))))) {
		    map1 = (int[])qend.get(i);
		    map2 = (int[])qend.get(j);
		    map = comp((int[])qend.get(i),(int[])qend.get(j));
 		    Latex.printMap(map1); 
		    System.out.print("$\\bullet$");Latex.printMap(map2);
		    System.out.print("$=$");Latex.printMap(map);
		    (new DialogMessage("QEnd not closed.")).show();
		    break weiter3;
		}
	    }
	}
    }

}
