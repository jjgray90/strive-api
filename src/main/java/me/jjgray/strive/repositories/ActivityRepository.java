package me.jjgray.strive.repositories;

import me.jjgray.strive.entities.Activity;
import me.jjgray.strive.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
List<Activity> findByUser(User user);
}
