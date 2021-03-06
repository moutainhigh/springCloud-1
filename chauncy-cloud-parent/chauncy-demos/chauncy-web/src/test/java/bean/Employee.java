package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author cheng
 * @create 2020-08-24 22:54
 */
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class Employee {

    private String name;
    private int age;
    private double salary;

    public Employee(String name){
        this.name = name;
    }
}
