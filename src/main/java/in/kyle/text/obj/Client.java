package in.kyle.text.obj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import in.kyle.text.TextJ;
import in.kyle.text.awt.popup.TextError;
import in.kyle.text.event.events.ProgramExitEvent;
import in.kyle.text.storage.ProgramSettings;
import lombok.Getter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.*;

/**
 * Created by Kyle on 9/5/2015.
 */
public class Client {
    
    private File settingsSaveFile = new File("program-data.json");
    
    @Getter
    private Clipboard clipboard;
    @Getter
    private ProgramSettings programSettings;
    
    public Client(TextJ textJ) {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        textJ.getEventManager().register(this);
        loadProgramSettings();
    }
    
    public void setClipboardContents(String string) {
        clipboard.setContents(new StringSelection(string), null);
    }
    
    public String getClipboardContents() {
        try {
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            e.printStackTrace();
            new TextError(e);
            return "";
        }
    }
    
    public void loadProgramSettings() {
        if (settingsSaveFile.exists()) {
            try {
                Gson gson = new Gson();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(settingsSaveFile));
                programSettings = gson.fromJson(bufferedReader, ProgramSettings.class);
            } catch (Exception e) {
                e.printStackTrace();
                new TextError(e);
            }
        } else {
            programSettings = new ProgramSettings();
        }
    }
    
    public void onProgramClose(ProgramExitEvent e) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(settingsSaveFile));
            bufferedWriter.write(gson.toJson(programSettings));
            bufferedWriter.close();
            System.out.println("Wrote settings to " + settingsSaveFile.getAbsolutePath());
        } catch (IOException e1) {
            e1.printStackTrace();
            new TextError(e1);
        }
    }
}
