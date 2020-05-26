package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.SafeLocation;

public interface SafeLocationRepository extends JpaRepository<SafeLocation, Integer> {

}
