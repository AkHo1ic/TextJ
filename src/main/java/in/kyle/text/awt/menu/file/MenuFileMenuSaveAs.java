package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileMenuSaveAs extends TextMenuItem {
    
    public MenuFileMenuSaveAs(TextJ textJ) {
        super("Save As...", textJ, -1);
    }
    
    public void actionPerformed(ActionEvent e) {
        textJ.getFrame().getTextTabs().getSelectedTab().saveAs();
    }
}
