import java.awt.*;
import javax.swing.*;

public class ActionSystemExit implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionSystemExit(FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	System.exit(0);
    }
}



