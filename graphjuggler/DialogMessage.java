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


class DialogMessage extends JDialog implements ActionListener {

    public DialogMessage(String message) 
    {	
	super((Frame) null, "Message");
	getContentPane().add(getPanel(message));
	//setSize(500,500);
	pack();
	setLocation(200,200);
	setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
	dispose();
    }

    public JPanel getPanel(String message) {
	JPanel p = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	p.setLayout(gridbag);
	c.weightx = 1.0;
	c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
	JLabel l = new JLabel(message);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(l, c);
	p.add(l, c);
	JButton jbOK = new JButton("OK");
	jbOK.addActionListener(this);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbOK, c);
	p.add(jbOK, c);
	return p;
    }
}

	  
