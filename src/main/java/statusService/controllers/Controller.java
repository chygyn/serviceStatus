package statusService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import statusService.bl.ServiceBD;
import statusService.bl.StatusChangeThread;
import statusService.bl.Util;
import statusService.entity.Entity;
import statusService.util.Texts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/")
public class Controller {
    @Autowired
    public ServiceBD serviceBD;
    //create new entry with status = created and current time
    @RequestMapping(method = RequestMethod.POST, value = "/task")
    public ResponseEntity postServiceConfig() {
        System.out.println(Texts.controllerCreate);
        String dt = Util.getCurDate();
        Entity entity = new Entity("created", dt);
        Entity result = serviceBD.createEntity(entity);
        System.out.println("result int " + result.toString());
        //start thread for status changes
        StatusChangeThread th = new StatusChangeThread(entity, serviceBD);
        return new ResponseEntity<>(entity.getId().toString(), HttpStatus.ACCEPTED);
    }
    //get entry by id
    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}", produces = "application/json")
    public Object getStatusById(@PathVariable(value = "id") Integer id) {
        Entity entity = serviceBD.getEntityByID(id);
        if (entity.getStatus() == null || entity.getDate() == null) {
            throw new ResourceNotFoundException();
        }
        return entity;
    }
    //get all entries from objects table
    @RequestMapping(method = RequestMethod.GET, value = "/task/all", produces = "application/json")
    public Object getStatusById() {
        List<Entity> list = serviceBD.getAllEntity();
        return list;
    }


}

