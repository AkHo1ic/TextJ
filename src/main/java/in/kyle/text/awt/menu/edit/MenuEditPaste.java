package in.kyle.text.awt.menu.edit;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuEditPaste extends TextMenuItem {
    
    public MenuEditPaste(TextJ textJ) {
        super("Paste", textJ, KeyEvent.VK_V);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        textJ.getFrame().getTextTabs().getSelectedTab().getTextArea().getJTextArea().paste();
    }
}
