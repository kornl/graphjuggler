import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * 
 * \brief MouseListener für die NetzListe
 *
 * @version 29 Dec 2001
 * @author G9-Gui
 * @see NetzListe
 */

public class MouseNetz implements MouseListener {

    NetzListe n;

    public MouseNetz(NetzListe p) {
	n = p;
    }
    
    public void mousePressed(MouseEvent e) {
	n.mousePress(e);
    }
    
    public void mouseReleased(MouseEvent e) {
	n.mouseRelease(e);
    }
    
    public void mouseEntered(MouseEvent e) {
	//System.out.println("Mouse entered");
    }
    
    public void mouseExited(MouseEvent e) {
	//System.out.println("Mouse exited");
    }
    
    public void mouseClicked(MouseEvent e) {
	//System.out.println("Mouse clicked (# of clicks: "+ e.getClickCount() + ")");
    }

}



