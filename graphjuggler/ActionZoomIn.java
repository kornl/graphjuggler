import java.awt.*;
import javax.swing.*;

public class ActionZoomIn implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionZoomIn (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent e)
    {	
	vs.setZoom(vs.getZoom()*1.25);
	parent.refresh();
    }
}



