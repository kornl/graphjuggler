import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class DialogInfo extends JDialog
{

    public DialogInfo(java.awt.Component parent){
	super((Frame) parent, "Info"); //, true);
	getContentPane().setLayout(new BorderLayout());	
	getContentPane().add(new ImagePanel(Toolkit.getDefaultToolkit().getImage("info.jpg")));
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension labelSize = getPreferredSize();
	setLocation(screenSize.width/2 - 100,
		    screenSize.height/2 - 100);
	pack();
	setSize(300,150+30);
	setVisible(true);
    }

}





