package github.magyarzoli.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NumberConvertTest.class,
        NumberConvertParameterizedTest.class,
        NumberConvertExceptionTest.class
})
public class MainSuiteTest {}