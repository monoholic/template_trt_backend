package kr.co.trito.domain.repository.mybatis.receiveAppv;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvListDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvListParamDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvParamDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvUserListDto;

@Mapper
public interface ReceiveAppvRepository {

    int selectCountLog(ReceiveAppvListParamDto receiveAppvListParamDto);

    List<ReceiveAppvListDto> getReceiveAppvList(ReceiveAppvParamDto params);

    List<ReceiveAppvListDto> getTypeList();

    List<ReceiveAppvUserListDto> getUserList();

    int addApproval(ReceiveAppvListDto receiveAppvListDto);
}
