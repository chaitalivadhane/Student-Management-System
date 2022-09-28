package com.student.student.service

import com.student.student.model.Student
import com.student.student.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class StudentService (
    @Autowired
    val studentRepository: StudentRepository
)
{


  fun addStudent(student:Student): Mono<Student> {
   return studentRepository.save(student)
}
    fun findAllStudents() : Flux<Student> {
        return studentRepository.findAll()
    }
    fun findById(id:String):Mono<Student>{
        return studentRepository.findById(id)
    }
    fun updateById(id:String, student: Student): Mono<Student> {
        return studentRepository.save(student)
    }

    fun deleteById(id: String): Mono<Void> {
        return studentRepository.deleteById(id)
    }
}