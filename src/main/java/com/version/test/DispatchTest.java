package com.version.test;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 见<li>{@link com.version.VersionControlApplicationTests}</li>
 * @author ly
 */
@Service
public class DispatchTest {
    @Resource
    List<Test> tests;

    public void print() {
        tests.forEach(Test::print);
    }
}
