package io.githiub.therealmone.model;

public class NGram {
    private final String value;
    private final double frequency;

    public NGram(final String value, final double frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public String getValue() {
        return value;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "\n{" + value + " : " + frequency + "}";
    }
}
