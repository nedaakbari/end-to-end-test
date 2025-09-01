//package com.example.demo;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void shouldDeleteJobPost(){
//        when()
//                .delete("/api/v1/jobs/{jobId}", 50)
//                .then()
//                .statusCode(204);
//
//        assertThatExceptionOfType(JobNotFoundException.class).isThrownBy(
//                        () -> jobService.findJobById(50L))
//                .withMessage("Job with id: 50 not found!");
//    }
//
//}
