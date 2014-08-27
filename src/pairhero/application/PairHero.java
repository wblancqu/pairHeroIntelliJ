package pairhero.application;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.impl.ComponentManagerImpl;
import org.jetbrains.annotations.NotNull;
import pairhero.event.EventBus;
import pairhero.test.event.listener.TestBrokenListener;
import pairhero.test.event.listener.TestExecutionFinishedListener;
import pairhero.test.event.listener.TestResolvedListener;

import java.util.logging.Logger;

import static com.intellij.openapi.components.ServiceManager.getService;

public class PairHero implements ApplicationComponent {

    private static final Logger LOGGER = Logger.getLogger(PairHero.class.getName());

    private EventBus eventBus;
    private ComponentManagerImpl componentManager;

    PairHero(EventBus eventBus, ComponentManagerImpl componentManager) {
        this.eventBus = eventBus;
        this.componentManager = componentManager;
    }

    @Override
    public void initComponent() {
        LOGGER.info("PairHero Plugin Initialised");
        eventBus.register(getService(TestExecutionFinishedListener.class));
        eventBus.register(getService(TestBrokenListener.class));
        eventBus.register(getService(TestResolvedListener.class));
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
}
