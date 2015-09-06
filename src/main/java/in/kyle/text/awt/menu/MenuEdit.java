package in.kyle.text.awt.menu;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.edit.MenuEditCopy;
import in.kyle.text.awt.menu.edit.MenuEditLines;
import in.kyle.text.awt.menu.edit.MenuEditPaste;

import javax.swing.*;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuEdit extends JMenu {
    
    public MenuEdit(TextJ textJ) {
        super("Edit");
        add(new MenuEditCopy(textJ));
        add(new MenuEditPaste(textJ));
        add(new MenuEditLines(textJ));
    }
}
