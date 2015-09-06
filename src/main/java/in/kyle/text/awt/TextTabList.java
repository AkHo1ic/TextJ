package in.kyle.text.awt;

import in.kyle.text.TextJ;
import in.kyle.text.event.events.OpenFileEvent;
import in.kyle.text.event.events.TabChangeEvent;
import in.kyle.text.event.events.TabCloseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kyle on 9/5/2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TextTabList extends JTabbedPane implements ChangeListener {
    
    private List<TextTab> tabs;
    private TextJ textJ;
    
    public TextTabList(TextJ textJ) {
        this.textJ = textJ;
        tabs = new ArrayList<>();
    }
    
    public void init() {
        // default tab
        createBlankTab();
        setVisible(true);
        textJ.getEventManager().register(this);
    }
    
    public TextTab getSelectedTab() {
        return (TextTab) getSelectedComponent();
    }
    
    public void createBlankTab() {
        TextTab textTab = new TextTab(textJ, "New " + (tabs.size()+1));
        addTab(textTab);
    }
    
    public void addTab(TextTab textTab) {
        tabs.add(textTab);
        add(textTab);
        setTitleAt(textTab.getIndex(), textTab.getTitle());
        setSelectedIndex(textTab.getIndex());
    }
    
    public void open(File file) {
        TextTab textTab = new TextTab(textJ, file);
        textTab.refresh();
        addTab(textTab);
        textJ.getEventManager().fire(new OpenFileEvent(file));
    }
    
    public void closeAllTabs() {
        Iterator<TextTab> tabIterator = tabs.iterator();
        while (tabIterator.hasNext()) {
            TextTab textTab = tabIterator.next();
            tabIterator.remove();
            textTab.close();
        }
    }
    
    public void stateChanged(ChangeEvent e) {
        TextFrame frame = textJ.getFrame();
        TextTabList textTabs = frame.getTextTabs();
        TextTab selectedTab = textTabs.getSelectedTab();
        textJ.getEventManager().fire(new TabChangeEvent(selectedTab));
    }
    
    public void onTabClose(TabCloseEvent tabCloseEvent) {
        if (tabs.size() == 0) {
            createBlankTab();
        }
    }
}
