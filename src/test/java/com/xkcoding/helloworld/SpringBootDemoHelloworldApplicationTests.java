package com.xkcoding.helloworld;

import com.boen.miniostarter.service.impl.MinIOFileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests {

  @Autowired
  private MinIOFileStorageService fileStorageService;

  @Autowired
  private Environment environment;


  @Value("${certId:rsa}")
  private String certId;

  @Test
  public void contextLoads() throws FileNotFoundException {
//      File file = new File("D:\\desk\\pic\\v2-da0e3fd5b10b35eaca8a8add7de5ce0d_r.jpg");
//      InputStream inputStream = new FileInputStream(file);
//      String s = fileStorageService.uploadImgFile("", file.getName(), inputStream);
//      System.out.println(s);
//    String property = environment.getProperty("certId");
    System.out.println(certId);

  }

}
