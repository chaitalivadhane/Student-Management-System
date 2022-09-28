package com.student.student.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Student")
class Student (
    @Id
    var id: String?,
    var name:String?,
    var education:String?,
    var gender:String?,
    var email:String?,
    var mobileNumber: String?,

        )