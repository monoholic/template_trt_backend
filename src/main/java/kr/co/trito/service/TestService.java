package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.TestMapper;
import kr.co.trito.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public List<TestDTO> getUser(){
        return testMapper.selectUser();
    }
}
