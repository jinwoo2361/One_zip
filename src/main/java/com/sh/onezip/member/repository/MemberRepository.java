package com.sh.onezip.member.repository;

import com.sh.onezip.member.entity.Member;
import com.sh.onezip.neighbor.entity.Neighbor;
import com.sh.onezip.orderproduct.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByName(String name);
    @Query("FROM Member m JOIN FETCH m.authorities WHERE m.memberId = :memberId")
    Member findByMemberId(String memberId);

    @Query("FROM Member m JOIN FETCH m.authorities WHERE m.memberId = :memberId")
    Optional<Member> findByMemberIdOptional(String memberId);

}
