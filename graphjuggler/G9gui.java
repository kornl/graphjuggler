import java.awt.*;
import java.lang.*;
import javax.swing.*;

/**
 * 
 * \brief Die G9-Gui-Klasse stellt statische Methoden bereit, die auf java.awt.Graphics-Objekten arbeiten.
 *
 * @version 26 Dec 2001
 * @author G9-Gui
 * @see java.awt.Graphics
 */

class G9gui {

    public static int corr = -1;
/**
 * Zeichnet einen Pfeil mit rechtwinkliger Spitze
 * @param g das zu bearbeitende Graphics-Objekt
 * @param x1 die Abszisse des Pfeilanfangs
 * @param y1 die Ordinate des Pfeilanfangs
 * @param x2 die Abszisse des Pfeilendes
 * @param y2 die Ordinate des Pfeilendes
 * @param l die Länge der Pfeilspitze
 */    
    public static void malPfeil(Graphics g, int x1, int y1, int x2, int y2, int l) 
    {
	g.drawLine(x1,y1,x2,y2);
	int dx = (x2-x1);
	int dy = (y2-y1);
	double q;
	if (dx==0) { q = ((double)dy)/(double).1d; }
	else { q = ((double)dy)/(double)dx;}	
	double w = Math.atan(q);
	w = w + (Math.PI / 4);	
	if ((dx >= 0 && dy < 0)||(dx < 0 && dy > 0)) corr = -1;
	else corr = 1;
	int px = (int) (x2 - Math.cos(w) * l * sign(dx));
	int py = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	g.drawLine(x2,y2,px,py);
	w = Math.atan(q);
	w = w - (Math.PI / 4);
	px = (int) (x2 - Math.cos(w) * l * sign(dx));
	py = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	g.drawLine(x2,y2,px,py);
    }

/**
 * Zeichnet einen Pfeil mit ausgefüllter rechtwinkliger Spitze
 * @param g das zu bearbeitende Graphics-Objekt
 * @param x1 die Abszisse des Pfeilanfangs
 * @param y1 die Ordinate des Pfeilanfangs
 * @param x2 die Abszisse des Pfeilendes
 * @param y2 die Ordinate des Pfeilendes
 * @param l die Länge der Pfeilspitze
 */

    public static void malVollenPfeil(Graphics g, int x1, int y1, int x2, int y2, int l) 
    {
	g.drawLine(x1,y1,x2,y2);
	int dx = (x2-x1);
	int dy = (y2-y1);
	double q;
	if (dx==0) { q = ((double)dy)/(double).1d; }
	else { q = ((double)dy)/(double)dx;}	
	double w = Math.atan(q);
	w = w + (Math.PI / 4);
	if ((dx >= 0 && dy < 0)||(dx < 0 && dy > 0)) corr = -1;
	else corr = 1;
	int px = (int) (x2 - Math.cos(w) * l * sign(dx));
	int py = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	w = Math.atan(q);
	w = w - (Math.PI / 4);
	int px2 = (int) (x2 - Math.cos(w) * l * sign(dx));
	int py2 = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	g.fillPolygon(new int[] {x2, px, px2}, new int[] {y2, py, py2}, 3);
    }

    public static int sign(int zahl)
    {
	if (zahl > 0) return 1;
	else return -1;

    }

/**
 * Zeichnet einen Pfeil 
 * @param g das zu bearbeitende Graphics-Objekt
 * @param x1 die Abszisse des Pfeilanfangs
 * @param y1 die Ordinate des Pfeilanfangs
 * @param x2 die Abszisse des Pfeilendes
 * @param y2 die Ordinate des Pfeilendes
 * @param l die Länge der Pfeilspitze
 * @param grad der Winkel zwischen Pfeilspitzenschenkeln und Pfeilrumpf
 */

    public static void malPfeil(Graphics g, int x1, int y1, int x2, int y2, int l, int grad) 
    {
	g.drawLine(x1,y1,x2,y2);
	int dx = (x2-x1);
	int dy = (y2-y1);
	double q;
	if (dx==0) { q = ((double)dy)/(double).1d; }
	else { q = ((double)dy)/(double)dx;}	
	double w = Math.atan(q);
	w = w + (2 * Math.PI * grad / 360);
	if ((dx >= 0 && dy < 0)||(dx < 0 && dy > 0)) corr = -1;
	else corr = 1;
	int px = (int) (x2 - Math.cos(w) * l * sign(dx));
	int py = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	g.drawLine(x2,y2,px,py);
	w = Math.atan(q);
	w = w - (2 * Math.PI * grad / 360);
	px = (int) (x2 - Math.cos(w) * l * sign(dx));
	py = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	g.drawLine(x2,y2,px,py);
    }

/**
 * Zeichnet einen Pfeil mit ausgefüllter Spitze 
 * @param g das zu bearbeitende Graphics-Objekt
 * @param x1 die Abszisse des Pfeilanfangs
 * @param y1 die Ordinate des Pfeilanfangs
 * @param x2 die Abszisse des Pfeilendes
 * @param y2 die Ordinate des Pfeilendes
 * @param l die Länge der Pfeilspitze
 * @param grad der Winkel zwischen Pfeilspitzenschenkeln und Pfeilrumpf
 */

