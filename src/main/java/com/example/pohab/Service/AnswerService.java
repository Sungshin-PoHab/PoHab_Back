package com.example.pohab.Service;

import com.example.pohab.Entity.*;
import com.example.pohab.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private AnswerRepository answerRepository;
    private QuestionService questionService;
    private ApplyStatusRepository applyStatusRepository;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private StepRepository stepRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionService questionService, ApplyStatusRepository applyStatusRepository, UserRepository userRepository, DepartmentRepository departmentRepository, StepRepository stepRepository){
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.applyStatusRepository = applyStatusRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.stepRepository = stepRepository;
    }

    /** 처음 지원할 때 빈 답변 저장 **/
    public List<Answer> saveEmptyAnswers(ApplyStatus applyStatus, Integer department_id) {

        //먼저 해당 부서의 지원서 양식 가져오기
        List<Question> questions;
        List<Answer> answers = new ArrayList<>();

        questions = this.questionService.getQuestionByDepartment(department_id);

        //지원서 양식의 각 질문에 대한 답변을 null로 저장.
        for(Question question: questions) {
            Answer answer = new Answer();
            answer.setApplyStatus(applyStatus);
            answer.setQuestion(question);
            answer.setAnswer(null);
            this.saveAnswer(answer);
            answers.add(answer);
        }
        return answers;
    }

    public Answer saveAnswer(Answer answer){
        return this.answerRepository.save(answer);
    }

    /** 답변 임시저장 **/
    public List<Answer> tempSaveAnswer(List<Answer> answers) {
        List<Answer> newAnswers = new ArrayList<>();
        for(Answer answer: answers) {
            Optional<Answer> optionalAnswer = this.answerRepository.findById(answer.getId());

            if (optionalAnswer.isPresent()) {
                Answer pastAnswer = optionalAnswer.get();
                pastAnswer.setAnswer(answer.getAnswer());
                this.answerRepository.save(pastAnswer);
                newAnswers.add(pastAnswer);
            } else {
                return null;
            }
        }
        return newAnswers;
    }

    /** 제출 상태 검색 **/
    public ApplyStatus statusSearch(Integer user_id, Integer department_id, Integer step_id) {
        User user = this.userRepository.findById(user_id).orElse(null);
        Department department = this.departmentRepository.findById(department_id).orElse(null);
        Step step = this.stepRepository.findById(step_id).orElse(null);

        try{
            return this.applyStatusRepository.getApplyStatusByUserDepartmentStep(user, department, step);
        } catch (Exception e){
            return null;
        }

    }

    /** 지원 현황에 대한 답변 검색 **/
    public List<Answer> answerSearch(ApplyStatus applyStatus) {
        List<Answer> answer = answerRepository.getAllAnswersByApplyStatusId(applyStatus);
        return answer;
    }
}
