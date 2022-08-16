package com.example.pohab.Controller;

import com.example.pohab.DTO.CreateQuestionDto;
import com.example.pohab.DTO.UpdateQuestionDto;
import com.example.pohab.Entity.Question;
import com.example.pohab.Repository.QuestionRepository;
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

    // 지원서 양식(question) 부서 별로 가져오기
    @GetMapping("")
    public List<Question> getQuestionByDepartment(@RequestParam("department") Integer department_id) {
        return this.questionService.getQuestionByDepartment(department_id);
    }

    @GetMapping("/{party_id}")
    public List<Question> getQuestionByParty(@PathVariable("party_id") String party_id) {
        return this.questionService.getQuestionByParty(party_id);
    }

    // 지원서 양식(question) 질문/제한길이 수정하기
    @PutMapping("/{question_id}")
    public Question updateQuestion(@PathVariable("question_id") Integer question_id, @RequestBody() UpdateQuestionDto updateQuestionDto) {
        return this.questionService.updateQuestion(question_id, updateQuestionDto);
    }

    // 채점 양식(question) 삭제하기
    @DeleteMapping("/{question_id}")
    public void deleteQuestion(@PathVariable("question_id") Integer question_id) {
        this.questionService.deleteQuestion(question_id);
    }
}