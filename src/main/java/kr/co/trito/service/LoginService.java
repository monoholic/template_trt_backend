package kr.co.trito.service;

import kr.co.trito.domain.UserInfo;
import kr.co.trito.domain.repository.UserInfoRepository;
import kr.co.trito.dto.request.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserInfoRepository userInfoRepository;

    public UserInfo getLogin(LoginDto loginDto) {
        return userInfoRepository.findById(1L).orElse(new UserInfo());
    }
}
