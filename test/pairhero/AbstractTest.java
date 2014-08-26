package pairhero;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public abstract class AbstractTest {

    @Before
    public void before() {
        initMocks(this);
    }
}
