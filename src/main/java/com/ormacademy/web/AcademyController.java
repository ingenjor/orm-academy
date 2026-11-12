package com.ormacademy.web;

import com.ormacademy.dto.CourseRequest;
import com.ormacademy.entity.*;
import com.ormacademy.service.AcademyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AcademyController {

    private final AcademyService academyService;


    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return academyService.getAllCourses();
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestParam Long teacherId, @Valid @RequestBody CourseRequest request) {
        return academyService.createCourse(teacherId, request.getTitle());
    }

    @PostMapping("/courses/{courseId}/enroll/{studentId}")
    public String enroll(@PathVariable Long courseId, @PathVariable Long studentId) {
        academyService.enroll(courseId, studentId);
        return "Успешно записан!";
    }

    @PostMapping("/homework/submit")
    public Submission submitHw(@RequestBody Map<String, String> body) {
        Long studentId = Long.valueOf(body.get("studentId"));
        Long hwId = Long.valueOf(body.get("assignmentId"));
        return academyService.submitHw(studentId, hwId, body.get("content"));
    }

    @PostMapping("/homework/{subId}/grade")
    public String grade(@PathVariable Long subId, @RequestParam Integer score) {
        academyService.gradeHw(subId, score);
        return "Оценка " + score + " выставлена!";
    }

    @PostMapping("/quizzes/{quizId}/pass/{studentId}")
    public QuizSubmission passQuiz(@PathVariable Long quizId, @PathVariable Long studentId, @RequestBody List<Long> optionIds) {
        return academyService.passQuiz(studentId, quizId, optionIds);
    }
}