package in.kyle.text.awt.menu.search;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;
import in.kyle.text.awt.popup.FindPopup;

import java.awt.event.ActionEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuSearchFind extends TextMenuItem {
    
    public MenuSearchFind(TextJ textJ) {
        super("Find...", textJ, -1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        new FindPopup(textJ.getFrame(), textJ);
    }
}
