package com.prueba.integracion.Rutas;

import com.prueba.integracion.Modelos.Request;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


@Component
public class ConsumerRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception{
        restConfiguration()
                .component("servlet") // Aqui se especifica que se usara el componente servlet, el cual expande las capacidades de un servidor web en java, permitiendo solicitudes http, y respuestas dinamicas, ademas de interacciones con bases de datos
                .bindingMode(RestBindingMode.auto); // Esto ayuda a que camel infiera directamente que tipo de peticion le llega (json, xml, etc) y lo "enlace" automaticamente.

        rest()
                .path("/cambioNombre") // Aqui se puede configurar la url que con la cual se llamara la aplicacion.
                .consumes("application/json") // El tipo de la peticion.
                .produces("application/json") // El tipo de la respuesta
                .post("").type(Request.class).enableCORS(true).description("Flujo que transforma la primera letra del nombre y apellido en mayuscula") // Aqui se setean el metodo HTTP, la clase del objeto que se recibe y una pequeña descripcion.
                .to("direct:rutainicial"); // Finalmente se refiere a la siguiente parte de la aplicacion, dirijete al archivo rutaInicial.
    }
}
