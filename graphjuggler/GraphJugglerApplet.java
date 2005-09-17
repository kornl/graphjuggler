import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphJugglerApplet extends JApplet {

 MyDesktopPane desktop;

 public CharakterGeneratorApplet() {
 }

public void init() {
     try {
     UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
     } catch (Exception e) { System.out.println("Problem mit dem LookAndFeel, könnte häßlich werden ... ;-)");}
     desktop = new JDesktopPane();
     setContentPane(desktop);
     Image splash;
     try {
	splash = getImage(getCodeBase(), "splash.jpg");
     } catch (Exception e) { 
        System.out.println("Huh... Wo ist die Codebase hin?");
     }
}


}
