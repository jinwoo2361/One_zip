package com.sh.onezip.authority.repository;

import com.sh.onezip.authority.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
