package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileCloseAll extends TextMenuItem {
    
    public MenuFileCloseAll(TextJ textJ) {
        super("Close All", textJ, -1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        textJ.getFrame().getTextTabs().closeAllTabs();
    }
}
