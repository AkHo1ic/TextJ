package in.kyle.text.awt.menu.util;

import in.kyle.text.TextJ;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Kyle on 9/5/2015.
 */
public abstract class TextMenuItem extends JMenuItem implements ActionListener {
    
    @Getter
    protected TextJ textJ;
    
    public TextMenuItem(String title, TextJ textJ, int accelerator) {
        super(title);
        this.textJ = textJ;
        if (accelerator > -1) {
            setAccelerator(KeyStroke.getKeyStroke(accelerator, Event.CTRL_MASK));
        }
        addActionListener(this);
        textJ.getEventManager().register(this);
    }
}
