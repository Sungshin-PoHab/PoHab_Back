package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateQuestionDto;
import com.example.pohab.Entity.Question;
import com.example.pohab.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    // 지원서 양식(question) 등록
    @PostMapping("")
    public List<Question> createQuestion(@RequestBody() List<CreateQuestionDto> createQuestionDtos) {
        return this.answerService.createQuestion(createQuestionDtos);
    }
}
