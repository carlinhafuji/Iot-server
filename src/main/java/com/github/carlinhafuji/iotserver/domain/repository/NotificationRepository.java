package com.github.carlinhafuji.iotserver.domain.repository;

import com.github.carlinhafuji.iotserver.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
