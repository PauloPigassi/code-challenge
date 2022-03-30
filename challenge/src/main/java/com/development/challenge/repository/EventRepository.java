package com.development.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.development.challenge.model.AccessModel;
import com.development.challenge.model.EventModel;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Integer>{

	@Query(value = "select count (distinct user_token) from event where url = :url", nativeQuery = true)
	List<Integer> getAllEventsByUrl(@Param("url") String url);

}
