package com.edumatrix.service;

import com.edumatrix.model.Activity;
import com.edumatrix.model.User;
import com.edumatrix.model.Course;
import com.edumatrix.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityTrackingService {

    @Autowired
    private ActivityRepository activityRepository;

    public void trackActivity(User user, Course course, String activityType, String details) {
        Activity activity = new Activity();
        activity.setUser(user);
        activity.setCourse(course);
        activity.setActivityType(activityType);
        activity.setDetails(details);
        activity.setTimestamp(LocalDateTime.now());
        activityRepository.save(activity);
    }

    public List<Activity> getUserActivities(User user) {
        return activityRepository.findByUser(user);
    }

    public List<Activity> getUserActivitiesInRange(User user, LocalDateTime start, LocalDateTime end) {
        return activityRepository.findByUserAndTimestampBetween(user, start, end);
    }
}
