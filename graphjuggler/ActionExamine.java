import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class ActionExamine implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionExamine (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	int nr = vs.getSelected();
	if (nr == -1) {
	    System.out.println("No graph selected!"); 
	    (new DialogMessage("No graph selected!")).show();
	    return;
	}
	vs.createMatrices(nr,nr);	
	(new DialogExamine(vs)).show();
    }
}



