package in.kyle.text.event.events;

import in.kyle.text.awt.TextTab;
import in.kyle.text.event.TextEvent;

/**
 * Created by Kyle on 9/5/2015.
 */
public class TabCloseEvent implements TextEvent {
    private TextTab textTab;
    
    public TabCloseEvent(TextTab textTab) {
        this.textTab = textTab;
    }
}
