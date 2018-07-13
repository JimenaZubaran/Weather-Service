import com.avoka.core.groovy.GroovyLogger as logger
import com.avoka.tm.http.GetRequest
import com.avoka.tm.http.HttpResponse
import com.avoka.tm.http.RequestBuilder
import com.avoka.tm.vo.*
import groovy.json.JsonOutput

import javax.servlet.http.*

class Weather {

    private static final String END_POINT = "/data/2.5/weather"
    Map mapJson = [:]
    /*
     * Perform a groovy service invocation
     *
     * return: the result object
     */
    Object invoke(SvcDef svcDef, HttpServletRequest request, User user, Map params) {
        try {


/*
        def ciudad = 'London'
        def apiKey = '79dce9113772bb56536b64746c681f28'
        def paramss = [:]

        ciudad ? paramss.put("q", ciudad) : ""
        apiKey ? paramss.put("appid", apiKey) : ""
*/

            //Obtener valores. Mapear el mapa
            Map parametros = (Map) params ["params"]

            String ciudad = parametros.get("ciudad")
            String apiKey = parametros.get("apiKey")
            def paramss = [:]

            /*logger.info("WeatherGroovy")
            logger.info(ciudad)
            logger.info(apiKey)
            logger.info("............!")
            */

            //Construir mapa con valores obtenidos
            ciudad ? paramss.put("q", ciudad) : ""
            apiKey ? paramss.put("appid", apiKey) : ""

            //Service Defint.- Service connect.
            SvcConn svcConn = svcDef.svcConn

           // GetRequest getWeather = new RequestBuilder().setSvcConn(svcConn).setPath(END_POINT).buildGet()

           // HttpResponse response = getWeather.setParams(paramss).execute()

            //Hacer llamada
            HttpResponse response = new RequestBuilder()
                    .setSvcConn(svcConn)
                    .setPath(END_POINT)
                    .buildGet()
                    .setParams(paramss)
                    .execute()

            //Retornar valor
                String json = response.getTextContent()
                return json

        }catch(Exception e){
            mapJson.put("cod", 500)
            mapJson.put("msg", e.getMessage())
            String sJson = JsonOutput.toJson(mapJson)
            logger.info(sJson)
            return sJson


            /*logger.info(e.message)
            mapJson.put("error", e.getMessage())
            return mapJson*/
        }
    }
}

