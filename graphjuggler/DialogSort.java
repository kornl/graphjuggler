import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

/**
 * 
 * \brief JDialog, in dem die Hilfe angezeigt wird
 *
 * @version 28 Dec 2001
 * @author G9-Gui
 */


class DialogSort extends JDialog implements ActionListener {

    Vector g;
    VS vs;
    String name;

    public DialogSort(Vector g, VS vs, String name) 
    {	
	super((Frame) null, "Sort Endomorphisms");
	this.g=g;
	this.vs=vs;
	this.name=name;
	getContentPane().add(getPanel());
	setSize(500,500);
	setLocation(200,200);
	setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
	dispose();
    }

    public String getString(int[] map){
	String s = "(";
	for (int i=0; i< map.length; i++) {
	    s=s+(" "+(map[i]+1));
	}
	return s+")";
    }

    Vector vMorphNr = new Vector();
 
    public JPanel getPanel() {
	JPanel p = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	p.setLayout(gridbag);
	c.weightx = 1.0;
	c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
	JLabel lmorph;
	JTextField tfMorphNr;
	for (int i=0; i < g.size(); i++){
	    lmorph = new JLabel(getString((int[])g.get(i)));
	    c.gridwidth = GridBagConstraints.RELATIVE;
	    gridbag.setConstraints(lmorph, c);
	    p.add(lmorph, c); 
	    
	    tfMorphNr = new JTextField(""+(i+1));
	    c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(tfMorphNr, c);
	    p.add(tfMorphNr, c);
	    vMorphNr.add(tfMorphNr);
	}

	JButton jbOK = new JButton("OK");
	jbOK.addActionListener(new ActionShowCompositionTable2(this, g, vs, name));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbOK, c);
	p.add(jbOK, c);

	return p;
    }
}

	  
