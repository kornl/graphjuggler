import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
/**
 * 
 * \brief MouseMotionListener für die NetzListe
 *
 * @version 29 Dec 2001
 * @author G9-Gui
 * @see NetzListe
 */
public class MouseMotionNetz implements MouseMotionListener {
   
    NetzListe n;

    public MouseMotionNetz(NetzListe p) {
	n = p;
    }

    public void mouseMoved(MouseEvent e) {
	n.mouseMove(e);
    }

    public void mouseDragged(MouseEvent e) {
	//System.out.println("Mouse dragged");
    }

}
