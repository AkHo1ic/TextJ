package in.kyle.text.storage;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 9/5/2015.
 */
@Data
public class ProgramSettings {
    
    private String lastDirectory = new File("").getAbsolutePath();
    
    private List<String> recentFiles = new ArrayList<>();
    
    
}
