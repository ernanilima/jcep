package br.com.ernanilima.jcep.resource;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeResource {

    /**
     * Redireciona o acesso para a pagina do swagger
     * @param response HttpServletResponse
     */
    @RequestMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/index.html");
    }
}
