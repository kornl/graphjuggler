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


class DialogCombine extends JDialog implements ActionListener {

    VS vs;

    public DialogCombine(VS vs) 
    {	
	super((Frame) null, "Combine two graphs");
	this.vs=vs;
	getContentPane().add(getPanel());
	setSize(500,500);
	setLocation(200,200);
	setVisible(true);
    }

    JTextField tf1, tf2;
    JComboBox comList;
    String[] comStrings = { "Union", "Join", "Edgesum", "Cross Product", "Box Product" };
    int[][] e1, e2, e;
    int[][] k1, k2, k;
    String[] b;

    public void actionPerformed(java.awt.event.ActionEvent ev) {	
	int nr1 = Integer.parseInt(tf1.getText())-1;
	int nr2 = Integer.parseInt(tf2.getText())-1;
	vs.createMatrices(nr1,nr2);
	e1 = vs.e1; k1 = vs.k1;	e2 = vs.e2; k2 = vs.k2;
	int v1 = e1.length; int v2 = e2.length;
	int max = 0; int max2 = 0;
	for (int i=0;i<v1;i++) {
	    if (k1[i][0]>max) max = k1[i][0];
	}
	for (int i=0;i<v2;i++) {
	    if (k2[i][0]>max2) max2 = k2[i][0];
	}
	max = max + (2*Node.r);	max2 = max2 + (2*Node.r);
	int scale = max2/(2*Node.r);
	switch (comList.getSelectedIndex()) {
	    case 0: System.out.println("Compute Union");
		e = new int[v1+v2][v1+v2];
		k = new int[v1+v2][2];
		b = new String[v1+v2];
		for (int i=0;i<v1;i++) {
		    k[i][0]=k1[i][0]; k[i][1]=k1[i][1];
		    b[i]=""+i;
		    for (int j=0;j<v1;j++) {
			e[i][j]=e1[i][j];			
		    }
		}
		for (int i=0;i<v2;i++) {
		    k[v1+i][0]=k2[i][0]+max; k[v1+i][1]=k2[i][1];
		    b[v1+i]=""+i;
		    for (int j=0;j<v2;j++) {
			e[v1+i][v1+j]=e2[i][j];
		    }
		}				
		break;
	    case 1: System.out.println("Compute Join");		
		e = new int[v1+v2][v1+v2];
		k = new int[v1+v2][2];
		b = new String[v1+v2];
		for (int i=0;i<v1+v2;i++) {
		    for (int j=0;j<v1+v2;j++) {
			e[i][j]=1;
		    }
		}
		for (int i=0;i<v1;i++) {
		    k[i][0]=k1[i][0]; k[i][1]=k1[i][1];
		    b[i]=""+i;
		    for (int j=0;j<v1;j++) {
			e[i][j]=e1[i][j];			
		    }
		}
		for (int i=0;i<v2;i++) {
		    k[v1+i][0]=k2[i][0]+max; k[v1+i][1]=k2[i][1];
		    b[v1+i]=""+i;
		    for (int j=0;j<v2;j++) {
			e[v1+i][v1+j]=e2[i][j];
		    }
		}				
		break;
	    case 2: System.out.println("Compute Edgesum");
		if (v1!=v2) {
		    System.out.println("Graphs have not the same edges.");
		    return;
		}
		e = new int[v1][v1];
		k = new int[v1][2];
		b = new String[v1];
		for (int i=0;i<v1;i++) {
		    k[i][0]=k1[i][0]; k[i][1]=k1[i][1];
		    b[i]=""+i;
		    for (int j=0;j<v1;j++) {
			if (e1[i][j]==1) e[i][j]=1;			
		    }
		}
		for (int i=0;i<v2;i++) {
		    for (int j=0;j<v2;j++) {
			if (e2[i][j]==1) e[i][j]=1;			
		    }
		}
		break;
	    case 3: System.out.println("Cross Product");		
		e = new int[v1*v2][v1*v2];
		k = new int[v1*v2][2];
		b = new String[v1*v2];	
		for (int i=0;i<v1;i++) {
		    for (int j=0;j<v2;j++) {
			k[(i*v2)+j][0]=k1[i][0]+k2[j][0]; 
			k[(i*v2)+j][1]=k1[i][1]+k2[j][1];
			b[(i*v2)+j] = "("+(i+1)+","+(j+1)+")";
			for (int k=0;k<v1;k++) {
			    for (int l=0;l<v2;l++) {
				if (e1[i][k]==1 && e2[j][l]==1) {
				    e[(i*v2)+j][(k*v2)+l]=1;
				}
			    }
			}
		    }
		}		
		break;
	    case 4: System.out.println("Box Product");		
		e = new int[v1*v2][v1*v2];
		k = new int[v1*v2][2];
		b = new String[v1*v2];	
		for (int i=0;i<v1;i++) {
		    for (int j=0;j<v2;j++) {
			k[(i*v2)+j][0]=k1[i][0]+k2[j][0]; 
			k[(i*v2)+j][1]=k1[i][1]+k2[j][1];
			b[(i*v2)+j] = "("+(i+1)+","+(j+1)+")";
			for (int k=0;k<v1;k++) {
			    for (int l=0;l<v2;l++) {
				if (e1[i][k]==1 && j==l) {
				    e[(i*v2)+j][(k*v2)+l]=1;
				}
				if (i==k && e2[j][l]==1) {
				    e[(i*v2)+j][(k*v2)+l]=1;
				}
			    }
			}
		    }
		}		
		break;
	}
	vs.e1 = e; vs.k1 = k; vs.b1 = b;
	vs.restoreMatrices();	
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
	l = new JLabel("First graph:");
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(l, c);
	p.add(l, c); 
	    
	tf1 = new JTextField(""+(vs.getSelected()+1));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(tf1, c);
	p.add(tf1, c);

	l = new JLabel("Second graph:");
	c.gridwidth = GridBagConstraints.RELATIVE;
	gridbag.setConstraints(l, c);
	p.add(l, c); 
	    
	tf2 = new JTextField("");
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(tf2, c);
	p.add(tf2, c);

        comList = new JComboBox(comStrings);
        comList.setSelectedIndex(0);
        //comList.addActionListener(new ListListener(this));
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(comList, c);
	p.add(comList, c);
	
	JButton jbOK = new JButton("OK");
	jbOK.addActionListener(this);
	c.gridwidth = GridBagConstraints.REMAINDER;
	gridbag.setConstraints(jbOK, c);
	p.add(jbOK, c);

	return p;
    }
}

//class ListListener implements ActionListener { 
//    public void actionPerformed(java.awt.event.ActionEvent e) {
//    }
//}
