package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.groupbuying.vo.Test;

@Repository
public interface TestRepository extends JpaRepository<Test,Long>  {

}
