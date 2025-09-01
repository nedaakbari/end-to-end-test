package org.example.sample4;

import org.springframework.web.bind.annotation.*;

@RestController
public class Sample {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }


    @PostMapping("/put")
    public ShahkarResponse test(@RequestBody String body) {
        System.out.println(body);
        ShahkarResponse shahkarResponse = new ShahkarResponse();
        shahkarResponse.setId("3514564864864");
        shahkarResponse.setResponse(200);
        shahkarResponse.setComment("درخواست با موفقیت ");
        shahkarResponse.setRequestId("51456416546854685");
        return shahkarResponse;
    }
}
