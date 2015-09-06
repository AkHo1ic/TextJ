package in.kyle.text.awt.menu.edit.lines;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kyle on 9/6/2015.
 */
public class MenuEditLinesFlip extends TextMenuItem {
    
    public MenuEditLinesFlip(TextJ textJ) {
        super("Flip Lines", textJ, -1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = textJ.getFrame().getTextTabs().getSelectedTab().getTextArea().getJTextArea();
    
        List<String> lines = Arrays.asList(textArea.getText().split("\n"));
        Collections.reverse(lines);
        
        StringBuilder stringBuilder = new StringBuilder();
        lines.forEach(line -> stringBuilder.append(line).append("\n"));
        textArea.setText(stringBuilder.toString());
    }
}
