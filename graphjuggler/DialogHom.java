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


class DialogHom extends JDialog implements ActionListener {

    VS vs;

    public DialogHom(VS vs) 
    {	
	super((Frame) null, "Homomorphisms");
	this.vs=vs;
	getContentPane().add(getPanel());
	setSize(500,500);
	setLocation(200,200);
	setVisible(true);
    }

    JTextField tf1, tf2;

    public void actionPerformed(java.awt.event.ActionEvent e) {
	int nr1 = Integer.parseInt(tf1.getText())-1;
	int nr2 = Integer.parseInt(tf2.getText())-1;
	vs.createMatrices(nr1,nr2);
	vs.findMorphisms();
	(new DialogCard(vs)).show();
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
	l = new JLabel("From graph nr.");
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(l, c);
	p.add(l, c); 
	    
	tf1 = new JTextField(""+(vs.getSelected()+1));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(tf1, c);
	p.add(tf1, c);

	l = new JLabel("to graph nr.");
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(l, c);
	p.add(l, c); 
	    
	tf2 = new JTextField("");
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(tf2, c);
	p.add(tf2, c);
	
	JButton jbOK = new JButton("OK");
	jbOK.addActionListener(this);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbOK, c);
	p.add(jbOK, c);

	return p;
    }
}

	  
