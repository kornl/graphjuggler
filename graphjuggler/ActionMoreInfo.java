import java.awt.*;
import javax.swing.*;

public class ActionMoreInfo implements java.awt.event.ActionListener
{
    FrameViewer parent;
    VS vs;

    public ActionMoreInfo (FrameViewer parent, VS vs)
    {
	this.parent=parent;
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	vs.moreInfo=!vs.moreInfo;
	((JButton) event.getSource()).setSelected(vs.moreInfo);
	vs.repaint();
    }
}



