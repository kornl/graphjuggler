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


class DialogCard extends JDialog implements ActionListener {

    VS vs;

    public DialogCard(VS vs) 
    {	
	super((Frame) null, "Cardinalities");
	this.vs = vs;
	getContentPane().add(getPanel());
	setSize(500,500);
	setLocation(200,200);
	setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
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

	JLabel lGra = new JLabel("Iso: "+vs.aut.size());
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(lGra, c);
	p.add(lGra, c); 
 
	JButton jbGra = new JButton("Print");
	jbGra.addActionListener(new ActionPrintMorph(vs.aut, vs, "Iso"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	lGra = new JLabel("Str: "+vs.send.size());
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(lGra, c);
	p.add(lGra, c); 
 
	jbGra = new JButton("Print");
	jbGra.addActionListener(new ActionPrintMorph(vs.send, vs, "Str"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	jbGra = new JButton("Show Composition Table");
	jbGra.addActionListener(new ActionShowCompositionTable(vs.send, vs, "Str"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	lGra = new JLabel("QStr: "+vs.qend.size());
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(lGra, c);
	p.add(lGra, c); 
 
	jbGra = new JButton("Print");
	jbGra.addActionListener(new ActionPrintMorph(vs.qend, vs, "QStr"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	lGra = new JLabel("LStr: "+vs.lend.size());
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(lGra, c);
	p.add(lGra, c); 
 
	jbGra = new JButton("Print");
	jbGra.addActionListener(new ActionPrintMorph(vs.lend, vs, "LStr"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	lGra = new JLabel("HStr: "+vs.hend.size());
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(lGra, c);
	p.add(lGra, c); 
 
	jbGra = new JButton("Print");
	jbGra.addActionListener(new ActionPrintMorph(vs.hend, vs, "HStr"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	lGra = new JLabel("Hom: "+vs.end.size());
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(lGra, c);
	p.add(lGra, c); 
 
	jbGra = new JButton("Print");
	jbGra.addActionListener(new ActionPrintMorph(vs.end, vs, "Hom"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	jbGra = new JButton("Show Composition Table");
	jbGra.addActionListener(new ActionShowCompositionTable(vs.end, vs, "End"));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbGra, c);
	p.add(jbGra, c);

	if (vs.endomorphisms) {
	    JButton jbTest = new JButton("Test, whether HEnd, LEnd, QEnd are closed");
	    jbTest.addActionListener(new ActionTestClosed(vs));
	    c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(jbTest, c);
	    p.add(jbTest, c);
	}

	JButton jbClose = new JButton("Close");
	jbClose.addActionListener(this);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbClose, c);
	p.add(jbClose, c);

	return p;
    }
}

	  
