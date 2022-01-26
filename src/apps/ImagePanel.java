package apps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay {

    private BufferedImage bitmap;
    private Image image;
    
    public ImagePanel(){
        try {
            bitmap = ImageIO.read(new File("fotos/monalisa.jpg"));
            bitmap = ImageIO.read(new File("fotos/monalisa2.jpg"));
            bitmap = ImageIO.read(new File("fotos/monalisa3.jpg"));
            
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if (bitmap == null) return;
        Scale scale = new Scale(bitmap.getWidth(), bitmap.getHeight(), getWidth(), getHeight());
        g.drawImage(bitmap, scale.x(), scale.y(), scale.width(), scale.height(), null);
    }
    
    @Override
    public void display(Image image){
        this.image = image;
        loadBitmap();
        repaint();
    }
    
    @Override
    public Image getImage(){
        return image;
    }

    private void loadBitmap() {
        try {
            bitmap  = ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            bitmap = null;
        }
    }
    
    private static class Scale  {
        private final int iw;
        private final int ih;
        private final int pw;
        private final int ph;
           
        private Scale(int iw, int ih, int pw, int ph) {
            this.iw = iw;
            this.ih = ih;
            this.pw = pw;
            this.ph = ph;
        }
        
        int x(){
            return (pw - width()) / 2;
        }
        
        int y(){
            return (ph - height()) / 2;
        }
        
        int width(){
            return adjustWidth() ? pw : (int) (ih * (double) pw / iw);
        }
        
        int height(){
            return adjustWidth() ? (int) (iw * (double) ph / ih) : ph;
        }
       
        private boolean adjustWidth(){
            return iw * ph > pw * ih;
        }
        
        private boolean adjustHeight(){
            return ih * pw > ph * iw;
        }
    }
        
        
    
    
}

