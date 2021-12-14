package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

}
