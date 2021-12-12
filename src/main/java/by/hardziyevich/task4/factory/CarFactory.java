package by.hardziyevich.task4.factory;

import by.hardziyevich.task4.entity.Car;
import by.hardziyevich.task4.reader.Property;

import java.util.ArrayList;
import java.util.Collection;

public class CarFactory {

    private CarFactory() {
    }

    public static Collection<Car> newCars(Property property, int size){
        Collection<Car> cars = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * 3 + 1);
            String key = "car" + random;
            cars.add(new Car(property.getMapping().get(key)));
        }
        return cars;
    }
}
