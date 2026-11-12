package com.ormacademy.service;

import com.ormacademy.entity.*;
import com.ormacademy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademyService {
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollRepo;
    private final AssignmentRepository assignmentRepo;
    private final SubmissionRepository submissionRepo;
    private final QuizRepository quizRepo;
    private final QuizSubmissionRepository quizSubRepo;


    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course createCourse(Long teacherId, String title) {
        User t = userRepo.findById(teacherId).orElseThrow(() -> new RuntimeException("Нет учителя"));
        Course c = new Course();
        c.setTitle(title);
        c.setTeacher(t);
        c.setStartDate(LocalDate.now());
        return courseRepo.save(c);
    }

    public void enroll(Long courseId, Long studentId) {
        Course c = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Нет курса"));
        User s = userRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Нет студента"));


        for (Enrollment e : enrollRepo.findAll()) {
            if (e.getCourse().getId().equals(courseId) && e.getStudent().getId().equals(studentId)) {
                throw new RuntimeException("Студент уже записан!");
            }
        }

        Enrollment en = new Enrollment();
        en.setCourse(c);
        en.setStudent(s);
        en.setStatus("ACTIVE");
        en.setEnrollDate(LocalDate.now());
        enrollRepo.save(en);
        System.out.println("Студент " + s.getName() + " записан на " + c.getTitle());
    }

    public Submission submitHw(Long studentId, Long assignmentId, String text) {
        User s = userRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Нет студента"));
        Assignment a = assignmentRepo.findById(assignmentId).orElseThrow(() -> new RuntimeException("Нет задания"));

        for (Submission sub : submissionRepo.findAll()) {
            if (sub.getStudent().getId().equals(studentId) && sub.getAssignment().getId().equals(assignmentId)) {
                throw new RuntimeException("Вы уже сдавали эту работу!");
            }
        }

        Submission newSub = new Submission();
        newSub.setStudent(s);
        newSub.setAssignment(a);
        newSub.setContent(text);
        newSub.setSubmittedAt(Instant.now());
        return submissionRepo.save(newSub);
    }

    public void gradeHw(Long subId, Integer score) {
        Submission sub = submissionRepo.findById(subId).orElseThrow(() -> new RuntimeException("Нет работы"));
        if (score > sub.getAssignment().getMaxScore()) {
            throw new RuntimeException("Оценка больше максимальной!");
        }
        sub.setScore(score);
        submissionRepo.save(sub);
    }

    public QuizSubmission passQuiz(Long studentId, Long quizId, List<Long> chosenIds) {
        User s = userRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Нет студента"));
        Quiz q = quizRepo.findById(quizId).orElseThrow(() -> new RuntimeException("Нет теста"));

        int correct = 0;
        for (Question question : q.getQuestions()) {
            for (AnswerOption opt : question.getOptions()) {
                if (chosenIds.contains(opt.getId()) && opt.isCorrect()) correct++;
            }
        }

        int score = q.getQuestions().isEmpty() ? 0 : (correct * 100) / q.getQuestions().size();

        QuizSubmission qSub = new QuizSubmission();
        qSub.setStudent(s);
        qSub.setQuiz(q);
        qSub.setScore(score);
        qSub.setTakenAt(Instant.now());
        return quizSubRepo.save(qSub);
    }
}