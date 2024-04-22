package es.torres.books.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.torres.books.model.CommentDTO;
import es.torres.books.model.UserDTO;
import es.torres.books.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import java.util.Collection;
import java.util.Set;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get a list of users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns the list of users"),
        @ApiResponse(responseCode = "500", description = "If a runtime error occurs while processing the request", content = @Content)})
    @GetMapping("/")
    public ResponseEntity<Collection<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the user"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content)})
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userService.findById(username));
    }

    @Operation(summary = "Delete a user by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "If the user has been deleted successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content)})
    @DeleteMapping("/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        userService.deleteById(username);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Creates a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "If the user has been created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
        @ApiResponse(responseCode = "400", description = "If one or more request parameters don't comply with the specification", content = @Content),
        @ApiResponse(responseCode = "500", description = "If a runtime error occurs while processing the request", content = @Content)})
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        userService.save(user);
        URI location = fromCurrentRequest().path("/{username}").buildAndExpand(user.nickname()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @Operation(summary = "Updates a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "If the user has been created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
        @ApiResponse(responseCode = "400", description = "If one or more request parameters don't comply with the specification", content = @Content),
        @ApiResponse(responseCode = "500", description = "If a runtime error occurs while processing the request", content = @Content)})
    @PutMapping("/{username}")
    public ResponseEntity updateUser(@Valid @RequestBody UserDTO user, @PathVariable String username) {
        userService.update(user, username);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all the comments from a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the comments"),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)})
    @GetMapping("/{userId}/comments")
    public ResponseEntity<Set<CommentDTO>> getCommentsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(userService.findCommentByUserId(userId));
    }
}
