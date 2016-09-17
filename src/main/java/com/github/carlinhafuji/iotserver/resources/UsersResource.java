package com.github.carlinhafuji.iotserver.resources;

import com.github.carlinhafuji.iotserver.domain.*;
import com.github.carlinhafuji.iotserver.resources.model.MobileModel;
import com.github.carlinhafuji.iotserver.resources.model.ThingModel;
import com.github.carlinhafuji.iotserver.resources.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UsersResource {

    private final UserRepository userRepository;

    @Autowired
    public UsersResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> postUsers(
            @RequestBody UserModel userModel,
            UriComponentsBuilder uriBuilder) {
        User user = userRepository().save(new User(userModel.getEmail()));
        URI createdUri = uriBuilder.path("users/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(createdUri).build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        User user = userRepository().findOne(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "{id}/things")
    public ResponseEntity<Void> postThings(
            @PathVariable Long id,
            @RequestBody ThingModel thingModel,
            UriComponentsBuilder uriBuilder) {
        User owner = userRepository().findOne(id);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }

        Thing thing = new Thing(thingModel.getName(), ThingType.valueOf(thingModel.getType().toUpperCase()), owner);
        owner.addThing(thing);
        userRepository().save(owner);

        URI createdUri = uriBuilder.path("users/{userId}/things/{thingId}").buildAndExpand(id, thing.id()).toUri();
        return ResponseEntity.created(createdUri).build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "{id}/mobiles")
    public ResponseEntity<Void> postMobiles(
            @PathVariable Long id,
            @RequestBody MobileModel model,
            UriComponentsBuilder uriBuilder) {
        User owner = userRepository().findOne(id);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }

        Mobile mobile = new Mobile(model.getDeviceId(), owner);
        owner.addMobile(mobile);
        userRepository().save(owner);

        URI createdUri = uriBuilder.path("users/{userId}/mobiles/{thingId}").buildAndExpand(id, mobile.id()).toUri();
        return ResponseEntity.created(createdUri).build();
    }



    private UserRepository userRepository() {
        return userRepository;
    }
}
