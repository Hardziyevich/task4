package by.hardziyevich.task4.entity;

import by.hardziyevich.task4.reader.Property;
import by.hardziyevich.task4.service.IdGenerator;


public class Car implements Runnable {

    private final long id;
    private final String typeCar;
    private final int weight;
    private final int size;

    public Car(Property property) {
        this.id = IdGenerator.generateId();
        this.typeCar = property.getTypeCar();
        this.size = property.getSize();
        this.weight = property.getWeight();
    }

    public long getId() {
        return id;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }


    @Override
    public void run() {
        Ferry instance = Ferry.getINSTANCE();
        instance.addCarToFerry(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && weight == car.weight && size == car.size && typeCar.equals(car.typeCar);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + Long.hashCode(id);
        result = result * prime + (typeCar == null ? 0 : typeCar.hashCode());
        result = result * prime + Integer.hashCode(weight);
        result = result * prime + Integer.hashCode(size);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", typeCar='").append(typeCar).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", size=").append(size).append('}');
        return sb.toString();
    }
}
