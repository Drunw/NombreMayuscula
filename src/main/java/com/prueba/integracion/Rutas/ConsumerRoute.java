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
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        rest()
                .path("/cambioNombre")
                .consumes("application/json")
                .produces("application/json")
                .post("").type(Request.class).enableCORS(true).description("Flujo que transforma la primera letra del nombre y apellido en mayuscula")
                .to("direct:rutainicial");
    }
}
