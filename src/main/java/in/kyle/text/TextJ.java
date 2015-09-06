package in.kyle.text;

import in.kyle.text.awt.TextFrame;
import in.kyle.text.awt.popup.TextError;
import in.kyle.text.event.EventManager;
import in.kyle.text.obj.Client;
import lombok.Getter;

/**
 * Created by Kyle on 9/5/2015.
 */
public class TextJ {

    @Getter
    private TextFrame frame;
    @Getter
    private EventManager eventManager;
    @Getter
    private Client client;
    
    public static void main(String[] args) {
        new TextJ();
    }
    
    public TextJ() {
        eventManager = new EventManager();
        client = new Client(this);
        try {
            frame = new TextFrame(this);
            frame.init();
        } catch (Exception e) {
            new TextError(e);
            e.printStackTrace();
        }
    }
}
