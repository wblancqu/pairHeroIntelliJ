package pairhero.application;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import pairhero.event.EventBus;
import pairhero.intellij.listener.TestExecutionListener;
import pairhero.test.event.listener.TestExecutionFinishedListener;

import java.util.logging.Logger;

public class PairHero implements ApplicationComponent {

    private static final Logger LOGGER = Logger.getLogger(PairHero.class.getName());

    private EventBus eventBus;

    @Override
    public void initComponent() {
        LOGGER.info("PairHero Plugin Initialised");

        eventBus().register(new TestExecutionFinishedListener());
    }

    @Override
    public void disposeComponent() {
        LOGGER.info("PairHero Plugin Disposed");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return PairHero.class.getName();
    }

    private EventBus eventBus() {
        if(eventBus == null) {
            eventBus = ServiceManager.getService(EventBus.class);
        }
        return eventBus;
    }
}
