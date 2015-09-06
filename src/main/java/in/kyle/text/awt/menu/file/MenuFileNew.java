package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileNew extends TextMenuItem {
    
    public MenuFileNew(TextJ textJ) {
        super("New", textJ, KeyEvent.VK_N);
    }
    
    public void actionPerformed(ActionEvent e) {
        textJ.getFrame().getTextTabs().createBlankTab();
    }
}
