package com.example.pohab.Service;

import com.example.pohab.DTO.CreateGradingDto;
import com.example.pohab.Entity.*;
import com.example.pohab.Login.Model.UserDetailsImpl;
import com.example.pohab.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradingService {
    private final GradingStatusRepository gradingStatusRepository;
    private final GradingRepository gradingRepository;
    private final GradingStandardRepository gradingStandardRepository;
    private final StaffRepository staffRepository;
    private final ApplyStatusRepository applyStatusRepository;

    public void createGrading(Integer apply_id, List<CreateGradingDto> createGradingDtos) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer user_id = userDetails.getId();

        Staff staff = this.staffRepository.findByUser_id(user_id);
        ApplyStatus applyStatus = this.applyStatusRepository.findById(apply_id).orElse(null);

        if (staff == null || applyStatus == null) {
            // 추후 에러처리
            System.out.println("error");
        } else {
            GradingStatus gradingStatus = new GradingStatus();
            gradingStatus.setStaff(staff);
            gradingStatus.setApply_status(applyStatus);
            gradingStatus.setScore(0);
            GradingStatus new_gradingStatus = this.gradingStatusRepository.save(gradingStatus); // 채점 현황 생성

            ArrayList<Grading> gradings = new ArrayList<>();
            for (CreateGradingDto gradingDto: createGradingDtos) {
                Grading grading = new Grading();

                GradingStandard gradingStandard = this.gradingStandardRepository.findById(gradingDto.getStandardId()).orElse(null);
                if (gradingStandard == null) {
                    // 추후 에러처리
                    System.out.println("error");
                } else {
                    grading.setGrading_status(new_gradingStatus);
                    grading.setGrading_standard(gradingStandard);
                    grading.setScore(gradingDto.getScore());
                    gradings.add(grading);
                }
            }
            List<Grading> new_gradings = this.gradingRepository.saveAll(gradings); // 점수 저장

            // grading status 용 총합 계산 & 저장
            int total_score = 0;
            for (Grading new_grading: new_gradings) {
                total_score += new_grading.getScore();
            }
            new_gradingStatus.setScore(total_score);
            this.gradingStatusRepository.save(new_gradingStatus);
        }
    }
}
