package com.ormacademy.config;

import com.ormacademy.entity.*;
import com.ormacademy.entity.Module; // Явно указываем наш модуль, чтобы не путать с java.lang.Module
import com.ormacademy.entity.Role;   // Явно указываем нашу роль, чтобы не путать со Spring
import com.ormacademy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DataInit {
    private final UserRepository users;
    private final CourseRepository courses;
    private final CategoryRepository cats;
    private final ModuleRepository modules;
    private final LessonRepository lessons;
    private final AssignmentRepository assignments;
    private final QuizRepository quizzes;
    private final QuestionRepository questions;
    private final AnswerOptionRepository options;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (users.count() > 0) return;
            System.out.println("Заполняем базу...");

            User t = new User();
            t.setName("Препод");
            t.setEmail("teacher@mail.ru");
            t.setRole(Role.TEACHER);
            users.save(t);

            User s = new User();
            s.setName("Студент");
            s.setEmail("student@mail.ru");
            s.setRole(Role.STUDENT);
            users.save(s);

            Category cat = new Category();
            cat.setName("Программирование");
            cats.save(cat);

            Course c = new Course();
            c.setTitle("Java-разработчик");
            c.setTeacher(t);
            c.setCategory(cat);
            c.setStartDate(LocalDate.now());
            courses.save(c);

            Module m = new Module();
            m.setTitle("Модуль 1");
            m.setCourse(c);
            modules.save(m);

            Lesson l = new Lesson();
            l.setTitle("Урок 1");
            l.setModule(m);
            lessons.save(l);

            Assignment a = new Assignment();
            a.setTitle("Домашка 1");
            a.setMaxScore(10);
            a.setDueDate(LocalDate.now().plusWeeks(1));
            a.setLesson(l);
            assignments.save(a);

            Quiz q = new Quiz();
            q.setTitle("Итоговый тест");
            q.setCourse(c);
            quizzes.save(q);

            Question quest = new Question();
            quest.setText("2+2?");
            quest.setQuiz(q);
            questions.save(quest);

            AnswerOption opt1 = new AnswerOption();
            opt1.setText("4");
            opt1.setCorrect(true);
            opt1.setQuestion(quest);
            options.save(opt1);

            AnswerOption opt2 = new AnswerOption();
            opt2.setText("5");
            opt2.setCorrect(false);
            opt2.setQuestion(quest);
            options.save(opt2);

            System.out.println("База заполнена!");
        };
    }
}