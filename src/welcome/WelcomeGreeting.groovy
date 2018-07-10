import com.avoka.core.groovy.GroovyLogger as logger
import com.avoka.tm.vo.*
import javax.servlet.http.*

class WelcomeGreeting {

    /*
     * Perform Form Dynamic Data service call.
     *
     * returns: REST response text data
     */
    String invoke(SvcDef svcDef, Txn txn, HttpServletRequest request, User user) {

        // TODO: replace with data lookup call
        
        String data = '''{
          "address": {
            "firstLine": "123 Wall Street"
          }
        }'''

        return data
    }
}