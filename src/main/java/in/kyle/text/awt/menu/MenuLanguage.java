package in.kyle.text.awt.menu;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;
import in.kyle.text.awt.popup.TextError;
import org.apache.commons.lang3.StringUtils;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

/**
 * Created by Kyle on 9/5/2015.
 */
public class MenuLanguage extends JMenu {
    
    public MenuLanguage(TextJ textJ) {
        super("Language");
        try {
            for (Field field : SyntaxConstants.class.getDeclaredFields()) {
                String name = StringUtils.capitalize(field.getName().substring(13));
                add(new MenuLanguageItem(name, (String) field.get(null), textJ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new TextError(e);
        }
    }
    
    public static class MenuLanguageItem extends TextMenuItem {
        
        private String title;
        private String value;
        
        public MenuLanguageItem(String title, String value, TextJ textJ) {
            super(title, textJ, -1);
            this.title = title;
            this.value = value;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            textJ.getFrame().getTextTabs().getSelectedTab().getTextArea().getJTextArea().setSyntaxEditingStyle(value);
        }
    }
}
