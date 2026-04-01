package com.edumatrix.repository;

import com.edumatrix.model.Activity;
import com.edumatrix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUser(User user);
    List<Activity> findByUserAndTimestampBetween(User user, LocalDateTime start, LocalDateTime end);
    List<Activity> findByActivityType(String activityType);
}
