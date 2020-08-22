package com.jd.backend.rest.model.counter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
interface CountersRepository extends JpaRepository<Counter, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO counters (id, counter, creation_date, last_update_date) VALUES (:id, 1, :date, :date) ON DUPLICATE KEY UPDATE counter = counter + 1, last_update_date = :date", nativeQuery = true)
    void incrementCounter(String id, Timestamp date);

}
