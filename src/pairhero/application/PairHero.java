package pairhero.application;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class PairHero implements ApplicationComponent {

    private static final Logger LOGGER = Logger.getLogger(PairHero.class.getName());

    @Override
    public void initComponent() {
        LOGGER.info("PairHero Plugin Initialised");
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
