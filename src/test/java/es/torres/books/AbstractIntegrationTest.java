package es.torres.books;

import java.util.Map;
import java.util.stream.Stream;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
abstract class AbstractIntegrationTest {

   static class Initializer
         implements ApplicationContextInitializer<ConfigurableApplicationContext> {

      static MySQLContainer<?> mysql = new MySQLContainer<>();

      public static Map<String, String> getProperties() {
         Startables.deepStart(Stream.of(mysql)).join();

         return Map.of(
               "spring.datasource.url", mysql.getJdbcUrl(),
               "spring.datasource.username", mysql.getUsername(),
               "spring.datasource.password",mysql.getPassword()
         );
        }

      @Override
      public void initialize(ConfigurableApplicationContext context) {
         var env = context.getEnvironment();
         env.getPropertySources().addFirst(new MapPropertySource(
               "testcontainers",
               (Map) getProperties()
         ));
      }
  }
}
