import java.awt.*;
import javax.swing.*;

public class ActionLoad implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionLoad (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	SaveGraphs s = new SaveGraphs();
	s.read();
	vs.e1 = s.e1;
	vs.k1 = s.k1;
	vs.b1 = s.b1;
	vs.restoreMatrices();
    }
}



