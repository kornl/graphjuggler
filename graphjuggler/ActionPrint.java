import java.awt.*;
import javax.swing.*;

public class ActionPrint implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionPrint (FrameViewer parent, VS vs)
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
	PrintGraph.print(((MyInternalFrame)vs.nl.get(nr)).nl);
    }
}



