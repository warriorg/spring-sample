package dev.warrior.spring.mvc.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

@Getter
@Setter
@ToString
public class NewCoffeeRequest {
    @NotEmpty private String name;

    @NotNull private Money price;
}
