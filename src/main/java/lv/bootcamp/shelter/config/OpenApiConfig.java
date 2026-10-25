package lv.bootcamp.shelter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * General information shown at the top of Swagger UI.
 *
 * @author Karyna Litvinova
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI shelterOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shelter Animals API")
                        .version("v1")
                        .description("REST endpoints for listing, creating and adopting shelter animals. "
                                + "Data is kept in memory and resets when the application restarts."));
    }
}