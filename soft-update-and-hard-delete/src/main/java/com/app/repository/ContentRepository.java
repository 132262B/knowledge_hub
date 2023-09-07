package com.app.repository;

import com.app.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContentRepository extends JpaRepository<Content, Long>, ContentRepositoryCustom {

    @Modifying
    @Query("update Content c SET c.status = 'N' WHERE c.status = 'Y' AND c.id IN :contentIds ")
    void deleteAllContent(List<Long> contentIds);
}
