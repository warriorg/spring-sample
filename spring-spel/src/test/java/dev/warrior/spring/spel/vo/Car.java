package dev.warrior.spring.spel.vo;

/**
 * @author gao shiyong
 * @since 2022/12/20 09:37
 */
public class Car {
    private String make;
    private String model;
    private Engine engine;
    private int horsePower;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
