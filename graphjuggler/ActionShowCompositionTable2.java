import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class ActionShowCompositionTable2 implements java.awt.event.ActionListener
{
    Vector g;
    VS vs;
    String name;
    DialogSort ds;

    public ActionShowCompositionTable2 (DialogSort ds, Vector g, VS vs, String name)
    {
	this.ds=ds;
	this.g=g;
	this.vs=vs;
	this.name=name;
    }
    //int[] morphNr;

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	//morphNr = new int[g.size()];
	Vector sort = new Vector();
	for (int i=0; i<g.size(); i++) {
	    for (int j=0; j<g.size(); j++) {
		if (i+1 == Integer.parseInt(((JTextField)(ds.vMorphNr.get(j))).getText())) {
		    sort.add(g.get(j));
		    //System.out.println(""+i+" "+j);
		}
	    }
	}
	int[][] table = new int[sort.size()][sort.size()];
	int[] map3;
	for (int i=0; i<sort.size(); i++) {
	    for (int j=0; j<sort.size(); j++) {
		map3 = comp((int[])sort.get(i), (int[])sort.get(j));
		for (int k=0; k<sort.size(); k++) {
		    if (equal(map3, (int[])sort.get(k))) {
			table[i][j]=k;
		    }
		}
	    }
	}
	// In Datei ausgeben.
	JFileChooser fc = new JFileChooser();
	File file;
	int returnVal = fc.showSaveDialog(ds);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    file = fc.getSelectedFile();
	    System.out.println("Saving: " + file.getAbsolutePath() + ".");
	} else {
	    return;
	}
	String filename = file.getAbsolutePath();
	//	
	Latex.openFile(filename);
	Latex.writeFHeader();
	Latex.printFTable(table);
	Latex.printFClass(sort, name);
	Latex.writeFEnd();
	Latex.closeFile();
    }

    public int[] comp(int[] map1, int[] map2) {
	int[] comp = new int[map2.length];
	for (int i=0; i<map2.length; i++) {
	    comp[i] = map1[map2[i]];
	}
	return comp;
    }

    public boolean equal(int[] map1, int[] map2) {
	for (int i=0; i<map1.length; i++) {
	    if (map1[i] != map2[i]) return false;
	}
	return true;
    }

}



