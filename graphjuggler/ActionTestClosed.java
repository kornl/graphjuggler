import java.awt.*;
import javax.swing.*;

public class ActionTestClosed implements java.awt.event.ActionListener
{
    VS vs;

    public ActionTestClosed (VS vs)
    {
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	vs.test();
    }
}



