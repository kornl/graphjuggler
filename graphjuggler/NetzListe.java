import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.lang.Math;
import java.awt.event.MouseEvent;

/**
 * Diese Klasse (extends JPanel) stellt einen Graphen graphisch dar ;).
 * Maximum von Schritten und Kanten : 1000
 */

class NetzListe extends JPanel {
    // Globale Variablen mit Defaultwerten
    // Radius der Knoten:
    final int MAX=1000;
    Node[] knoten = new Node[MAX];
    int[][] kanten = new int[MAX][2];
    int anzahl = 0;
    int kAnzahl = 0;
    JLabel statusBar;
    VS vs;
    RunnableAlgorithm algo;

    /**
     * Setzt die Settings aus dem Viewer Settings Objekt und
     * führt gegebenenfalls Algorithmen zur Anordnung der Knoten aus.
     */

    public void updateSettings(){
	int d = (2*Node.getRadius()+10);
	switch (vs.getGraphDrawAlgo()) {
	case 0:
	    for (int i = 0; i < anzahl; i++) {
		int w = (int)(Math.sqrt(anzahl))+1;
		knoten[i].x=3+(d*(i%w));
		knoten[i].y=3+(d*(i/w));
	    }	
	    break;
	case 1:
	    RunnableAlgorithm.hierarchicallySort(knoten, kanten, anzahl, kAnzahl, vs);
	    break;
	}
	calculateSize();
    } 

 
    /**
     * Mal die NetzListe neu und setzt preferredSize etc.
     */   

    public void refresh() { 
	calculateSize();
    }

    /**
     * Liefert die interne Nummer des Knoten mit der ID id
     * @param id ID des gesuchten Knotens
     */

    public int getNodeNr(int id) throws Exception{
	for (int j = 0; j < anzahl; j++) {
	    if (knoten[j].nr == id) return j;	    
	}
	throw new Exception();
    }

    
    public void refreshTime(){
	
    }

    /**
     * Fügt die zweite Liste in die erste ein.
     */

    public void joinList(java.util.List list1, java.util.List list2){
	for (int i = 0; i < list2.size(); i++) {
	    list1.add(list2.get(i));
	}
    }


    /**
     * Konstruktor der die NetzListe erzeugt
     * @param document Ein org.jdom.Document, das den Plan enthält.
     * @param statusBar Die Statusbar des zugehörigen FrameViewer
     * @param vs VS Viewer Setting Objekt
     */

    public NetzListe (JLabel statusBar, VS vs) {
	this.statusBar = statusBar;	
	this.vs = vs;
	addMouseMotionListener(new MouseMotionNetz(this));
	addMouseListener(new MouseNetz(this));
    }


    /**
     * Liefert die Adjacenz-Matrix zurück
     */
    public int[][] getAMatrix() {
	int[][] e = new int[anzahl][];
	for(int i = 0; i < anzahl; i++){
	    e[i] = new int[anzahl];
	    for(int j = 0; j < anzahl; j++){
		e[i][j]=0;
	    }
	}
	for(int i = 0; i < kAnzahl; i++) {
	    e[kanten[i][0]][kanten[i][1]]=1;
	    e[kanten[i][1]][kanten[i][0]]=1;
	}
	return e;
    }

    /**
     * Berechnet die benötigte Größe um alle Knoten anzuzeigen und setzt sie.
     */

    public void calculateSize() {
	long maxX = 0;
	long maxY = 0;
	for(int i = 0; i < anzahl; i++) { 
		if (knoten[i].x > maxX) maxX = knoten[i].x;
		if (knoten[i].y > maxY) maxY = knoten[i].y;
	    }
	setPreferredSize(new Dimension((int)((maxX + 2*Node.getRadius() + 10)*vs.getZoom()), (int)((maxY + 2*Node.getRadius() + 10)*vs.getZoom())));	
	revalidate();
	repaint();
    }

    /**
     * Fügt Knoten hinzu und ruft calculateSize auf.
     * @param id id des Knotens
     * @param name Name / Beschreibung des Knotens
     */

    public void addNode(int id, String name, int dauer, int start, int lstart, int x, int y, boolean fixed){
	knoten[anzahl] = new Node(id,name,x,y, vs);
	knoten[anzahl].fix = fixed;
	anzahl++;
	calculateSize();
    }

    /**
     * Fügt Kante hinzu
     * @param von Nummer des Knotens, aus dem die Kante austritt.
     * @param nach Nummer des Knotens, in den die Kante eintritt.
     */

    public void addEdge(int von, int nach) {
	kanten[kAnzahl] = new int[] {von, nach};
	kAnzahl++;
	knoten[von].degree++;
	knoten[nach].degree++;	
    }

