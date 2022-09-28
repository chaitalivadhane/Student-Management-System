package com.student.student.repository

import com.student.student.model.Student
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository:ReactiveMongoRepository<Student,String>{

}