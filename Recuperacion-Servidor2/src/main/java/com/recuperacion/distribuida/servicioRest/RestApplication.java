package com.recuperacion.distribuida.servicioRest;



import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.config.inject.ConfigProperty;



import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;


@ApplicationScoped
@ApplicationPath(value = "/")
public class RestApplication extends Application {

	@Inject
	@ConfigProperty(name="server.port")
	private Integer puerto;
	
	private String id=UUID.randomUUID().toString();
	
	@Inject
	@ConfigProperty(name="consult.host", defaultValue = "127.0.0.1")
	private String consulHost;
	
	@Inject
	@ConfigProperty(name="consult.port", defaultValue = "8500")
	private Integer consulPort;
	
	
	
	@Override
	public Set<Class<?>> getClasses() {	
        return Set.of(
                UsersRest.class
        );
    }
	
	/////////////////////////IMPORTANTE///////////////////////////////////////
	//Necesitamos declarar una lista para indicarle el tag que necesitamos para usar gateway fabio
	List<String> tags = Arrays.asList("urlprefix-/users");
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		System.out.println("Inicializando");
		
/////////////////////////IMPORTANTE///////////////////////////////////////	
		
		//se puede dejar por default o especificar el puerto
		ConsulClient client = new ConsulClient(consulHost,consulPort);
		
		NewService s = new NewService();
		
		s.setName("Table-Users");
		s.setId(id);
		s.setAddress("127.0.0.1");
		s.setPort(puerto);
		
		//Con esta linea se agrega el tag necesario para integrar nuestra instancia al gateway fabio
		s.setTags(tags);
		
	   NewService.Check check = new NewService.Check();
	   
	   check.setMethod("GET");
	   check.setHttp("http://127.0.0.1:" + puerto +"/users" +"/ping");
	  check.setInterval("10s");
	   check.setDeregisterCriticalServiceAfter("20s");
	   
	   s.setCheck(check);
	   
		client.agentServiceRegister(s);
		
		}
	
	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
		ConsulClient client = new ConsulClient();
		System.out.println("Deteniendo");
		client.agentServiceDeregister( id );
		}
	
	
}
