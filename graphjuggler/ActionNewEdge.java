import java.awt.*;
import javax.swing.*;

public class ActionNewEdge implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionNewEdge (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent e)
    {	
	vs.newVertex=false;
	vs.newEdge=true;
    }
}



