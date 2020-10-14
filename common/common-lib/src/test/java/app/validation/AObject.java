package app.validation;

/**
 * @author steve
 */
public class AObject {
    public String name;
    @Min(1)
    public Integer age;

    @Min(1.5)
    public Float wage;

    public AObject(String name, Integer age, Float wage) {
        this.name = name;
        this.age = age;
        this.wage = wage;
    }
}
