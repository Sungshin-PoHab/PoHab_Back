package com.example.pohab.Controller;

import com.example.pohab.Entity.Answer;
import com.example.pohab.Entity.ApplyStatus;
import com.example.pohab.Entity.User;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/answer")
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /** 임시저장 **/
    @PostMapping("/tempSave")
    public List<Answer> tempSave(@RequestBody() List<Answer> answers) {
        return this.answerService.tempSaveAnswer(answers);
    }

    /** 해당 사용자의 해당 부서&스텝에 대한 제출 상태 및 답변 검색 **/
    @GetMapping("/{department_id}/{step_id}")
    public List<Answer> statusAndAnswerSearch(@PathVariable("department_id") Integer department_id, @PathVariable("step_id") Integer step_id) {

        UserDetailsImpl userDetailsIml = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplyStatus applystatus = this.answerService.statusSearch(userDetailsIml.getId(), department_id, step_id);
        return this.answerService.answerSearch(applystatus);
    }

}
