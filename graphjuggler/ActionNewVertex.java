import java.awt.*;
import javax.swing.*;

public class ActionNewVertex implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionNewVertex (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent e)
    {	
	vs.newVertex=true;
	vs.newEdge=false;
    }
}



