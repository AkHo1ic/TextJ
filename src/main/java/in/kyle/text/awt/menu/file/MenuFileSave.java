package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileSave extends TextMenuItem {
    
    public MenuFileSave(TextJ textJ) {
        super("Save", textJ, KeyEvent.VK_S);
    }
    
    public void actionPerformed(ActionEvent e) {
       textJ.getFrame().getTextTabs().getSelectedTab().save();
    }
}
