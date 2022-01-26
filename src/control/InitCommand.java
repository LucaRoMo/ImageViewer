package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Image;
import view.ImageDisplay;

public class InitCommand implements Command {
    private final Map<String, Command> commands = new HashMap<>();
    private final List<Image> images;
    private final ImageDisplay imageDisplay;
    

    public InitCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute(){
        commands.put("N", new NextImageCommand(images, imageDisplay));
        commands.put("P", new PrevImageCommand(images, imageDisplay));
        commands.put("Q", new ExitImageCommand());
    }
    
    public Map<String, Command> getCommands(){
        return commands;
    }
    
}
