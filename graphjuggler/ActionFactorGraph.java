import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class ActionFactorGraph implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionFactorGraph (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	int[][] e1, e;
	int[][] k1, k;
	String[] b;
	int nr = vs.getSelected();
	if (nr == -1) {
	    System.out.println("No graph selected!"); 
	    (new DialogMessage("No graph selected!")).show();
	    return;
	}
	vs.createMatrices(nr,nr);	
        e1 = vs.e1; k1 = vs.k1; 
	int v1 = e1.length;
	String[] d = new String[v1];
	boolean[] doppelt = new boolean[v1];
	int anzahl = v1;
	System.out.println("Bilde Äquivalenzklassen.");
	for (int i=0; i<v1; i++) {
	    doppelt[i]=false;
	    d[i] = ""+(i+1);
	    //gefunden:
	    for (int j=0; j<i; j++) {
		if (compare(e1[i],e1[j])) {
		    doppelt[i]=true;
		    d[j]=d[j]+", "+(i+1);
		    anzahl--;
		    System.out.println("Es sind "+i+" und "+j+" in einer Klasse.");		    
		    j=i;
		    //break gefunden;
		} 
	    }
	}
	k = new int[anzahl][2];
	e = new int[anzahl][anzahl];
	b = new String[anzahl];
	int wo = 0;
	for (int i=0; i<v1; i++) {
	    if(!doppelt[i]) {
		int wo2 = 0; 
		for (int j=0; j<v1; j++) {
		    if(!doppelt[j]) {
			e[wo][wo2]=e1[i][j];
			wo2++;
		    }
		}
		k[wo]=myCopy(k1[i]);
		b[wo]=d[i];
		wo++;
	    }
	}
	vs.e1 = e;
	vs.k1 = k;
	vs.b1 = b;
	vs.restoreMatrices();
    }

    public int[] myCopy(int[] e) {
	int[] r = new int[e.length];
	for (int i=0; i<r.length; i++) {
	    r[i]=e[i];
	}
	return r;
    }

    public boolean compare(int[] r1, int[] r2) {
	for (int i=0; i<r1.length; i++) {
	    if (r1[i]!=r2[i]) return false; 
	}
	return true;
    }
}



