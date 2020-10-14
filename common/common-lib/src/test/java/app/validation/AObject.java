package app.validation;

/**
 * @author steve
 */
public class AObject {
    @Max(1)
    public String name;
    public Integer age;

    public AObject(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
