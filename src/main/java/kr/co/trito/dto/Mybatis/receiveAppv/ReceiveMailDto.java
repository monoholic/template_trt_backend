package kr.co.trito.dto.Mybatis.receiveAppv;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReceiveMailDto {
    private String address;
    private String title;
    private String content;
}
