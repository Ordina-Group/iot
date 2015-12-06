package be.ordina.iot.model;

import be.ordina.iot.exceptions.InputValidationException;

import java.util.Random;

/**
 * Model class that contains our random generated number and some extra information.
 */
public class RandomResult {

    private Integer minimum;
    private Integer maximum;
    private Integer randomValue;

    /**
     * Constructor for the RandomResult class.
     * When the random number is generated a number will be picked between the minimum and maximum values (values included)
     * Range: [minimum, maximum]
     *
     * @param minimum The minimum value to set. This is the lowest value that can be randomly selected.
     * @param maximum The maximum value to set. This is the highest value that can be randomly selected.
     */
    public RandomResult(Integer minimum, Integer maximum) throws InputValidationException {
        this.minimum = minimum;
        this.maximum = maximum;

        validateInput();
        generateRandomValue();
    }

    /**
     * Validates the given input (minimum and maximum values)
     *
     * @throws InputValidationException Is thrown when the validation has failed!
     */
    private void validateInput() throws InputValidationException {
        if(minimum < 0) {
            throw new InputValidationException("Minimum must be greater than zero!", minimum, maximum);
        }
        if(maximum < minimum) {
            throw new InputValidationException("Maximum must be greater than minimum value!", minimum, maximum);
        }
        //TODO: More validations?
    }

    /**
     * Generates the actual random (pseudo) random value.
     */
    private void generateRandomValue() {
        Random r = new Random();
        randomValue = r.nextInt((maximum + 1) - minimum) + minimum;
    }

    //Getters & setters:
    public Integer getMinimum() {
        return minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public Integer getRandomValue() {
        return randomValue;
    }
}