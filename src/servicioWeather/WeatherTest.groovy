import com.avoka.core.groovy.GroovyLogger as logger
import com.avoka.tm.svc.*
import com.avoka.tm.test.*
import com.avoka.tm.vo.*
import org.junit.*

class UnitTest extends AbstractJUnitTest {

    /*
     * Perform service unit test
     *
     * throws exception if unit test fails
     */
    @Test
    void test() throws Exception {

        Txn txn = new MockVoBuilder().createTxnOpened()

        Map args = [
            "ciudad": "London",
            "apiKey" : "79dce9113772bb56536b64746c681f28"
        ]

        Map params = [
            "svcDef": svcDef,
            "request": null,
            "user": null,
            "params": args
        ]

        def result = new ServiceInvoker(svcDef).invoke(params)

        // Check result
        logger.info result

        //assert "groovy result - 23" == result
    }

    @After
    void cleanup() {
        MockRegister.clear();
    }
}