import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    Image image;

    public ImagePanel(Image image){
	super();
	this.image=image;
    }

    public void paint(Graphics g) {
	g.drawImage(this.image,0,0,this);
    }

    public void changeImage(Image image) {
	this.image=image;
	repaint();
    }

}
