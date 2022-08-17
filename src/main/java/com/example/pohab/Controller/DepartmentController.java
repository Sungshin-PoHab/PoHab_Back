package com.example.pohab.Controller;

import com.example.pohab.Entity.Department;
import com.example.pohab.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    /** 해당 소속의 설명+개인정보+공통 부서 반환 **/
    @GetMapping("/{party_id}")
    public List<Department> findDepartmentsNameByParty(@PathVariable("party_id") String party_id){
        return this.departmentService.findCommonDepartmentByParty(party_id);
    }

    /** 해당 소속의 공통 부서만 반환 **/
    @GetMapping("/common/{party_id}")
    public Department findCommonDepartmentById(@PathVariable("party_id") String party_id){
        return this.departmentService.findCommonByParty(party_id);
    }
}