    /**
     * Die Paint-Methode.
     * paint() geht nicht, da sie nicht bei revalidate 
     * der Scrollbars aufgerufen wird.
     */

    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	for(int i = 0; i < anzahl; i++)
	    { 
		knoten[i].paintYou(g);
	    }
	long x1, x2, y1, y2;
	for(int i = 0; i < kAnzahl; i++) {
	    x1 = knoten[kanten[i][0]].x+Node.getRadius();
	    x2 = knoten[kanten[i][1]].x+Node.getRadius();
	    y1 = knoten[kanten[i][0]].y+Node.getRadius();
	    y2 = knoten[kanten[i][1]].y+Node.getRadius();		
	    if (kanten[i][0]!=kanten[i][1]) {
		long dx = x1-x2;
		long dy = y1-y2;
		double d = Math.sqrt(dx*dx+dy*dy);
		x1 = x1 - (int)(Node.getRadius() * dx / d);
		x2 = x2 + (int)(Node.getRadius() * dx / d);
		y1 = y1 - (int)(Node.getRadius() * dy / d);
		y2 = y2 + (int)(Node.getRadius() * dy / d);
		if (vs.directed) {
		    G9gui.malVollenPfeil(g, (int)(x1*vs.getZoom()), (int)(y1*vs.getZoom()), (int)(x2*vs.getZoom()), (int)(y2*vs.getZoom()), (int)(8*vs.getZoom()));
		} else {
		    g.drawLine((int)(x1*vs.getZoom()), (int)(y1*vs.getZoom()), (int)(x2*vs.getZoom()), (int)(y2*vs.getZoom()));
		}
	    } else { // Edge is a loop:
		int r = (int)(Node.getRadius());
		g.drawArc((int)((x1-109)*vs.getZoom()), (int)((y1-(r/2))*vs.getZoom()), (int)(100*vs.getZoom()), (int)(r*vs.getZoom()), 45, 270);
		if (vs.directed) {	
		    // ToDo: Kanten mit Pfeilspitze
		}
	    }
	}
    }

    int drag=-1;

    /**
     * Methode die vom MouseListener MouseNetz aufgerufen wird, wenn eine Mousetaste gedrückt wird.
     * @param e Eingetretenes MouseEvent
     */

    boolean firstVertexSelected = false;
    int firstVertex;
    
    public void mousePress(MouseEvent e) {
	if (vs.newVertex){
	    knoten[anzahl] = new Node(anzahl+1, ""+(anzahl+1), (int) (e.getX()/vs.getZoom())-Node.r, (int)(e.getY()/vs.getZoom())-Node.r, vs);
	    anzahl++;
	    vs.newVertex=false;
	    repaint();
	    return;
	} 
	if (vs.newEdge){
	    if (!firstVertexSelected) {
		firstVertex = vertexSelected(e.getX(), e.getY());
		if (firstVertex==-1) return;
		firstVertexSelected = true;
	    } else {
		int secondVertex = vertexSelected(e.getX(), e.getY());
		if (secondVertex==-1) return;
		addEdge(firstVertex, secondVertex);
		vs.newEdge = false;
		firstVertexSelected = false;
	    }
	    repaint();
	    return;
	}
	if (drag==-1){ 
	    for(int i = 0; i < anzahl; i++)
		{ 
		    if (knoten[i].inYou(e.getX(), e.getY())) 
			{
			    drag=i;
			    statusBar.setText("Nr:"+knoten[i].nr+" Beschreibung:"+knoten[i].name);
			}
		}
	    if (drag!=-1){
		if (false) knoten[drag].fix=true;
		knoten[drag].drag=true;
	    }
	}else{
	    knoten[drag].drag=false;
	    drag=-1;	    
	}
	if (e.getClickCount() == 2)
	for(int i = 0; i < anzahl; i++)
	    { 
		if (knoten[i].inYou(e.getX(), e.getY())) 
		    {
			knoten[i].fix = !knoten[i].fix;
		    }
	    }
	//vs.frameViewer.refresh();
	repaint();
    }

    public int vertexSelected(int x, int y) {
	for(int i = 0; i < anzahl; i++)
		{ 
		    if (knoten[i].inYou(x, y)) 
			{
			    return i;
			}
		}
	return -1;
    }

    /**
     * Methode die vom MouseListener MouseNetz aufgerufen wird, wenn eine Mousetaste losgelassen wird.
     * @param e Eingetretenes MouseEvent
     */

    public void mouseRelease(MouseEvent e) {
	if (drag!=-1){ 	    
	     knoten[drag].x = (int)((e.getX()-Node.getRadius()*vs.getZoom())/(double)vs.getZoom());
	    knoten[drag].y = (int)((e.getY()-Node.getRadius()*vs.getZoom())/(double)vs.getZoom());
	    calculateSize();
	    //vs.frameViewer.refresh();
	    repaint();
	}
    }

    /**
     * Methode die vom MouseMotionListener MouseMotionNetz aufgerufen wird, wenn die Mouse bewegt wird.
     * @param e Eingetretenes MouseEvent
     */

    public void mouseMove(MouseEvent e) {
	if (drag!=-1){ 
	    knoten[drag].x = (int)((e.getX()-Node.getRadius()*vs.getZoom())/(double)vs.getZoom());
	    knoten[drag].y = (int)((e.getY()-Node.getRadius()*vs.getZoom())/(double)vs.getZoom());
	    calculateSize();
	    //vs.frameViewer.refresh();
	    repaint();
	}else{
	    for(int i = 0; i < anzahl; i++) { 
		if (knoten[i].inYou(e.getX(), e.getY())) 
		    {
			statusBar.setText("Nr:"+knoten[i].nr+" Beschreibung:"+knoten[i].name);
		    }
	    }
	}
    }

    boolean started = false;

    public void changePhysics() {
	if (!started) {
	    System.out.println("Starte Algorithmen");
	    algo = new RunnableAlgorithm(knoten, kanten, anzahl, kAnzahl, vs, this);
	    algo.start();
	    started = true;
	    algo.force=false;	    
	}
	algo.force=!algo.force;
    }

} 











































