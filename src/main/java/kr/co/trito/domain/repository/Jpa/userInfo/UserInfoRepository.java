package kr.co.trito.domain.repository.Jpa.userInfo;

import kr.co.trito.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>, UserInfoCustom {
}
