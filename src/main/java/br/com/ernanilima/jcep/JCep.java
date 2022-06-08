package br.com.ernanilima.jcep;

import br.com.ernanilima.jcep.utils.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JCep  implements CommandLineRunner {

    @Autowired
    private DataBase dataBase;

    public static void main(String[] args) {
        SpringApplication.run(JCep.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataBase.createDataDatabase();
    }
}
