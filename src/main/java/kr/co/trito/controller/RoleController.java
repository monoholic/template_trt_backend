package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.role.RoleAddModDto;
import kr.co.trito.dto.Mybatis.role.RoleListParamsDto;
import kr.co.trito.dto.Mybatis.role.UserRoleAddModDto;
import kr.co.trito.dto.Mybatis.role.UserRoleListParamsDto;
import kr.co.trito.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roleMng")
public class RoleController {

    @Autowired
    private final RoleService roleService;

// ----------- 역할 관리 --------------
    
    // 역할 목록 조회
    @PostMapping("/getRoleList")
    public ResponseEntity<TritoResponse<?>> getRoleList(
            @Valid @RequestBody RoleListParamsDto roleListParamsDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(roleService.getRoleList(roleListParamsDto)));
    }

    // 역할 추가 수정
    @PostMapping("/roleAddMod")
    public ResponseEntity<TritoResponse<?>> addModRole(
            @Valid @RequestBody RoleAddModDto roleAddModDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(roleService.addModRole(roleAddModDto)));
    }

    // 역할 삭제
    @PostMapping("/roleDel")
    public ResponseEntity<TritoResponse<?>> delRole(
            @Valid @RequestBody List<String> params
    ){
        return ResponseEntity.ok(new TritoResponse<>(roleService.delRole(params)));
    }

// ----------- 사용자 역할 관리 --------------

    // 사용자 역할 목록 조회
    @PostMapping("/getUserRoleList")
    public ResponseEntity<TritoResponse<?>> getUserRoleList(
            @Valid @RequestBody UserRoleListParamsDto userRoleListParamsDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(roleService.getUserRoleList(userRoleListParamsDto)));
    }

    // 사용자 역할 추가, 수정
    @PostMapping("/addModUserRole")
    public ResponseEntity<TritoResponse<?>> addModUserRole(
            @Valid @RequestBody UserRoleAddModDto userRoleAddModDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(roleService.addModUserRole(userRoleAddModDto)));
    }

    // 사용자 역할 삭제
    @PostMapping("/delUserRole")
    public ResponseEntity<TritoResponse<?>> delUserRole(
            @Valid @RequestBody Object params
    ){
        return ResponseEntity.ok(new TritoResponse<>(roleService.delUserRole(params)));
    }
}
