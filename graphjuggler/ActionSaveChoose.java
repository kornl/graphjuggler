import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ActionSaveChoose implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionSaveChoose (FrameViewer parent, VS vs)
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
	JFileChooser fc = new JFileChooser();
	File file;
	int returnVal = fc.showSaveDialog(parent);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    file = fc.getSelectedFile();
	    System.out.println("Saving: " + file.getAbsolutePath() + ".");
	} else {
	    return;
	}
	SaveGraphs s = new SaveGraphs();
	s.fileName=file.getAbsolutePath();
	vs.createMatrices(nr,nr);	
	s.e1 = vs.e1; 
	s.k1 = vs.k1;
	s.b1 = vs.b1;
	s.write();
    }
}



