import java.awt.*;
import javax.swing.*;
import java.io.*;

public class ActionExportToLatex implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionExportToLatex (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	int nr = -1;
	for (int i=0; i< vs.nl.size(); i++) {
	    if(((MyInternalFrame)(vs.nl.get(i))).isSelected()) {
		nr = i;
	    }
	}
	if (nr == -1) {
	    System.out.println("No graph selected!"); 
	    (new DialogMessage("No graph selected!")).show();
	    return;
	}
	NetzListe nl =((MyInternalFrame)(vs.nl.get(nr))).nl;
	JFileChooser fc = new JFileChooser();
	File file;
	int returnVal = fc.showSaveDialog(parent);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    file = fc.getSelectedFile();
	    System.out.println("Export to: " + file.getAbsolutePath() + ".");
	} else {
	    return;
	}
	String filename = file.getAbsolutePath();
	//	
	Latex.openFile(filename);
	Latex.writeFHeader2();
	Latex.printlnF("\\setlength{\\unitlength}{0.2mm}");
	Latex.printlnF("\\begin{picture}(500,500)"); 
	Latex.printlnF("\\psset{unit=0.2mm,linewidth=1pt}"); 	
	for(int i = 0; i < nl.anzahl; i++) {
	    Latex.printF("\\rput("+nl.knoten[i].x+","+nl.knoten[i].y+"){");
	    Latex.printlnF("\\cnode(0,0){"+Node.r+"}{Node"+i+"}{"
			   +((vs.shownr)?(""+nl.knoten[i].nr):(nl.knoten[i].name))+"}}");
	}
	for(int i = 0; i < nl.kAnzahl; i++) {
	    int node1 = nl.kanten[i][0];
	    int node2 = nl.kanten[i][1];
	    if (vs.directed) {
		Latex.printlnF("\\ncline{->}{Node"+node1+"}{Node"+node2+"}");
	    } else {
		Latex.printlnF("\\ncline{-}{Node"+node1+"}{Node"+node2+"}");
	    }
	}
	Latex.printlnF("\\end{picture}");
	Latex.writeFEnd();
	Latex.closeFile();
    }
}



