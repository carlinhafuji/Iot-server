package com.github.carlinhafuji.iotserver.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.carlinhafuji.iotserver.domain.Thing;
import com.github.carlinhafuji.iotserver.domain.ThingRepository;
import com.github.carlinhafuji.iotserver.domain.notification.NotificationSender;

@Service
public class EventProcessor {

    private final ThingRepository thingRepository;
    private final NotificationSender notificationSender;

    @Autowired
    public EventProcessor(ThingRepository thingRepository, NotificationSender notificationSender) {
        this.thingRepository = thingRepository;
        this.notificationSender = notificationSender;
    }

    public void processor(EventData eventData) {
        System.out.println(eventData.getThingId() + " - " + String.join(", " , eventData.getParams().keySet()));

        Thing thing = thingRepository().findOne(eventData.getThingId());
        List<Integer> paramValues = new ArrayList<Integer>();

        System.out.print(thing);
        
        for (Map.Entry<String, String> value: eventData.getParams().entrySet()) {
        	paramValues.add(new Integer(value.getValue()));
        }
        
        Map<String, String> msgs = thingMessage(thing, paramValues);
        
        for (Map.Entry<String, String> msg: msgs.entrySet()) {
        	if(msg.getValue() != null){
        		System.out.println("Titulo: "+ msg.getKey() + "Mensagem: " + msg.getValue());
        		//Notification n = new Notification(msg.getKey(),  msg.getValue(), thing.getOwner())
        	}
        }        
        
        //Notification n = new Notification("titulo", "corpo", thing.getOwner())
        //notificationSender().send(n);
    }
    
    private Map<String,String> thingMessage(Thing thing, List<Integer> paramValues){
    	Map<String,String> msg = new HashMap<String,String>();
    	String title = "";
    	String body = "";
    	
    	switch (thing.type()) {
		case PLANTA:
			title = "Menssagem da Planta";
			body = processTree(thing, paramValues);
			break;
		case BALANCA:
			
			break;
		case CARTEIRA_CHAVE_CARTEIRA:
			title = "Menssagem da Carteira/Celular/Chave";
			body = processRFID(paramValues);
			break;
		}
    	
    	msg.put(title, body);
    	
    	return msg;
    }
    
    private String processTree(Thing thing, List<Integer> paramValues){
    	Integer paramValue = paramValues.get(0);
    	
    	if(paramValue < 50 && paramValue > 30){
    		return "Regar a planta "+thing.name();
    	}else if(paramValue < 30){
    		return "Regar a planta "+thing.name() + "urgente";
    	}
    	
    	return null;    	
    }
    
    private String processRFID(List<Integer> paramValues){
    	if(paramValues.get(0) == 1) return "Você está esquecendo a chave";
    	if(paramValues.get(0) == 2) return "Você está esquecendo a carteira";
    	if(paramValues.get(0) == 3) return "Você está esquecendo a carteira e chave";
    	if(paramValues.get(0) == 4) return "Você está esquecendo o celular e a chave";
    	if(paramValues.get(0) == 5) return "Você está esquecendo o celular";
    	if(paramValues.get(0) == 6) return "Você está esquecendo o celular e a carteira";
    	
    	return null;   	
    }

    private ThingRepository thingRepository() {
        return thingRepository;
    }
    private NotificationSender notificationSender() {
        return notificationSender;
    }
}
