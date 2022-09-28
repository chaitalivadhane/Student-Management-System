package com.student.student.controller

import com.student.student.model.Student
import com.student.student.repository.StudentRepository
import com.student.student.service.StudentService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
@WebFluxTest(StudentController::class)
@AutoConfigureWebTestClient

class StudentControllerTest {

    // need to mock the service layer responses

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var studentService:StudentService

    @Autowired
    lateinit var studentRepository: StudentRepository

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun studentService() = mockk<StudentService>()

        @Bean
        fun studentRepository() = mockk<StudentRepository>()
    }



    @Test

    fun `should return list  `() {
        //Arranging data members
         val student1 = Student(
                "1","Chaitali","BE","Female","chv@gmail.com","9325059460"
        )

        //expectations
        val expectedResult = mapOf(
            "id" to "1",
            "name" to "Chaitali",
            "education" to "BE",
            "gender" to "Female",
            "email" to "chv@gmail.com",
            "mobileNumber" to "9325059460"

        )

        //Action invoking the methods
        every {
            studentService.findAllStudents()
        } returns Flux.just(student1)

        //Assertions
        val response = client.get()
            .uri("/students/list")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            studentService.findAllStudents()
        }
    }

    @Test

    fun `should update `() {

        val exepectedResponse = mapOf(
            "id" to "1",
            "name" to "Chaitali",
            "education" to "BE",
            "gender" to "Female",
            "email" to "chv@gmail.com",
            "mobileNumber" to "9325059460"
        )
        val student1 = Student(
            "1","Chaitali","BE","Female","chv@gmail.com","9325059460"
        )
        every {
            studentService.updateById("1",student1)
        } returns Mono.just(student1)

        val response = client.put()
            .uri("/update/1")
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful

        verify(exactly = 1) {
            studentService.updateById("1",student1)
        }
    }

    @Test

    fun `should delete `() {
        val student1 = Student(
            "1","Chaitali","BE","Female","chv@gmail.com","9325059460"
        )
        val exepectedResponse = mapOf(
            "id" to "1",
            "name" to "Chaitali",
            "education" to "BE",
            "gender" to "Female",
            "email" to "chv@gmail.com",
            "mobileNumber" to "9325059460"
        )
        every {
            studentService.deleteById("1")
        } returns Mono.empty()

        val response = client.delete()
            .uri("/student/1")
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful

        verify(exactly = 1) {
            studentService.deleteById("1")
        }
    }

//    @Test
//
//    fun `should able to update student`(){
//        val exepectedResponse = mapOf(
//
//            "id" to "1",
//            "name" to "Chaitali",
//            "education" to "BE",
//            "gender" to "Female",
//            "email" to "chv@gmail.com",
//            "mobileNumber" to "9325059460")
//
//        val student1 = Student(
//            "1","Chaitali","BE","Female","chv@gmail.com","9325059460"
//
//        )
//        every {
//            studentService.updateById("1",student1)
//        }returns Mono.just(student1)
//
//
//        val response= client.put()
//            .uri("/update/1")
//            .bodyValue(student1)
//            .exchange()// invoking the end point
//            .expectStatus().is2xxSuccessful
//
//        verify(exactly = 1){
//            studentService.updateById("1",student1)
//        }
//    }
}