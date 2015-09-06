package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.TextTab;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFileClose extends TextMenuItem {
    
    public MenuFileClose(TextJ textJ) {
        super("Close", textJ, KeyEvent.VK_W);
    }
    
    public void actionPerformed(ActionEvent e) {
        TextTab selectedTab = textJ.getFrame().getTextTabs().getSelectedTab();
        selectedTab.close();
    }
}
