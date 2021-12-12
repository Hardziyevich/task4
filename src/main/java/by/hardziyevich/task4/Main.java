package by.hardziyevich.task4;

import by.hardziyevich.task4.entity.Car;
import by.hardziyevich.task4.factory.CarFactory;
import by.hardziyevich.task4.reader.PropertiesUtil;
import by.hardziyevich.task4.reader.Property;

import java.util.Collection;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Property allProperty = PropertiesUtil.getAllProperty();
        Collection<Car> cars = CarFactory.newCars(allProperty, 10);
        ExecutorService executorService = Executors.newScheduledThreadPool(cars.size());
        for (Car car : cars) {
            executorService.submit(car);
        }
        executorService.shutdown();
    }
}
