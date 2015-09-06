package in.kyle.text.awt.menu.edit;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.edit.lines.MenuEditLinesFlip;
import in.kyle.text.awt.menu.edit.lines.MenuEditLinesRemoveEmpty;
import in.kyle.text.awt.menu.edit.lines.MenuEditLinesSortAlphabetically;
import in.kyle.text.awt.menu.edit.lines.MenuEditLinesSortWithStartingInteger;

import javax.swing.*;

/**
 * Created by Kyle on 9/6/2015.
 */
public class MenuEditLines extends JMenu {
    
    public MenuEditLines(TextJ textJ) {
        super("Line Actions");
        add(new MenuEditLinesRemoveEmpty(textJ));
        add(new MenuEditLinesFlip(textJ));
        add(new JPopupMenu.Separator());
        add(new MenuEditLinesSortWithStartingInteger(textJ));
        add(new MenuEditLinesSortAlphabetically(textJ));
    }
}
