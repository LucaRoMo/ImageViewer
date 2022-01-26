package control;

import java.util.List;
import model.Image;
import view.ImageDisplay;

public class PrevImageCommand implements Command{
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public PrevImageCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
    }

    
    @Override
    public void execute(){
        imageDisplay.display(prev());
    }
    
    private Image prev(){
        return images.get(prevIndex());
    }
    private int prevIndex(){
        return (currentIndex() + images.size() - 1) % images.size();
    }
    private int currentIndex(){
        return images.indexOf(imageDisplay.getImage());
    }
    
    
}
