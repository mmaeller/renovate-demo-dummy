package de.maeller.renovatedemodummy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RenovateDemoDummyApplication {

  public static void main(String[] args) {
    SpringApplication.run(RenovateDemoDummyApplication.class, args);
  }

  @RestController
  @RequestMapping("/api")
  static class RenovateDummyController {

    @Operation(
        summary = "Say hello to a friend.",
        operationId = "sayHello",
        method = "GET",
        parameters = {
          @Parameter(
              name = "name",
              description = "Person's name to greet.",
              required = true,
              in = ParameterIn.PATH)
        },
        responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully requested. It's a pleasure to meet you.",
              content = @Content(schema = @Schema(implementation = String.class)))
        })
    @GetMapping("hello/{name}")
    public String hello(@PathVariable("name") final String name) {
      return "Hello " + name;
    }
  }
}
