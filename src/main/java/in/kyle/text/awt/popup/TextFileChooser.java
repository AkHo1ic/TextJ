package in.kyle.text.awt.popup;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Created by Kyle on 9/5/2015.
 */
public class TextFileChooser extends JFileChooser {
    
    public TextFileChooser() {
        setFileFilter(new TextFileFilter());
        setDialogTitle("Open a text file");
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setVisible(true);
    }
    
    public File open(Component parent) {
        int returnValue = showDialog(parent, "Open");
        return getSelectedFile();
    }
    
    private class TextFileFilter extends FileFilter {
    
        @Override
        public boolean accept(File f) {
            return !f.isDirectory();
        }
    
        @Override
        public String getDescription() {
            return "Any file that is not a directory";
        }
    }
}
