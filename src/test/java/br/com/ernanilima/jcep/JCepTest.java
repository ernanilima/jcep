package br.com.ernanilima.jcep;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@SpringBootTest(properties = {"eureka.client.enabled=false"},
        classes = {JCep.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class JCepTest {

}