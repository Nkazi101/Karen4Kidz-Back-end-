package com.transport.repository;

import com.transport.entity.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationHistoryRepo extends JpaRepository<LocationHistory, Long> {
   
}
