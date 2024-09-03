package kr.co.trito.domain.repository.Jpa;

import kr.co.trito.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);

    //username을 받아 회원을 조회 하는 method
    UserEntity findByUsername(String username);
}