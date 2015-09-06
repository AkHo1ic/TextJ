package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileReload extends TextMenuItem {
    
    public MenuFileReload(TextJ textJ) {
        super("Reload", textJ, -1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        textJ.getFrame().getTextTabs().getSelectedTab().refresh();
    }
}
