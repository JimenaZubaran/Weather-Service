import com.avoka.core.groovy.GroovyLogger as logger
import com.avoka.tm.svc.*
import com.avoka.tm.test.*
import com.avoka.tm.util.*
import com.avoka.tm.vo.*
import org.junit.*

class Testtt extends AbstractJUnitTest {

    /*
     * Perform service unit test
     *
     * throws: exception if unit test fails
     */
    @Test
    void testAddressFirstLine() throws Exception {

        Txn txn = new MockVoBuilder().createTxnOpened()
        MockRequest request = new MockRequest()

        Map params = [
            "svcDef": svcDef,
            "txn": txn,
            "request": request,
            "user": null
        ]

        String result = (String) new ServiceInvoker(svcDef).invoke(params)

        logger.info result

        Path path = new Path(result)

        assert "123 Wall Street" == path.val("address.firstLine")
    }

    @After
    void cleanup() {
        MockRegister.clear();
    }
}