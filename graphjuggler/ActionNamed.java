import java.awt.*;
import javax.swing.*;

public class ActionNamed implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionNamed (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	vs.shownr=!vs.shownr;
	((JButton) event.getSource()).setSelected(vs.shownr);
	vs.repaint();
    }
}



