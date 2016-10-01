package com.github.carlinhafuji.iotserver.resources;

import com.github.carlinhafuji.iotserver.domain.Mobile;
import com.github.carlinhafuji.iotserver.domain.User;
import com.github.carlinhafuji.iotserver.domain.UserRepository;
import com.github.carlinhafuji.iotserver.resources.model.MobileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "mobiles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class MobilesResource {

    private final UserRepository userRepository;

    @Autowired
    public MobilesResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> postMobiles(
            @RequestBody MobileModel model,
            UriComponentsBuilder uriBuilder) {

        User owner = userRepository().findByEmail(model.getOwner());
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }

        Mobile mobile = new Mobile(model.getToken(), owner);
        owner.addMobile(mobile);
        userRepository().save(owner);

        URI createdUri = uriBuilder.path("users/{userId}/mobiles/{mobileId}").buildAndExpand(owner.id(), mobile.id()).toUri();
        return ResponseEntity.created(createdUri).build();
    }

    private UserRepository userRepository() {
        return userRepository;
    }
}
