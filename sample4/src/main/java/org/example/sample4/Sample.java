package org.example.sample4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }
}
