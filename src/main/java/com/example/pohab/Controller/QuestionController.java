package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateQuestionDto;
import com.example.pohab.Entity.Question;
import com.example.pohab.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    // 지원서 양식(question) 등록
    @PostMapping("")
    public List<Question> createQuestion(@RequestBody() List<CreateQuestionDto> createQuestionDtos) {
        return this.questionService.createQuestion(createQuestionDtos);
    }

    // 지원서 양식(question)
    @GetMapping("")
    public List<Question> getQuestionByDepartment(@RequestParam("department") Integer department_id) {
        return this.questionService.getQuestionByDepartment(department_id);
    }
}