package apps;

import control.Command;
import control.NextImageCommand;
import control.PrevImageCommand;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.CENTER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class Main extends JFrame{
    public static void main(String[] args){
        new Main().execute();
        
    }
    private ImageDisplay imageDisplay;
    private Map<String, Command> commands = new HashMap<>();
    
    public Main(){
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocation(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.getContentPane().add(imagePanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        List<Image> images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        this.commands.put("prev", new PrevImageCommand(images, imageDisplay));
        this.commands.put("next", new NextImageCommand(images, imageDisplay));

    }
    
    private void execute(){
        this.setVisible(true);
    }
    
    private JPanel imagePanel(){
        ImagePanel panel = new ImagePanel();
        this.imageDisplay = panel;
        return panel;
    }

    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(CENTER));
        toolbar.add(button("prev"));
        toolbar.add(button("next"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        return button;
    }
}
