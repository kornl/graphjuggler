import java.util.Vector;
import java.awt.*;
import javax.swing.*;

public class ActionCombine implements java.awt.event.ActionListener
{
    VS vs;

    public ActionCombine (VS vs)
    {
	this.vs=vs;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	(new DialogCombine(vs)).show();
    }
}



