package in.kyle.text.awt.menu;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.file.*;

import javax.swing.*;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuFile extends JMenu {
    
    public MenuFile(TextJ textJ) {
        super("File");
        add(new MenuFileNew(textJ));
        add(new MenuFileOpen(textJ));
        add(new MenuFileSave(textJ));
        add(new MenuFileMenuSaveAs(textJ));
        add(new MenuFileReload(textJ));
        add(new JPopupMenu.Separator());
        add(new MenuFileOpenRecent(textJ));
        add(new MenuFileClose(textJ));
        add(new MenuFileCloseAll(textJ));
    }
}
