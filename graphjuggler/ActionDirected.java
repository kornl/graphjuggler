import java.awt.*;
import javax.swing.*;

public class ActionDirected implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionDirected (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	vs.directed=!vs.directed;
	((JButton) event.getSource()).setSelected(vs.directed);
	vs.repaint();
    }
}



