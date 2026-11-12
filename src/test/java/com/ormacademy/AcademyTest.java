package com.ormacademy;

import com.ormacademy.entity.*;
import com.ormacademy.repository.*;
import com.ormacademy.service.AcademyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AcademyTest {
    @Autowired UserRepository userRepo;
    @Autowired CourseRepository courseRepo;
    @Autowired AcademyService service;
    @Autowired EntityManagerFactory emf;
    @Autowired EnrollmentRepository enrollRepo;
    @Autowired AssignmentRepository assignmentRepo;
    @Autowired SubmissionRepository submissionRepo;
    @Autowired QuizRepository quizRepo;
    @Autowired QuestionRepository questionRepo;
    @Autowired AnswerOptionRepository optionRepo;

    @Test void testCreateUser() { User u = new User(); u.setName("A"); u.setEmail("a@mail.ru"); u.setRole(Role.STUDENT); assertNotNull(userRepo.save(u).getId()); }
    @Test void testCreateCourse() { User t = new User(); t.setName("T"); t.setEmail("t@mail.ru"); t.setRole(Role.TEACHER); userRepo.save(t); assertNotNull(service.createCourse(t.getId(), "Курс").getId()); }
    @Test void testEnroll() { User t = new User(); t.setName("T2"); t.setEmail("t2@mail.ru"); t.setRole(Role.TEACHER); userRepo.save(t); User s = new User(); s.setName("S"); s.setEmail("s@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s); Course c = service.createCourse(t.getId(), "Курс2"); service.enroll(c.getId(), s.getId()); assertEquals(1, enrollRepo.findAll().size()); }
    @Test void testEnrollDuplicateThrows() { User t = new User(); t.setName("T3"); t.setEmail("t3@mail.ru"); t.setRole(Role.TEACHER); userRepo.save(t); User s = new User(); s.setName("S3"); s.setEmail("s3@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s); Course c = service.createCourse(t.getId(), "Курс3"); service.enroll(c.getId(), s.getId()); assertThrows(RuntimeException.class, () -> service.enroll(c.getId(), s.getId())); }
    @Test void testSubmitHw() { User s = new User(); s.setName("S4"); s.setEmail("s4@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s); Assignment a = new Assignment(); a.setTitle("HW"); a.setMaxScore(100); assignmentRepo.save(a); assertNotNull(service.submitHw(s.getId(), a.getId(), "Ответ").getId()); }
    @Test void testSubmitDuplicateThrows() { User s = new User(); s.setName("S5"); s.setEmail("s5@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s); Assignment a = new Assignment(); a.setTitle("HW2"); a.setMaxScore(100); assignmentRepo.save(a); service.submitHw(s.getId(), a.getId(), "1"); assertThrows(RuntimeException.class, () -> service.submitHw(s.getId(), a.getId(), "2")); }
    @Test void testGradeHw() { User s = new User(); s.setName("S6"); s.setEmail("s6@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s); Assignment a = new Assignment(); a.setTitle("HW3"); a.setMaxScore(100); assignmentRepo.save(a); Submission sub = service.submitHw(s.getId(), a.getId(), "Ответ"); service.gradeHw(sub.getId(), 95); assertEquals(95, submissionRepo.findById(sub.getId()).get().getScore()); }
    @Test void testGradeExceedsMaxThrows() { User s = new User(); s.setName("S7"); s.setEmail("s7@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s); Assignment a = new Assignment(); a.setTitle("HW4"); a.setMaxScore(10); assignmentRepo.save(a); Submission sub = service.submitHw(s.getId(), a.getId(), "Ответ"); assertThrows(RuntimeException.class, () -> service.gradeHw(sub.getId(), 11)); }

    @Test
    void testPassQuiz() {
        User s = new User(); s.setName("S8"); s.setEmail("s8@mail.ru"); s.setRole(Role.STUDENT); userRepo.save(s);
        Quiz q = new Quiz(); q.setTitle("Q"); quizRepo.save(q);
        Question quest = new Question(); quest.setQuiz(q); questionRepo.save(quest);
        q.getQuestions().add(quest);

        AnswerOption o = new AnswerOption(); o.setCorrect(true); o.setQuestion(quest); optionRepo.save(o);
        quest.getOptions().add(o);

        assertEquals(100, service.passQuiz(s.getId(), q.getId(), List.of(o.getId())).getScore());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testLazyException() {
        Course c = new Course(); c.setTitle("Lazy Course");
        courseRepo.save(c);

        EntityManager em = emf.createEntityManager();
        Course detached = em.find(Course.class, c.getId());
        em.close();
        assertThrows(LazyInitializationException.class, () -> detached.getModules().size());
    }

    @Test void testFindAllCourses() { assertNotNull(courseRepo.findAll()); }
    @Test void testFindAllUsers() { assertNotNull(userRepo.findAll()); }
    @Test void testFindAllEnrollments() { assertNotNull(enrollRepo.findAll()); }
    @Test void testFindAllSubmissions() { assertNotNull(submissionRepo.findAll()); }
    @Test void testDeleteCourse() { Course c = new Course(); c.setTitle("Delete"); courseRepo.save(c); courseRepo.deleteById(c.getId()); assertFalse(courseRepo.findById(c.getId()).isPresent()); }
}