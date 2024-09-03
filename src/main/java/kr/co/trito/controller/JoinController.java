package kr.co.trito.controller;

import kr.co.trito.dto.Jpa.login.JoinDto;
import kr.co.trito.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody

public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;

    }

    @PostMapping("/join")
    public String JoinProcess(JoinDto joinDto){

        joinService.joinProcess(joinDto);
        return  "ok";
    }

}
