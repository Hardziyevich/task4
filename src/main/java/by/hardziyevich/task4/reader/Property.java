package by.hardziyevich.task4.reader;

import java.util.*;

public final class Property {
    private final String typeCar;
    private final int weight;
    private final int size;
    private final Collection<Property> properties;
    private final Map<String, Property> mapping;


    private Property(Builder builder) {
        this.typeCar = builder.typeCar;
        this.weight = builder.weight;
        this.size = builder.size;
        this.properties = builder.properties;
        this.mapping = builder.mapping;
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

    public Collection<Property> getProperties() {
        return properties;
    }

    public Map<String, Property> getMapping() {
        return mapping;
    }


    public final static class Builder {
        private String typeCar;
        private int weight;
        private int size;
        private Collection<Property> properties;
        private Map<String, Property> mapping;

        public Builder typeCar(String typeCar) {
            this.typeCar = typeCar;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder properties(Collection<Property> properties) {
            this.properties = properties;
            return this;
        }

        public Builder mapping(Map<String, Property> mapping) {
            this.mapping = mapping;
            return this;
        }

        public Property build() {
            return new Property(this);
        }
    }
}
