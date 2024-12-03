package com.jahaci.edukacija.repository;

import com.jahaci.edukacija.model.Lesson;
import com.jahaci.edukacija.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
