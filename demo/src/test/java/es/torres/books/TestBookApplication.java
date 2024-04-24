package es.torres.books;

public class TestBookApplication {
    public static void main(String[] args) {
        var application = BookApplication.createSpringApplication();

        // Here we add the same initializer as we were using in our tests...
        application.addInitializers(new AbstractIntegrationTest.Initializer());
        
        // ... and start it normally
        application.run(args);
    }   
}
