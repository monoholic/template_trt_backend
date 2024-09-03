package kr.co.trito.service;

import kr.co.trito.domain.repository.Jpa.UserRepository;
import kr.co.trito.dto.Jpa.login.JoinDto;
import kr.co.trito.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();

    }

    public void joinProcess(JoinDto joinDto){

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        boolean isExist = userRepository.existsByUsername(username);

        if(isExist) {

            return ;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
