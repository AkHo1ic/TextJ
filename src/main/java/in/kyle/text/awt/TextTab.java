package in.kyle.text.awt;

import in.kyle.text.TextJ;
import in.kyle.text.awt.popup.TextError;
import in.kyle.text.awt.popup.TextFileSave;
import in.kyle.text.awt.popup.TextUnsavedChanges;
import in.kyle.text.event.events.TabCloseEvent;
import in.kyle.text.event.events.TextEditEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Kyle on 9/5/2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TextTab extends JPanel implements DocumentListener {
    
    private File file;
    private String title;
    private ScrollTextArea textArea;
    private boolean changed;
    private Document document;
    private TextJ textJ;
    
    private TextTab(TextJ textJ) {
        this.textJ = textJ;
        textArea = new ScrollTextArea();
        setLayout(new GridLayout(1, 1));
        add(textArea);
        document = textArea.getJTextArea().getDocument();
        document.addDocumentListener(this);
        setVisible(true);
    }
    
    public TextTab(TextJ textJ, File file) {
        this(textJ);
        this.file = file;
        title = file.getAbsolutePath();
    }
    
    public TextTab(TextJ textJ, String title) {
        this(textJ);
        this.title = title;
    }
    
    public void refresh() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = IOUtils.toByteArray(bufferedInputStream);
            String text = new String(bytes, Charset.forName("UTF-8"));
            textArea.getJTextArea().setText(text);
            changed = false;
        } catch (Exception e) {
            new TextError(e);
        }
    }
    
    public void close() {
        if (isChanged()) {
            switch (new TextUnsavedChanges(this, this).show()) {
                case SAVE:
                    save();
                case NOSAVE:
                    break;
                case CANCEL:
                    return;
            }
        }
        TextTabList textTabList = textJ.getFrame().getTextTabs();
        textTabList.removeTabAt(getIndex());
        textTabList.getTabs().remove(this);
        textJ.getEventManager().fire(new TabCloseEvent(this));
    }
    
    public void saveAs() {
        TextFileSave save = new TextFileSave(this);
        File file = save.open();
        if (file != null) {
            setFile(file);
            save();
        }
    }
    
    public int nextIndexOf(String string) {
        return textArea.getJTextArea().getText().indexOf(string, textArea.getJTextArea().getCaretPosition());
    }
    
    public void select(int from, int to) {
        textArea.getJTextArea().select(from, to);
    }
    
    public void save() {
        if (file == null) {
            saveAs();
            return;
        }
        try {
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            JTextArea area = textArea.getJTextArea();
            byte[] bytes = textArea.getJTextArea().getText().getBytes(Charset.forName("UTF-8"));
            os.write(bytes);
            os.close();
            changed = false;
        } catch (Exception e) {
            new TextError(e);
            e.printStackTrace();
        }
    }
    
    public int getIndex() {
        Component[] components = textJ.getFrame().getTextTabs().getComponents();
        
        for (int i = 0; i < components.length; i++) {
            System.out.println(components[i] + "==" + this + "====" + components[i].equals(this));
            if (components[i].equals(this)) {
                return i;
            }
        }
        return -1;
    }
    
    
    
    public void insertUpdate(DocumentEvent e) {
        textJ.getEventManager().fire(new TextEditEvent());
        changed = true;
    }
    
    public void removeUpdate(DocumentEvent e) {
        textJ.getEventManager().fire(new TextEditEvent());
        changed = true;
    }
    
    public void changedUpdate(DocumentEvent e) {
        textJ.getEventManager().fire(new TextEditEvent());
        changed = true;
    }
}
