package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class ImagePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
    private ImageObserver imageObserver;
     
    public ImagePanel(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        image = icon.getImage();
        imageObserver = icon.getImageObserver();
    }
 
    public void paint( Graphics g ) {
        super.paint( g ) ;
        g.drawImage(image,  0 , 0 , getWidth() , getHeight() , imageObserver);
    }
}