    public static void malVollenPfeil(Graphics g, int x1, int y1, int x2, int y2, int l, int grad) 
    {
	g.drawLine(x1,y1,x2,y2);
	int dx = (x2-x1);
	int dy = (y2-y1);
	double q;
	if (dx==0) { q = ((double)dy)/(double).1d; }
	else { q = ((double)dy)/(double)dx;}	
	double w = Math.atan(q);
	w = w + (2 * Math.PI * grad / 360);
	if ((dx >= 0 && dy < 0)||(dx < 0 && dy > 0)) corr = -1;
	else corr = 1;
	int px = (int) (x2 - Math.cos(w) * l * sign(dx));
	int py = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	w = Math.atan(q);
	w = w - (2 * Math.PI * grad / 360);
	int px2 = (int) (x2 - Math.cos(w) * l * sign(dx));
	int py2 = (int) (y2 - Math.sin(w) * l * sign(dy) * corr);
	g.fillPolygon(new int[] {x2, px, px2}, new int[] {y2, py, py2}, 3);
    }

    // The "Smoothness" of the Curve which is made of Lines
    final static int MAX_LINES = 25;

    static Dimension preferredSize = new Dimension(300,100);

    // The 4 Control-Points and the Lines Points
    static double ctrl_pts[][] = { {0d,0d}, {0d,100d}, {100d,0d}, {100d,100d} };
    static double line_pts[][] = new double[MAX_LINES][2];

    static public void drawSpline(Graphics g, int x1, int y1, int x2, int y2) {
	// Sets the Control-Points for this Bezier Spline. 
	if(x1 <= x2) { // Steps in Depency Order
	    ctrl_pts[0][0] = (double)x1;
	    ctrl_pts[0][1] = (double)y1;
	    ctrl_pts[1][0] = (double)x2;
	    ctrl_pts[1][1] = (double)y1;
	    ctrl_pts[2][0] = (double)x1;
	    ctrl_pts[2][1] = (double)y2;
	    ctrl_pts[3][0] = (double)x2;
	    ctrl_pts[3][1] = (double)y2;
	    //calc_line_pts();
	} else { // Steps NOT in Depency Order (Broken Depency!!!)
	    ctrl_pts[0][0] = (double)x1;
	    ctrl_pts[0][1] = (double)y1;
	    ctrl_pts[1][0] = 50d+(double)x1;
	    ctrl_pts[1][1] = 50d+(double)y1;
	    ctrl_pts[2][0] = -50d+(double)x2;
	    ctrl_pts[2][1] = -50d+(double)y2;
	    ctrl_pts[3][0] = (double)x2;
	    ctrl_pts[3][1] = (double)y2;
	}
	//calc_line_pts();
	// Punkte berechnen
	for(int i=0; i<MAX_LINES; i++) {
	    double a,b,c,d;
	    double t = (1d/(double)MAX_LINES) * (double)i;
	    a = ctrl_pts[0][0];
	    b = ctrl_pts[1][0];
	    c = ctrl_pts[2][0];
	    d = ctrl_pts[3][0];
	    line_pts[i][0] = a-3d*a*t+3d*t*b+3d*a*t*t-6d*t*t*b+3d*t*t*c-a*t*t*t+3d*t*t*t*b-3d*t*t*t*c+t*t*t*d;
	    a = ctrl_pts[0][1];
	    b = ctrl_pts[1][1];
	    c = ctrl_pts[2][1];
	    d = ctrl_pts[3][1];
	    line_pts[i][1] = a-3d*a*t+3d*t*b+3d*a*t*t-6d*t*t*b+3d*t*t*c-a*t*t*t+3d*t*t*t*b-3d*t*t*t*c+t*t*t*d;
	}
	// That's the Painting ;)
	for(int i=1; i<MAX_LINES; i++) 
	    g.drawLine((int)line_pts[i-1][0], (int)line_pts[i-1][1], (int)line_pts[i][0], (int)line_pts[i][1]);
	malVollenPfeil(g, (int)line_pts[MAX_LINES-1][0], (int)line_pts[MAX_LINES-1][1], x2, y2, 6);
    }
}
