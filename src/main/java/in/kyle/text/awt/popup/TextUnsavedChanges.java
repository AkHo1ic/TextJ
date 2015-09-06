package in.kyle.text.awt.popup;

import in.kyle.text.awt.TextTab;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kyle on 9/5/2015.
 */
@AllArgsConstructor
public class TextUnsavedChanges {
    
    private Component component;
    private TextTab tab;
    
    public Result show() {
        
        int value = JOptionPane.showConfirmDialog(component, "Save file \"" + tab.getTitle() + "\" before quitting?", "Save warning",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        for (Result r : Result.values()) {
            if (value == r.getNum()) {
                return r;
            }
        }
    
        return Result.CANCEL;
    }
    
    @AllArgsConstructor
    public enum Result {
        SAVE(JOptionPane.YES_OPTION),
        NOSAVE(JOptionPane.NO_OPTION),
        CANCEL(JOptionPane.CANCEL_OPTION);
    
        @Getter
        private final int num;
    }
}
