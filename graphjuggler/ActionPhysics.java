import java.awt.*;
import javax.swing.*;
import java.util.Vector;

public class ActionPhysics implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionPhysics (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	int nr = vs.getSelected();
	if (nr == -1) {System.out.println("Kein Graph aktiv!"); return;}
	(((MyInternalFrame)(vs.nl.get(nr))).nl).changePhysics();
    }
}



