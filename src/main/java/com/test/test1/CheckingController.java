package com.test.test1;

import com.test.test1.aspect.BeforeVerificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checking")
public class CheckingController {

    @GetMapping("/req")
    @BeforeVerificationRequest
    public ResponseEntity checking() {
        return ResponseEntity
                .ok()
                .build();
    }

}
