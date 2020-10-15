package app.validation;

/**
 * @author steve
 */
public class AObject {
    @NotNull
    @NotBlank
    public String id;

    @NotBlank
    @Size(min = 2, max = 50)
    public String name;
    @Min(1)
    public Integer age;

    @Min(1.5)
    public Float wage;

    public AObject(String id, String name, Integer age, Float wage) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.wage = wage;
    }
}
