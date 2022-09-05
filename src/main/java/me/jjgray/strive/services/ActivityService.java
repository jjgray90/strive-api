package me.jjgray.strive.services;

import me.jjgray.strive.entities.Activity;
import me.jjgray.strive.entities.User;
import me.jjgray.strive.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> findActivitiesByUser(User user) {
        return activityRepository.findByUser(user);
    }

}
