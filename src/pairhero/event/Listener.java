package pairhero.event;

public interface Listener<E extends Event> {

    boolean canHandle(Event event);

    void handle(E event);

}
