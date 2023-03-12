package com.student.assignment.Student;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    
    //--------Postman Test for function

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    //test

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable int studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public void addNewStudent(@PathVariable int studentId, @RequestBody Student updateStudent){
        updateStudent.setId(studentId);
        studentService.updateStudent(updateStudent);
    }

    //---------End Postman Test For Function

    @GetMapping("/showStudent")
    public ModelAndView showStudent(){
        ModelAndView mav = new ModelAndView("list-student");
        List<Student> list = studentService.getStudent();
        mav.addObject("student", list);
        return mav;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView showStudentForm() {
        ModelAndView mav = new ModelAndView("add-student-form");
        mav.addObject("student", new Student());
        return mav;
    }

    @PostMapping("/addStudent")
    public RedirectView addNewStudent(@ModelAttribute("student") Student student) {
        studentService.addNewStudent(student);
        return new RedirectView("/api/student/showStudent");
    }

    @GetMapping("/editStudentForm/{studentId}")
    public ModelAndView showEditStudentForm(@PathVariable("studentId") int studentId) {
        ModelAndView mav = new ModelAndView("edit-student-form");
        Student student = studentService.getStudentById(studentId);
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/editStudent/{studentId}")
    public RedirectView editStudent(@PathVariable("studentId") int studentId, @ModelAttribute("student") Student student) {
        student.setId(studentId); // set the ID of the updated student object
        studentService.updateStudent(student);
        return new RedirectView("/api/student/showStudent");
    }

    @GetMapping("/deleteStudent/{studentId}")
    public RedirectView deleteStudentData(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
        return new RedirectView("/api/student/showStudent");
    }


}
