package com.student.assignment.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/record")
public class AdvanceStudentController {
    
    @Autowired
    private final StudentService studentService;

    public AdvanceStudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(){
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }

    //search function section

    @GetMapping("/search")
    public String showStudentSearch(Model model) {
        model.addAttribute("keyword", "");
        return "student-search";
    }
    
    @PostMapping("/search")
    public String searchStudents(@RequestParam("keyword") String keyword, Model model) {
        List<Student> studentList = studentService.searchByKeyword(keyword);
        model.addAttribute("studentList", studentList);
        model.addAttribute("keyword", keyword);
        return "student-search";
    }

    //end search function section

    @GetMapping("/adShowStudent")
    public ModelAndView adShowStudent(){
        ModelAndView mav = new ModelAndView("ad-list-student");
        List<Student> list = studentService.getStudent();
        mav.addObject("student", list);
        return mav;
    }

    @GetMapping("/adShowStudentGrid")
    public ModelAndView adShowStudentGrid(){
        ModelAndView mav = new ModelAndView("ad-grid-student");
        List<Student> list = studentService.getStudent();
        mav.addObject("student", list);
        return mav;
    }

    @GetMapping("/adAddStudentForm")
    public ModelAndView adAddStudentForm() {
        ModelAndView mav = new ModelAndView("ad-add-student-form");
        mav.addObject("student", new Student());
        return mav;
    }

    @PostMapping("/adAddStudent")
    public RedirectView adAddStudent(@ModelAttribute("student") Student student) {
        studentService.addNewStudent(student);
        return new RedirectView("/record/adShowStudent");
    }

    @GetMapping("/adEditStudentForm/{studentId}")
    public ModelAndView adEditStudentForm(@PathVariable("studentId") int studentId) {
        ModelAndView mav = new ModelAndView("ad-edit-student-form");
        Student student = studentService.getStudentById(studentId);
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/adEditStudent/{studentId}")
    public RedirectView adEditStudent(@PathVariable("studentId") int studentId, @ModelAttribute("student") Student student) {
        student.setId(studentId); // set the ID of the updated student object
        studentService.updateStudent(student);
        return new RedirectView("/record/adShowStudent");
    }

    @GetMapping("/adDeleteStudent/{studentId}")
    public RedirectView adDeleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
        return new RedirectView("/record/adShowStudent");
    }

    @GetMapping("/adDeleteStudentGrid/{studentId}")
    public RedirectView adDeleteStudentGrid(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
        return new RedirectView("/record/adShowStudentGrid");
    }
}
