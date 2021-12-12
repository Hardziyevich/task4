package by.hardziyevich.task4.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static by.hardziyevich.task4.entity.Ferry.StateFerry.*;

public class Ferry {

    public enum StateFerry {
        LOADING,
        FUll,
        SHIPMENT,
        UNLOADING,
        COMEBACK
    }

    private static final Logger log = LoggerFactory.getLogger(Ferry.class);
    private static final int MAX_WEIGHT = 10000;
    private static final int MAX_SIZE = 40;

    private static Ferry INSTANCE;
    private static final AtomicBoolean stateInstance = new AtomicBoolean(false);
    private static final ReentrantLock lockInstance = new ReentrantLock(true);

    private int currentWeight;
    private int currentSize;
    private final List<Car> listShipment = new ArrayList<>();
    private StateFerry stateFerry = LOADING;

    private Ferry() {
    }

    public static Ferry getINSTANCE() {
        if (!stateInstance.get()) {
            lockInstance.lock();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new Ferry();
                    stateInstance.set(true);
                }
            } finally {
                lockInstance.unlock();
            }
        }
        return INSTANCE;
    }

    public void addCarToFerry(Car car) {
        log.info("The car {} arrived at the port.", car);
        lockInstance.lock();
        try {
            currentWeight += car.getWeight();
            currentSize += car.getSize();
            if (currentWeight > MAX_WEIGHT || currentSize > MAX_SIZE) {
                stateFerry = FUll;
                startFerry();
            }
            log.info("Current weight {}, current size {}", currentWeight, currentSize);
            listShipment.add(car);
            log.info("Car {} was loaded on ferry", car);
        } catch (InterruptedException e) {
            log.warn("WRONG!");
        } finally {
            lockInstance.unlock();
        }
        if(lockInstance.getQueueLength() == 0){
            try {
                startFerry();
            } catch (InterruptedException e) {
                log.warn("WRONG!");
            }
        }
    }

    private void startFerry() throws InterruptedException {
            stateFerry = SHIPMENT;
            log.info("Ferry - {}", stateFerry);
            TimeUnit.MILLISECONDS.sleep(100);

            stateFerry = UNLOADING;
            log.info("Ferry - {}", stateFerry);
            TimeUnit.MILLISECONDS.sleep(10);

            ListIterator<Car> carListIterator = listShipment.listIterator();
            while (carListIterator.hasNext()) {
                Car next = carListIterator.next();
                currentWeight -= next.getWeight();
                currentSize -= next.getSize();
                carListIterator.remove();
                log.info("Current car {} was unloading.", next);
            }
            stateFerry = COMEBACK;
            log.info("Ferry - {}", stateFerry);
            TimeUnit.MILLISECONDS.sleep(100);
    }
}
