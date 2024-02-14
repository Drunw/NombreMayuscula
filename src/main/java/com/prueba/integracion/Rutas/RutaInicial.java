package com.prueba.integracion.Rutas;

import com.prueba.integracion.Modelos.Request;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RutaInicial extends RouteBuilder {
    @Override
    public void configure() throws Exception{
        from("direct:rutainicial")
                .log("El nombre y apellido ingresado fue: ${body.nombre} ${body.apellido}") // Se imprime informacion en consola
                .setProperty("nombre",simple("${body.nombre}")) // Se setea una variable, o una propiedad, con el contenido del nombre.
                .setProperty("apellido",simple("${body.apellido}")) // Se setea una variable, o una propiedad, con el contenido del apellido.
                .process(exchange -> {
                    Request request = new Request();
                    String nombre = (String) exchange.getProperty("nombre"); // Se llaman las variables ya mencionadas dentro del proceso.
                    String apellido = (String) exchange.getProperty("apellido");

                    String Nombre = nombre.substring(0,1).toUpperCase() + nombre.substring(1);
                    String Apellido = apellido.substring(0,1).toUpperCase() + apellido.substring(1);   // Un proceso en java que se encarga de poner la primera letra en mayuscula

                    request.setNombre(Nombre);
                    request.setApellido(Apellido);

                    exchange.getIn().setBody(request); // Aqui se indica que el nuevo body o respuesta sera el objeto armado.
                })
                .log("El nombre y apellido convertido fue: ${body.nombre} ${body.apellido}"); // Se imprime informacion en consola

    }
}
