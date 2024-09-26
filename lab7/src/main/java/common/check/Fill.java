package common.check;

import common.exceptions.IllegaValue;
import common.exceptions.InvalidForm;
import common.exceptions.InvalidInput;

import java.io.IOException;

public abstract class Fill<T> {
    public abstract T build() throws InvalidForm, InvalidInput, IOException, IllegaValue;
}