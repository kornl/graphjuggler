import java.awt.*;
import javax.swing.*;

public class ActionSave implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionSave (FrameViewer parent, VS vs)
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
	SaveGraphs s = new SaveGraphs();
	vs.createMatrices(nr,nr);	
	s.e1 = vs.e1; 
	s.k1 = vs.k1;
	s.b1 = vs.b1;
	s.write();
    }
}



