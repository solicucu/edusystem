package com.hnu.edusystem.repository;

import com.hnu.edusystem.domain.SC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SCRepository extends JpaRepository<SC, Long> {
    Page<SC> findBySidOrderByCid(String sid, Pageable pageable);
    Page<SC> findByCnameLikeOrderBySid(String cname, Pageable pageable);
    SC findBySidAndCid(String sid, String cid);
    SC findByDayAndSessionAndSid(String day, Integer session, String sid);
    SC findByCnameAndSname(String cname, String sname);
}
