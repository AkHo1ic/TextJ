package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;
import in.kyle.text.awt.popup.TextFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileOpen extends TextMenuItem {
    
    public MenuFileOpen(TextJ textJ) {
        super("Open...", textJ, KeyEvent.VK_O);
    }
    
    public void actionPerformed(ActionEvent e) {
        TextFileChooser textFileChooser = new TextFileChooser();
        File file = textFileChooser.open(null);
        if (file != null) {
            textJ.getFrame().getTextTabs().open(file);
        }
    }
}
