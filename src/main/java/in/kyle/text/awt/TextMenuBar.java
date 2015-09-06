package in.kyle.text.awt;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.MenuEdit;
import in.kyle.text.awt.menu.MenuFile;
import in.kyle.text.awt.menu.MenuLanguage;
import in.kyle.text.awt.menu.MenuSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;

/**
 * Created by Kyle on 9/5/2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TextMenuBar extends JMenuBar {
    
    public TextMenuBar(TextJ textJ) {
        add(new MenuFile(textJ));
        add(new MenuEdit(textJ));
        add(new MenuLanguage(textJ));
        add(new MenuSearch(textJ));
    }
}
