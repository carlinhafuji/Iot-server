package com.github.carlinhafuji.iotserver.domain;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing, Long> {
}
