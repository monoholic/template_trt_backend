package kr.co.trito.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.trito.domain.repository.mybatis.receiveAppv.ReceiveAppvRepository;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvListDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvListParamDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvParamDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvUserListDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveMailDto;
import kr.co.trito.utils.SearchCondition;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReceiveAppvService {

    @Autowired
    private final ReceiveAppvRepository receiveAppvRepository;
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "hardnokk@gmail.com";

    public Object getReceiveAppvList(ReceiveAppvListParamDto receiveAppvListParamDto) {

        if(receiveAppvListParamDto.getSortBy() != null){
            String str = receiveAppvListParamDto.getSortBy().replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();
            receiveAppvListParamDto.setSortBy(str);
        }

        SearchCondition condition = new SearchCondition(receiveAppvListParamDto.getPage(), receiveAppvListParamDto.getNumOfRows());
        int total = receiveAppvRepository.selectCountLog(receiveAppvListParamDto);
        condition.pageSetup(total);
        
        ReceiveAppvParamDto params = new ReceiveAppvParamDto();
        params.setCondition(condition);
        params.setReceiveAppvListParamDto(receiveAppvListParamDto);
        
        List<ReceiveAppvListDto> resList = receiveAppvRepository.getReceiveAppvList(params);

        Map<String, Object> res = new HashedMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }
    
    public List<ReceiveAppvListDto> getTypeList(ReceiveAppvListDto receiveAppvListDto) {
        return receiveAppvRepository.getTypeList();
    }

    public List<ReceiveAppvUserListDto> getUserList(ReceiveAppvUserListDto receiveAppvUserListDto) {
        return receiveAppvRepository.getUserList();
    }

    public int addApproval(ReceiveAppvListDto receiveAppvListDto) {
        int res = -1;
        res = receiveAppvRepository.addApproval(receiveAppvListDto);
        
        return res;
    }

    public List<ReceiveAppvListDto> getAddress(ReceiveAppvListDto receiveAppvListDto) {
        List<ReceiveAppvListDto> address = receiveAppvRepository.getAddress(receiveAppvListDto);
        return address;
    }

    public void sendMail(ReceiveMailDto receiveMailDto) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiveMailDto.getAddress());
        message.setFrom(ReceiveAppvService.FROM_ADDRESS);
        message.setSubject(receiveMailDto.getTitle());
        message.setText(receiveMailDto.getContent());

        System.out.println("message ===>"+message);

        mailSender.send(message);
    }
}
