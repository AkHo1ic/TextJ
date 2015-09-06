package in.kyle.text.awt.menu.file;

import in.kyle.text.TextJ;
import in.kyle.text.awt.menu.util.TextMenuItem;
import in.kyle.text.event.events.OpenFileEvent;
import in.kyle.text.storage.ProgramSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

/**
 * Created by Kyle on 9/6/2015.
 */
public class MenuFileOpenRecent extends JMenu {
    
    private ProgramSettings programSettings;
    private TextJ textJ;
    
    public MenuFileOpenRecent(TextJ textJ) {
        super("Open Recent");
        this.textJ = textJ;
        
        programSettings = textJ.getClient().getProgramSettings();
        
        textJ.getEventManager().register(this);
        recalculateRecentFiles();
    }
    
    public void onOpenFile(OpenFileEvent e) {
        String path = e.getFile().getAbsolutePath();
        
        List<String> recentFiles = programSettings.getRecentFiles();
        if (recentFiles.contains(path)) {
            recentFiles.remove(path);
        }
        
        recentFiles.add(0, path);
        
        if (recentFiles.size() > 15) {
            recentFiles.remove(recentFiles.size()-1);
        }
        
        recalculateRecentFiles();
    }
    
    private void recalculateRecentFiles() {
        removeAll();
        for (String s : programSettings.getRecentFiles()) {
            File file = new File(s);
            add(new RecentFile(file, textJ));
        }
    }
    
    private static class RecentFile extends TextMenuItem {
    
        private File file;
        
        public RecentFile(File file, TextJ textJ) {
            super(file.getAbsolutePath(), textJ, -1);
            this.file = file;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            textJ.getFrame().getTextTabs().open(file);
        }
    }
}
