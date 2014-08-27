package pairhero.event;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private List<Listener> listeners = new ArrayList<Listener>();

    public void post(Event event) {
        for (Listener listener : listeners) {
            if (listener.canHandle(event)) {
                listener.handle(event);
            }
        }
    }

    public void register(Listener listener) {
        listeners.add(listener);
    }
}
