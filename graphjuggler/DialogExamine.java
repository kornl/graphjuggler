import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

/**
 * 
 * \brief JDialog, which shows information about a graph
 *
 */

class DialogExamine extends JDialog implements ActionListener {

    VS vs;

    public DialogExamine(VS vs) 
    {	
	super((Frame) null, "Information about this graph");
	this.vs=vs;
	getContentPane().add(getPanel());
	setSize(500,500);
	setLocation(200,200);
	setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent ev) {	
	dispose();
    }

    public JPanel getPanel() {
	JPanel p = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	p.setLayout(gridbag);
	c.weightx = 1.0;
	c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
	JLabel l;

	l = new JLabel("Vertices: "+vs.e1.length);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(l, c);
	p.add(l, c); 

	l = new JLabel("Sorry, no further Information available.");
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(l, c);
	p.add(l, c); 
	    
	return p;
    }
}

