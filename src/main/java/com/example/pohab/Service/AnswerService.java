package com.example.pohab.Service;

import com.example.pohab.Entity.Answer;
import com.example.pohab.Entity.Question;
import com.example.pohab.Repository.AnswerRepository;
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

    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionService questionService){
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    /** 처음 지원할 때 빈 답변 저장 **/
    public List<Answer> saveEmptyAnswers(Integer apply_status_id, Integer department_id) {

        //먼저 해당 부서의 지원서 양식 가져오기
        List<Question> questions;
        List<Answer> answers = new ArrayList<>();

        questions = this.questionService.getQuestionByDepartment(department_id);

        //지원서 양식의 각 질문에 대한 답변을 null로 저장.
        for(Question question: questions) {
            Answer answer = new Answer();
            answer.setApply_id(apply_status_id);
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
}
