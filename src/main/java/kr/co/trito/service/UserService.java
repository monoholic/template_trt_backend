package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.users.UsesRepository;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.utils.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private static UsesRepository usesRepository;

    public Object getUserList(UserListParamsDto userListParamsDto) {

        SearchCondition condition = new SearchCondition();

        return usesRepository.getUserList(userListParamsDto);
    }
}