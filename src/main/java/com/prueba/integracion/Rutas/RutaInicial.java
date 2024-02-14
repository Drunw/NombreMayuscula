package com.prueba.integracion.Rutas;

import com.prueba.integracion.Modelos.Request;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RutaInicial extends RouteBuilder {
    @Override
    public void configure() throws Exception{
        from("direct:rutainicial")
                .log("El nombre y apellido ingresado fue: ${body.nombre} ${body.apellido}")
                .setProperty("nombre",simple("${body.nombre}"))
                .setProperty("apellido",simple("${body.apellido}"))
                .process(exchange -> {
                    Request request = new Request();
                    String nombre = (String) exchange.getProperty("nombre");
                    String apellido = (String) exchange.getProperty("apellido");

                    String Nombre = nombre.substring(0,1).toUpperCase() + nombre.substring(1);
                    String Apellido = apellido.substring(0,1).toUpperCase() + apellido.substring(1);

                    request.setNombre(Nombre);
                    request.setApellido(Apellido);

                    exchange.getIn().setBody(request);
                })
                .log("El nombre y apellido convertido fue: ${body.nombre} ${body.apellido}");

    }
}
