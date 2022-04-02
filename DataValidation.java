import java.lang.reflect.Array;

// This class is used to validate data that has been inputted by the user.
public class DataValidation {

    public String input;
    public String dataType;
    public Array[] fields;

    // This constructor is for single fields/textboxes input and their datatype that
    // youll be checking for
    public DataValidation(String input, String type) {
        this.input = input;
        this.dataType = type;
    }

    // This constructor is for an array containing arrays of inputs and datatypes in
    // the event that there is a need for validation of more than one fields at a
    // time
    public DataValidation(Array[] fields) {
        this.fields = fields;
    }

    // some getters were put in place to help
    public String getInput() {
        return this.input;
    }

    public String getDataType() {
        return this.dataType;
    }

    public Array[] getFields() {
        return this.fields;
    }

    // This is where the magic happens, here you will validate each input based on
    // their datatype that comes with the params from the constructor.
    // So to make this a bit clearer, this function should be able to check arrays
    // with inputs or single inputs.
    // Another "validate" function can be created to get optional parameters, its up
    // to you to decide
    // Function should return a pop up error message or for your sanity, probably
    // just a boolean of true or false
    public void validate() {

    }

    // public void validate()
    // {

    // }
}
