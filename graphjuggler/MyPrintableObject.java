import java.lang.*;
import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
public class MyPrintableObject implements Printable
{
    public int iResMul = 4;  // 1 = 72 dpi; 4 = 288 dpi
    private NetzListe  nl;    
    private static int printed = 0; // Weil Print sich ganz seltsam verhaelt
    public MyPrintableObject (NetzListe nl) 
    {
	this.nl = nl;
    }    
    
    public int print( Graphics g, PageFormat pf, int iPage )
	throws PrinterException	       
    {	
	    final int    FONTSIZE = 12;
	    final double PNT_MM   = 25.4 / 72.;
	    if( 0 != iPage )
		return NO_SUCH_PAGE;
	    try {
		
		double iWdth = pf.getImageableWidth();
		double iHght = pf.getImageableHeight();
		Graphics2D g2 = (Graphics2D)g;
		Dimension dim = nl.getSize();
		    
		g2.translate( pf.getImageableX(), pf.getImageableY() );
		System.out.println(""+iWdth + " "+ dim.getWidth() +" "+ iHght +" "+ dim.getHeight());
		double scale = Math.min(iWdth/dim.getWidth(), iHght/dim.getHeight());
		g2.scale(scale, scale);
		nl.setBackground(Color.white);
		nl.paintComponent(g);	    		
	    } catch( Exception ex ) {
		throw new PrinterException( ex.getMessage() );
	    }
    return PAGE_EXISTS;

    }    

  
private static double dbldgt( double d )
  {
    return Math.round( d * 10. ) / 10.;  // show one digit after point
  }
}













