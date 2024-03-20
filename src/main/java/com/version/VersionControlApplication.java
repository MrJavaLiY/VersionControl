package com.version;

import com.version.control.annotation.VersionScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@VersionScan
@SpringBootApplication
public class VersionControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(VersionControlApplication.class, args);

    }

}
