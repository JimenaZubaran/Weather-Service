import com.avoka.tm.svc.*
import com.avoka.tm.util.Path
import com.avoka.tm.vo.*
import com.google.gson.Gson
import groovy.json.*

import javax.servlet.http.*
import com.avoka.core.groovy.GroovyLogger as logger


class DynamicSevice {

    /*
     * Perform Form Dynamic Data service call.
     *
     * returns: REST response text data
     */
    String invoke(SvcDef svcDef, Txn txn, HttpServletRequest request, User user) {
        def results = [:]

        Map paramss = [
                //"svcDef": svcDef,
                "txn": txn,
                "request": request,
                "user": user,
                "params": [
                        "ciudad" : request.getParameter("ciudad"),
                        "apiKey" : "79dce9113772bb56536b64746c681f28"
                ]
        ]
        //logger.info(request.getParameter("ciudad"))

        try{
            def respuesta = invokeWeather(paramss)
            //json string a mapa
            def json =  new JsonSlurper().parseText(respuesta)
            /*logger.info (json["name"])
            logger.info (json["main"]["temp"])
            logger.info (json["sys"]["country"])*/

            //Construir mapa con datos necesarios
           if(json["name"] != null){
               results.put("ciudad", json["name"])
               results.put("temperatura", json["main"]["temp"])
               results.put("pais", json["sys"]["country"])
               logger.info(results)
               return JsonOutput.toJson(results)
           }else{
               results.put("cod",json["cod"])
               results.put("msg",json["message"])
               String resultJson = JsonOutput.toJson(results)
               return resultJson
           }
        }catch(Exception e){
            results.put("cod",500)
            results.put("msg",e.getMessage())
            String sJson = JsonOutput.toJson(results)
            logger.info(sJson)
            return sJson
        }

    }

    String invokeWeather(Map params) {
        return new GroovyServiceInvoker()
                .setServiceName("Weather")
                .setClientCode("citi")
                .invoke(params)
    }

  /*  public class Clima{
        String ciudad
        String temperatura
        String pais
    }*/

}
