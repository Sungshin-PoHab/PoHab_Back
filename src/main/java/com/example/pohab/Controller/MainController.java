package com.example.pohab.Controller;

import com.example.pohab.Dto.MainDto;
import com.example.pohab.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final DepartmentService departmentService;

    /** 메인페이지 */
    @GetMapping("/main")
    public List<MainDto> main() {
        return departmentService.entityToMainDto();
    }

}