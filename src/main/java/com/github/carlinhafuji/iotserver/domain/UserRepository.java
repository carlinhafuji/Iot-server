package com.github.carlinhafuji.iotserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by carlosmendes on 9/17/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
