package in.kyle.text.awt.menu;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.search.MenuSearchFind;

import javax.swing.*;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuSearch extends JMenu {
    
    public MenuSearch(TextJ textJ) {
        super("Search");
        add(new MenuSearchFind(textJ));
    }
}
