import java.util.Vector;
import java.awt.*;
import javax.swing.*;

public class ActionPrintMorph implements java.awt.event.ActionListener
{
    //FrameViewer parent;
    Vector g;
    VS vs;
    String name;

    public ActionPrintMorph (Vector g, VS vs, String name)
    {
	this.g=g;
	this.vs=vs;
	this.name=name;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	Latex.printClass(g, name);
    }
}



