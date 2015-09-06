package in.kyle.text.event.events;

import in.kyle.text.event.TextEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 * Created by Kyle on 9/6/2015.
 */
@AllArgsConstructor
public class OpenFileEvent implements TextEvent {

    @Getter
    private File file;
    
}
