package in.kyle.text.event.events;

import in.kyle.text.awt.TextTab;
import in.kyle.text.event.TextEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Kyle on 9/5/2015.
 */
@Data
@AllArgsConstructor
public class TabChangeEvent implements TextEvent {
    
    private TextTab tab;
    
}
