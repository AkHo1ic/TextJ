package in.kyle.text.awt.menu.edit.lines;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;
import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kyle on 9/6/2015.
 */
public class MenuEditLinesSortWithStartingInteger extends TextMenuItem {
    
    public MenuEditLinesSortWithStartingInteger(TextJ textJ) {
        super("Sort By Starting Integer", textJ, -1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = textJ.getFrame().getTextTabs().getSelectedTab().getTextArea().getJTextArea();
        
        List<String> lines = Arrays.asList(textArea.getText().split("\n"));
        
        lines.sort((o1, o2) -> new CompareToBuilder().append(getNum(o1), getNum(o2)).toComparison());
    
        StringBuilder stringBuilder = new StringBuilder();
        lines.forEach(line->stringBuilder.append(line).append("\n"));
        
        textArea.setText(stringBuilder.toString());
    }
    
    private int getNum(String string) {
        String number = "";
        int i = 0;
        while (i < string.length() && Character.isDigit(string.charAt(i))) {
            number += string.charAt(i++);
        }
        
        if (number.isEmpty()) {
            return -1;
        }
        
        return Integer.parseInt(number);
    }
}
