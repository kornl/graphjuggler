import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class MyInternalFrame extends JInternalFrame implements InternalFrameListener{
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    JLabel statusbar;
    NetzListe nl;
    VS vs;
    int nr;

    public MyInternalFrame(JLabel statusbar, VS vs) {
        super("Graph #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
	this.nr = openFrameCount-1;
	this.statusbar = statusbar;
	this.vs = vs;

        //...Create the GUI and put it in the window...
	nl = new NetzListe(statusbar, vs);
	JScrollPane sPane = new JScrollPane(nl);
	getContentPane().add(sPane);

	// Listener anhängen_
	addInternalFrameListener(this);

        //...Then set the window size or call pack...
        setSize(300,300);

        //Set the window's location.	
        //setLocation(xOffset*(openFrameCount-1), yOffset*(openFrameCount-1));
        setLocation((xOffset*(openFrameCount-1))%120, (yOffset*(openFrameCount-1))%120);
    }

    public void internalFrameActivated(InternalFrameEvent e) {}
    public void internalFrameClosed(InternalFrameEvent e) {}
    public void internalFrameClosing(InternalFrameEvent e) {
	//vs.removeNL();
    }
    public void internalFrameDeactivated(InternalFrameEvent e) {}
    public void internalFrameDeiconified(InternalFrameEvent e) {}
    public void internalFrameIconified(InternalFrameEvent e) {}
    public void internalFrameOpened(InternalFrameEvent e) {}
   

}
