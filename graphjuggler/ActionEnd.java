import java.awt.*;
import javax.swing.*;

public class ActionEnd implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionEnd (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	vs.setEnd(!vs.endomorphisms);
	((JButton) event.getSource()).setSelected(vs.endomorphisms);
    }
}



