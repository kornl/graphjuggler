import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.tree.*;

/**
 * JFrame, das die NetzListe anzeigt.
 */

public class FrameViewer extends JFrame implements WindowListener, ActionListener{
    public VS vs;
    public JLabel statusBar;
    Vector nl = new Vector();
    JRootPane mainPanel = new JRootPane();
    JDesktopPane desktop;
    
    public FrameViewer() {
	super("Graph Morphism Juggler");
	vs = new VS();
	vs.setFrameViewer(this); 
	// JMenu einrichten:
	JMenuBar menubar = new JMenuBar();
	JMenu jmGraph = new JMenu("Graph");
	JMenuItem jmiLoad = new JMenuItem("Load");
	JMenuItem jmiSave = new JMenuItem("Save");
	JMenuItem jmiBeenden = new JMenuItem("Exit");
	jmGraph.add(jmiLoad);
	jmGraph.add(jmiSave);
	jmGraph.add(jmiBeenden);
	JMenu jmInfo = new JMenu("Info");
	JMenuItem jmiInfo = new JMenuItem("Info");
	jmInfo.add(jmiInfo);
	menubar.add(jmGraph);
	menubar.add(jmInfo);
	setJMenuBar(menubar);
	// ActionListener anhängen
	jmiBeenden.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    System.exit(0);
		}
	    });
	jmiLoad.addActionListener(new ActionLoadChoose(this,vs));
	jmiSave.addActionListener(new ActionSaveChoose(this,vs));
	jmiInfo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    new DialogInfo(new JFrame());
		}
	    });
	// anderes:
	setVisible(false);
	getContentPane().setLayout(new BorderLayout());
	// Füge Toolbar hinzu:
       	getContentPane().add("North", getToolBar());
       	getContentPane().add("West", getToolBar2());
       	getContentPane().add("East", getToolBar3());
	// Füge Statusbar hinzu:
	statusBar = new JLabel("StatusBar");
	getContentPane().add("South", statusBar);
	// Erzeuge Desktop:
	desktop = new JDesktopPane();
	createNLW();
	mainPanel.setContentPane(desktop);
	System.out.println("Initialisiere Netzliste");
	//((MyInternalFrame)nl.get(0)).setClosable(false);
	System.out.println("Fertig mit Netzliste.");
	getContentPane().add("Center", new JScrollPane(mainPanel));
	//Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	//setLocation(20,20);
	//pack();
	// Positionieren:
	int inset = 50;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);	
	setVisible(true);
	this.addWindowListener(this);
	System.out.println("Fertig.");
    }

    public MyInternalFrame createNLW() {
	MyInternalFrame frame = new MyInternalFrame(statusBar, vs);
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
	nl.add(frame.nl);
	vs.addNL(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
	return frame;
    }

    /**
     * Übernimmt die neuen Einstellungen
     */

    public void updateSettings()
    {
	for(int i=0; i<nl.size(); i++) {
	    ((NetzListe)nl.get(i)).updateSettings();
	}
    }

    /**
     * Zeichnet alle Komponenten neu und bestimmt deren Größe
     */

    public void refresh()
    {
	for(int i=0; i<nl.size(); i++) {
	    ((NetzListe)nl.get(i)).refresh();
	}
    }

    /**
     * Liefert die Toolbar zurück
     * @return die Toolbar 
     */

    public JPanel getToolBar(){
        Insets insets = new Insets(0,0,0,0);
	JPanel toolPanel = new JPanel();
	toolPanel.setLayout(new FlowLayout());
	((FlowLayout)(toolPanel.getLayout())).setAlignment(FlowLayout.LEFT);
	JButton buttonNewGraph = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/new.png")));
	toolPanel.add(buttonNewGraph);
	buttonNewGraph.addActionListener(this);
	buttonNewGraph.setToolTipText("new graph");
	JButton buttonNewVertex = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/vertex.gif")));
	toolPanel.add(buttonNewVertex);
	buttonNewVertex.addActionListener(new ActionNewVertex(this,vs));
	buttonNewVertex.setToolTipText("new vertex");
	JButton buttonNewEdge = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/edge.gif")));
	toolPanel.add(buttonNewEdge);
	buttonNewEdge.addActionListener(new ActionNewEdge(this,vs));
	buttonNewEdge.setToolTipText("new edge");
	JButton buttonSave = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/save.gif")));
	toolPanel.add(buttonSave);
	buttonSave.addActionListener(new ActionSave(this,vs));
	buttonSave.setToolTipText("save graphs");
	JButton buttonLoad = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/open.png")));
	toolPanel.add(buttonLoad);
	buttonLoad.addActionListener(new ActionLoad(this,vs));
	buttonLoad.setToolTipText("load saved graphs");
	JButton buttonZoomOut = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/zoom_out.gif")));
	toolPanel.add(buttonZoomOut);
	buttonZoomOut.addActionListener(new ActionZoomOut(this,vs));
	buttonZoomOut.setToolTipText("zoom out"); 
	JButton buttonZoomIn = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/zoom_in.gif")));
	toolPanel.add(buttonZoomIn);
	buttonZoomIn.addActionListener(new ActionZoomIn(this,vs));
	buttonZoomIn.setToolTipText("zoom in");
	JButton buttonPrint = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/print.gif")));
	toolPanel.add(buttonPrint);
	buttonPrint.addActionListener(new ActionPrint(this,vs));
	buttonPrint.setToolTipText("print graph");
	JButton buttonLatex = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/latex.gif")));
	toolPanel.add(buttonLatex);
	buttonLatex.addActionListener(new ActionExportToLatex(this,vs));
	buttonLatex.setToolTipText("export to LaTeX");
	JButton buttonEnd = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/noend.gif")));
	toolPanel.add(buttonEnd);
	buttonEnd.addActionListener(new ActionEnd(this,vs));
	buttonEnd.setSelectedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/end.gif")));
	buttonEnd.setToolTipText("endomorphisms"); 
	buttonEnd.setSelected(true);
	JButton buttonQuit = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/quit.gif")));
	toolPanel.add(buttonQuit);
	buttonQuit.addActionListener(new ActionSystemExit(this,vs));
	buttonQuit.setToolTipText("quit"); 
	return toolPanel;
    }

    public JPanel getToolBar2(){
        Insets insets = new Insets(0,0,0,0);
	JPanel toolPanel = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	toolPanel.setLayout(gridbag);
	c.weightx = 1.0;
	c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
	c.gridwidth = GridBagConstraints.REMAINDER;
	JButton button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/card.gif")));
	toolPanel.add(button);
	button.addActionListener(new ActionCard(this,vs));
	button.setToolTipText("calculate cardinalities");
	gridbag.setConstraints(button, c);	
	button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/examine.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionExamine(this,vs));
	button.setToolTipText("examine");
	gridbag.setConstraints(button, c);	
	button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/combine.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionCombine(vs));
	button.setToolTipText("combine");
	gridbag.setConstraints(button, c);	
	button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/factor.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionFactorGraph(this,vs));
	button.setToolTipText("factor graph");
	gridbag.setConstraints(button, c);
	return toolPanel;
    }

    public JPanel getToolBar3(){
        Insets insets = new Insets(0,0,0,0);
	JPanel toolPanel = new JPanel();
	GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	toolPanel.setLayout(gridbag);
	c.weightx = 1.0;
	c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
	c.gridwidth = GridBagConstraints.REMAINDER;
	JButton button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/lessInfo.png")));
	button.setSelectedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/moreInfo.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionMoreInfo(this,vs));
	button.setToolTipText("more info in vertices");
	gridbag.setConstraints(button, c);	
	button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/undirected.png")));
	button.setSelectedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/directed.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionDirected(this,vs));
	button.setToolTipText("directed / undirected");
	gridbag.setConstraints(button, c);
	button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/name.png")));
	button.setSelectedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/nr.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionNamed(this,vs));
	button.setToolTipText("named / numbered");
	gridbag.setConstraints(button, c);	
	button = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/smiley.png")));
	toolPanel.add(button);
	button.addActionListener(new ActionPhysics(this,vs));
	button.setToolTipText("physics");
	gridbag.setConstraints(button, c);	
	return toolPanel;
    }

    public void windowClosed(WindowEvent e) {
 	System.exit(0);   
    }
    
    public void windowActivated(WindowEvent e) { }

    public void windowClosing(WindowEvent e) { 
	System.exit(0);
    }

    public void windowDeactivated(WindowEvent e) { }
    
    public void windowDeiconified(WindowEvent e) { }
    
    public void windowIconified(WindowEvent e) { }
    
    public void windowOpened(WindowEvent e) { }

    public void actionPerformed(ActionEvent event) {
	createNLW();
    }
}

















