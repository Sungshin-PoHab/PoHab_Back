package com.example.pohab.Service;

import com.example.pohab.DTO.CreateQuestionDto;
import com.example.pohab.DTO.UpdateQuestionDto;
import com.example.pohab.Entity.Department;
import com.example.pohab.Entity.Question;
import com.example.pohab.Repository.DepartmentRepository;
import com.example.pohab.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private DepartmentRepository departmentRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(DepartmentRepository departmentRepository, QuestionRepository questionRepository) {
        this.departmentRepository = departmentRepository;
        this.questionRepository = questionRepository;
    }

    // 채점 양식(question) 등록
    public List<Question> createQuestion(List<CreateQuestionDto> createQuestionDtos) {
        ArrayList<Question> questions = new ArrayList<>();

        for (CreateQuestionDto questionDto: createQuestionDtos) {
            Question question = new Question();
            Department department = this.departmentRepository.findById(questionDto.getDepartmentId()).orElse(null);
            if (department == null) {
                // 추후 에러처리 필요
                System.out.println("error");
                return null;
            } else {
                question.setDepartment(department);
                question.setQuestion(questionDto.getQuestion());
                question.setMaxLength(questionDto.getMaxLength());
                questions.add(question);
            }
        }

        return this.questionRepository.saveAll(questions);
    }

    // 채점 양식(Question) 부서(Department) 별로 가져오기
    public List<Question> getQuestionByDepartment(Integer department_id) {
        return this.questionRepository.findAllByDepartment_id(department_id);
    }

    // 채점 양식(Question) 질문 Or 제한 길이 수정하기
    public Question updateQuestion(Integer question_id, UpdateQuestionDto updateQuestionDto) {
        Question update_question = this.questionRepository.findById(question_id).orElse(null);

        if (update_question == null) {
            // 추후 에러처리 필요
            System.out.println("error");
            return null;
        } else {
            update_question.setQuestion(updateQuestionDto.getQuestion());
            update_question.setMaxLength(updateQuestionDto.getMaxLength());
            return this.questionRepository.save(update_question);
        }
    }
}
