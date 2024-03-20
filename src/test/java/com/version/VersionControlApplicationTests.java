package com.version;

import com.version.test.DispatchTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class VersionControlApplicationTests {
    @Resource
    DispatchTest dispatchTest;

    @Test
    void contextLoads() {
        dispatchTest.print();
    }

}
