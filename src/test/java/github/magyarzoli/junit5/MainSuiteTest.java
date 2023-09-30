package github.magyarzoli.junit5;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        NumberConvertTest.class,
        NumberConvertParameterizedTest.class,
        NumberConvertExceptionTest.class
})
public class MainSuiteTest {}