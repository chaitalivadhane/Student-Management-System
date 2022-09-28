package com.student.student.controller

import com.student.student.model.Student
import com.student.student.repository.StudentRepository
import com.student.student.service.StudentService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class StudentController (
    val studentService: StudentService,
    val studentRepository: StudentRepository
        ){



    @PostMapping("/students/add")
    fun save(@RequestBody student:Student): Mono<Student>{
        return studentService.addStudent(student)
    }
    @GetMapping("/students/list")
    fun getAllStudents(student:Student):Flux<Student>
    {
        return studentService.findAllStudents()
    }
    @GetMapping("/students/{id}")
    fun getStudentById(@PathVariable id:String):Mono<Student>{
        return studentService.findById(id)
    }
    @PutMapping("/update/{id}")
    fun updateById(@PathVariable id: String, @RequestBody student: Student): Mono<Student> {
        return studentService.updateById(id,student)
    }
    @DeleteMapping("/remove")
    fun delete(): Mono<Void> {
        return studentRepository.deleteAll()
    }
    @DeleteMapping("/student/{id}")
    fun deleteStudents(@PathVariable id: String): Mono<Void> {
        return studentService.deleteById(id)
    }
}