package in.kyle.text.awt.menu.edit.lines;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Kyle on 9/6/2015.
 */
public class MenuEditLinesRemoveEmpty extends TextMenuItem {
   
    public MenuEditLinesRemoveEmpty(TextJ textJ) {
        super("Remove Empty Lines", textJ, -1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = textJ.getFrame().getTextTabs().getSelectedTab().getTextArea().getJTextArea();
        String text = textArea.getText();
        
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : text.split("\n")) {
            if (!s.isEmpty()) {
                stringBuilder.append(s).append("\n");
            }
        }
        
        textArea.setText(stringBuilder.toString());
    }
}
