import java.awt.*;
import javax.swing.*;

public class ActionCard implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionCard (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	if (vs.endomorphisms) {
	    int nr = vs.getSelected();
	    if (nr == -1) {
		System.out.println("No graph selected!"); 
		(new DialogMessage("No graph selected!")).show();
		return;
	    }
	    vs.createMatrices(nr,nr);
	    vs.findMorphisms();
	    (new DialogCard(vs)).show();
	} else {
	    (new DialogHom(vs)).show();
	}
    }
}



