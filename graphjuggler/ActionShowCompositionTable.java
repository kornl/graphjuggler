import java.util.Vector;
import java.awt.*;
import javax.swing.*;

public class ActionShowCompositionTable implements java.awt.event.ActionListener
{
    Vector g;
    VS vs;
    String name;

    public ActionShowCompositionTable (Vector g, VS vs, String name)
    {
	this.g=g;
	this.vs=vs;
	this.name=name;
    }

    public void actionPerformed(java.awt.event.ActionEvent event)
    {	
	(new DialogSort(g, vs, name)).show();
    }
}



