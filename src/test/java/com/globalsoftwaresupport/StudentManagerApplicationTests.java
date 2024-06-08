package com.globalsoftwaresupport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.globalsoftwaresupport.model.Student;
import com.globalsoftwaresupport.services.StudentService;

@SpringBootTest
@Transactional
class StudentManagerApplicationTests {

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        
        studentService.findAll().forEach(studentService::remove);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testAddAndRemoveStudent() {
        // Create a new student
        Student student = new Student();
        student.setName("Test Student");
        student.setCountry("Test Country");
        student.setAge(20);
        student.setZipCode(1234);
        student.setStatus(null); // Set a default status if needed

        // Save the student
        studentService.save(student);

        // Verify the student is added
        List<Student> students = studentService.findAll();
        assertThat(students).extracting(Student::getName).contains("Test Student");

        // Remove the student
        studentService.remove(student);

        // Verify the student is removed
        students = studentService.findAll();
        assertThat(students).extracting(Student::getName).doesNotContain("Test Student");
    }

    @Test
    void testFindStudentByName() {
        // Create and save a student
        Student student = new Student();
        student.setName("Search Student");
        student.setCountry("Search Country");
        student.setAge(22);
        student.setZipCode(5678);
        student.setStatus(null); // Set a default status if needed
        studentService.save(student);

        // Find the student by name
        List<Student> students = studentService.find("Search");
        assertThat(students).extracting(Student::getName).contains("Search Student");

        // Clean up
        studentService.remove(student);
    }

    @Test
    void testAddMultipleStudents() {
        // Create multiple students
        Student student1 = new Student();
        student1.setName("Student One");
        student1.setCountry("Country One");
        student1.setAge(21);
        student1.setZipCode(1234);
        student1.setStatus(null); // Set a default status if needed

        Student student2 = new Student();
        student2.setName("Student Two");
        student2.setCountry("Country Two");
        student2.setAge(22);
        student2.setZipCode(5678);
        student2.setStatus(null); // Set a default status if needed

        // Save the students
        studentService.save(student1);
        studentService.save(student2);

        // Verify the students are added
        List<Student> students = studentService.findAll();
        assertThat(students).extracting(Student::getName).contains("Student One", "Student Two");

        // Clean up
        studentService.remove(student1);
        studentService.remove(student2);
    }

    @Test
    void testUpdateStudent() {
        // Create a new student
        Student student = new Student();
        student.setName("Update Student");
        student.setCountry("Update Country");
        student.setAge(23);
        student.setZipCode(1234);
        student.setStatus(null); // Set a default status if needed

        // Save the student
        studentService.save(student);

        // Update the student's name
        student.setName("Updated Student");
        studentService.save(student);

        // Verify the student's name is updated
        List<Student> students = studentService.findAll();
        assertThat(students).extracting(Student::getName).contains("Updated Student");

        // Clean up
        studentService.remove(student);
    }

    @Test
    void testEmptyDatabase() {
        // Verify the database is initially empty
        List<Student> students = studentService.findAll();
        assertThat(students).isEmpty();
    }
}
