import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ActionLoadChoose implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionLoadChoose (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	JFileChooser fc = new JFileChooser();
	int returnVal = fc.showOpenDialog(parent);
	File file;
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	     file = fc.getSelectedFile();
	    System.out.println("Opening: " +file.getAbsolutePath() +  ".");
	} else {
	    return;
	}
	SaveGraphs s = new SaveGraphs();
	s.fileName=file.getAbsolutePath();
	s.read();
	vs.e1 = s.e1;
	vs.k1 = s.k1;
	vs.b1 = s.b1;
	vs.restoreMatrices();
    }
}



