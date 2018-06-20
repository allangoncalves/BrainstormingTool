package bp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Suite de testes para a aplicação
 * @author allan
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	IdeaTest.class,
	SessionTest.class
})
public class JunitTestSuite {

}
