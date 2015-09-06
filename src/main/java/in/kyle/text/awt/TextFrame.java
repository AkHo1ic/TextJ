package in.kyle.text.awt;

import in.kyle.text.TextJ;
import in.kyle.text.awt.popup.TextUnsavedChanges;
import in.kyle.text.event.events.ProgramExitEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TextFrame extends JFrame {
    
    private TextTabList textTabs;
    private TextMenuBar textMenuBar;
    private TextJ textJ;
    
    public TextFrame(TextJ textJ) throws Exception {
        this.textJ = textJ;
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    
    public void init() {
        textMenuBar = new TextMenuBar(textJ);
        setJMenuBar(textMenuBar);
    
        addWindowListener(new LocalFrameListener(this));
    
        textTabs = new TextTabList(textJ);
        textTabs.init();
        add(textTabs);
    
        setTitle("TextJ");
        setSize(900, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    
    @AllArgsConstructor
    class LocalFrameListener extends WindowAdapter {
        
        private TextFrame frame;
        
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closing");
            for (TextTab textTab : textTabs.getTabs()) {
                if (textTab.isChanged()) {
                    TextUnsavedChanges.Result result = new TextUnsavedChanges(frame, textTab).show();
                    if (result == TextUnsavedChanges.Result.CANCEL) {
                        return;
                    } else if (result == TextUnsavedChanges.Result.SAVE) {
                        textTab.save();
                    }
                }
            }
            frame.getTextJ().getEventManager().fire(new ProgramExitEvent());
            System.exit(0);
        }
    }
}
