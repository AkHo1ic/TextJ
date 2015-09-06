package in.kyle.text.awt;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Highlighter;
import java.awt.*;

/**
 * Created by Kyle on 9/5/2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ScrollTextArea extends JScrollPane implements CaretListener {
    
    private RSyntaxTextArea jTextArea;
    private Highlighter highlighter;
    private TextLineNumber textLineNumber;
    
    public ScrollTextArea() {
        jTextArea = new RSyntaxTextArea();
        setViewportView(jTextArea);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jTextArea.addCaretListener(this);
        textLineNumber = new TextLineNumber(jTextArea);
        setRowHeaderView(textLineNumber);
    }
    
    public ScrollTextArea(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
    }
    
    public void caretUpdate(CaretEvent e) {
        
    }
}
