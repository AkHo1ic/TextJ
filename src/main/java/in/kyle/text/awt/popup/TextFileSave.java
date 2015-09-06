package in.kyle.text.awt.popup;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Kyle on 9/5/2015.
 */
public class TextFileSave extends JFileChooser {
    
    private Component component;
    
    public TextFileSave(Component component) {
        this.component = component;
    }
    
    public File open() {
        int returnValue = showSaveDialog(component);
        return getSelectedFile();
    }
}
