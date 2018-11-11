package io.githiub.therealmone.model;

import java.util.List;

public class NGramSet {
    private final List<NGram> nGrams;
    private final int lengthOfNGrams;

    public NGramSet(final List<NGram> nGrams, final int lengthOfNGrams) {
        this.nGrams = nGrams;
        this.lengthOfNGrams = lengthOfNGrams;
    }

    public List<NGram> getNGrams() {
        return nGrams;
    }

    public int getLengthOfNGrams() {
        return lengthOfNGrams;
    }

    @Override
    public String toString() {
        return "Length : " + lengthOfNGrams + "\n" + nGrams.toString() + "\n";
    }
}
