package com.student.assignment.Student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public List<Student> searchByKeyword(String keyword) {
        return studentRepository.searchByKeyword(keyword);
    }

    public Student getStudentById(int studentId) {
    Optional<Student> studentOptional = studentRepository.findById(studentId);
    if (studentOptional.isEmpty()) {
        throw new IllegalStateException("Student with id " + studentId + " does not exist");
    }
    return studentOptional.get();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email is already taken");
        }
        if (student.getCgpa() > 4.00) {
            throw new IllegalArgumentException("Incorrect value");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(int studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("student with id " + studentId + " did not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Student updatedStudent) {
        Optional<Student> studentOptional = studentRepository.findById(updatedStudent.getId());
        if (studentOptional.isEmpty()) {
            throw new IllegalStateException("Student with id " + updatedStudent.getId() + " does not exist");
        }
        // update the fields of the existing student object with the values from the updatedStudent object
        Student existingStudent = studentOptional.get();
        
        if (updatedStudent.getName() != null && updatedStudent.getName().length() > 0 && !Objects.equals(existingStudent.getName(), updatedStudent.getName())){
            existingStudent.setName(updatedStudent.getName());
        }
        if (updatedStudent.getGender() != existingStudent.getGender()){
        existingStudent.setGender(updatedStudent.getGender());
        }
        if (updatedStudent.getDepartment() != null && updatedStudent.getDepartment().length() > 0 && !Objects.equals(existingStudent.getDepartment(), updatedStudent.getDepartment())){
        existingStudent.setDepartment(updatedStudent.getDepartment());
        }
        if (updatedStudent.getEmail() != null && updatedStudent.getEmail().length() > 0 && !Objects.equals(existingStudent.getEmail(), updatedStudent.getEmail())){
            Optional<Student> studentWithEmail = studentRepository.findStudentByEmail(updatedStudent.getEmail());
            if (studentWithEmail.isPresent() && !Objects.equals(studentWithEmail.get().getId(), existingStudent.getId())) {
                throw new IllegalStateException("Email is already taken");
            }
            existingStudent.setEmail(updatedStudent.getEmail());
        }
        if (Objects.nonNull(updatedStudent.getCgpa()) && !Float.valueOf(existingStudent.getCgpa()).equals(Float.valueOf(updatedStudent.getCgpa()))){
        existingStudent.setCgpa(updatedStudent.getCgpa());
        }
        if (updatedStudent.getDate_of_birth() != null && !Objects.equals(existingStudent.getDate_of_birth(), updatedStudent.getDate_of_birth())){
        existingStudent.setDate_of_birth(updatedStudent.getDate_of_birth());
        }
        studentRepository.save(existingStudent);
    }


}
