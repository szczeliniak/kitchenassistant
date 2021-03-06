package pl.szczeliniak.kitchenassistant;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class BaseTest implements WithAssertions {

    private static final RequestSpecification SPEC = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri("http://localhost:8080")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

    protected RequestSpecification spec() {
        return given().spec(SPEC);
    }

    @BeforeEach
    public void beforeEach() {
        spec().get("/dev/cleanup")
                .then()
                .statusCode(200);
    }

    protected SuccessResponse addUser(AddUserDto addUserDto) {
        return spec().body(addUserDto)
                .post("/users")
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessResponse.class);
    }

    protected AddUserDto addUserDto() {
        return AddUserDto.builder()
                .name("User")
                .password("Password")
                .email("user@gmail.com")
                .build();
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddUserDto {
        private String email;
        private String password;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class LoginDto {
        private String email;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class RegisterDto {
        private String email;
        private String password;
        private String name;
        private String passwordRepeated;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class LoginResponse {
        private String token;
        private Integer id;
    }

    @Data
    @NoArgsConstructor
    protected static class SuccessResponse {
        private Integer id;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class ReceiptsResponse {
        private List<Receipt> receipts;
        private Pagination pagination;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class Pagination {
        private Integer pageNumber;
        private Integer limit;
        private Integer numberOfPages;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class ReceiptResponse {
        private Receipt receipt;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class Receipt {
        private Integer id;
        private String name;
        private Category category;
        private String description;
        private String author;
        private String source;
        private List<Ingredient> ingredients;
        private List<Step> steps;
        private List<String> photos;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class Ingredient {
        private Integer id;
        private String name;
        private String quantity;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class Step {
        private Integer id;
        private String name;
        private String description;
        private Integer sequence;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddIngredientDto {
        private String name;
        private String quantity;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class UpdateIngredientDto {
        private String name;
        private String quantity;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddStepDto {
        private String name;
        private String description;
        private Integer sequence;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class UpdateStepDto {
        private String name;
        private String description;
        private Integer sequence;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddReceiptDto {
        private Integer userId;
        private String name;
        private String description;
        private String author;
        private Integer categoryId;
        private String source;
        private List<AddIngredientDto> ingredients;
        private List<AddStepDto> steps;
        private List<String> photos;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class UpdateReceiptDto {
        private String name;
        private Integer categoryId;
        private String description;
        private String author;
        private String source;
    }

    @Data
    @NoArgsConstructor
    protected static class UsersResponse {
        private List<User> users;
        private Pagination pagination;
    }

    @Data
    @NoArgsConstructor
    protected static class UserResponse {
        private User user;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class User {
        private Integer id;
        private String email;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddShoppingListDto {
        private Integer userId;
        private String name;
        private String description;
        private LocalDate date;
        private List<AddShoppingListItemDto> items;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class UpdateShoppingListDto {
        private String name;
        private String description;
        private LocalDate date;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddShoppingListItemDto {
        private String name;
        private String quantity;
        private Integer sequence;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class UpdateShoppingListItemDto {
        private String name;
        private String quantity;
        private Integer sequence;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class ShoppingListsResponse {
        private List<ShoppingList> shoppingLists;
        private Pagination pagination;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class ShoppingListResponse {
        private ShoppingList shoppingList;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class ShoppingList {
        private Integer id;
        private String name;
        private String description;
        private LocalDate date;
        private Boolean archived;
        private List<ShoppingListItem> items;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class ShoppingListItem {
        private Integer id;
        private String name;
        private String quantity;
        private Integer sequence;
        private Boolean done;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class AddCategoryDto {
        private Integer userId;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class UpdateCategoryDto {
        private String name;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class Category {
        private Integer id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @SuperBuilder
    protected static class CategoriesResponse {
        private List<Category> categories;
    }

}
