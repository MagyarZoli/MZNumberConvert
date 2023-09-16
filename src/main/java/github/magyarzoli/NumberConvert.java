package github.magyarzoli;

import lombok.*;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * NumberConvert class is designed to convert numeric values into their textual representations in the Hungarian
 * language. It accomplishes this by utilizing a set of predefined arrays, methods, and fields to handle various
 * components of the conversion process.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class NumberConvert {

    /**
     * Array of the 1st digit of an integer
     */
    private final String[] hun1 = new String[]{
            "", "egy", "kettő", "három", "négy", "öt", "hat", "hét", "nyolc", "kilenc"
    };

    /**
     * Array of the 4th / 7th / 10th digits of an integer if the next value is not 0 or 1.
     */
    private final String[] hun2 = new String[]{
            "", "", "két", "három", "négy", "öt", "hat", "hét", "nyolc", "kilenc"
    };

    /**
     * Array of 4th / 7th / 10th digits of an integer
     */
    private final String[] hun3 = new String[]{
            "", "egy", "két", "három", "négy", "öt", "hat" ,"hét", "nyolc", "kilenc"
    };

    /**
     * Array of 2nd / 5th / 8th digits of an integer
     */
    private final String[] hun4 = new String[]{
            "", "tizen", "huszon", "harminc", "negyven", "ötven", "hatvan", "hetven", "nyolcvan", "kilencven"
    };

    /**
     * Array of the 2nd/5th/8th digit of an integer if the number behind it is 0.
     * The value of zero is also loaded here.
     */
    private final String[] hun5 = new String[]{
            "nulla", "tíz", "húsz"
    };

    /**
     * Addition, depending on the size of the number.
     */
    private final String[] hun6 = new String[]{
            "száz", "ezer", "milllió", "milliárd"
    };

    /**
     * Positive or negative keywords of number.
     */
    private final String[] hun7 = new String[]{
            "", "minusz "
    };

    /**
     * An array of strings containing the supported languages. In this case, only "hu" (Hungarian) is included.
     */
    private final String[] languages = new String[]{"hu"};

    /**
     * A character array that stores the individual digits of the number being converted.
     */
    private char[] charArray;

    /**
     * An integer array that holds the numeric values of the digits from the charArray.
     */
    private int[] numberArray;

    /**
     * A variable to hold the original number being converted.
     */
    private long compareValue;

    /**
     * A counter used in the arrayUpload method to keep track of the number of digits processed.
     */
    private int count;

    /**
     * A flag to indicate whether the number being converted is negative (1) or not (0).
     */
    private int negative;

    /**
     * A string used to separate different parts of the textual representation.
     */
    @Getter
    @Setter
    @NonNull
    private String split;

    /**
     * {@code conversionNumberName} method is a public function that acts as an interface for converting a given
     * long integer into its textual representation based on a specified language.
     * <ul>
     *     <li>The method checks if the lowercase version of the {@code language} parameter is equal to the string "hu"
     *     (for Hungarian). If the condition is true, it means the desired language is Hungarian.</li>
     *     <li>If the desired language is Hungarian, the method calls the {@code hunNumberName} method with the
     *     {@code number} parameter to obtain the textual representation of the number in Hungarian.</li>
     *     <li>If the desired language is not Hungarian, an {@link java.lang.IllegalArgumentException
     *     IllegalArgumentException} is thrown. The error message includes the provided {@code language} and a note
     *     to choose from the available language list using the {@code languages()} method.</li>
     *     <li>The method returns the textual representation of the number in the specified language.</li>
     * </ul>
     * {@code conversionNumberName} method is a convenient way to convert a long integer into its textual
     * representation for a specified language. It handles the language check and conversion by delegating to the
     * appropriate methods based on the input language.
     * @param       number The long integer to be converted to a textual representation.
     * @param       language A string representing the language in which the number should be converted.
     * @return      The textual representation of the number in the specified language.
     * @see         github.magyarzoli.NumberConvert#hunNumberName(long) hunNumberName(long)
     * @see         github.magyarzoli.NumberConvert#languages() languages()
     */
    public String conversionNumberName(long number, String language) {
        if (language == ("hu").toLowerCase()) {
            return hunNumberName(number);
        } 
        throw new IllegalArgumentException(
                language + " specified language nothing! choose from the language list: " + languages());
    }

    /**
     * {@code hunNumberName} method is the main function responsible for converting a given long integer into its
     * Hungarian textual representation.
     * <ul>
     *     <li>The method takes a {@code number} as its parameter, which is the long integer to be converted to a
     *     text representation.</li>
     *     <li>It assigns the value of {@code number} to the {@code compareValue} variable for later use.</li>
     *     <li>It checks whether {@code compareValue} is not equal to <i>0</i>. If it's not <i>0</i>, the conversion
     *     process begins. If it's <i>0</i>, the method directly returns the Hungarian representation of the
     *     number "0" using {@code hun5[0]}</li>
     *     <li>If {@code compareValue} is not <i>0</i>, the method proceeds:</li>
     *     <li>The {@code charArray} is obtained by converting the number to a string and then splitting it into an
     *     array of characters.</li>
     *     <li>The {@code numberArray} is initialized to hold the numeric values of each digit in the
     *     {@code charArray}.</li>
     *     <li>The count is initialized to <i>0</i> to keep track of the current position in the
     *     {@code numberArray}.</li>
     *     <li>The {@code negative} variable is set to <i>0</i> by default.</li>
     *     <li>If {@code compareValue} is greater than <i>0</i>, the loop iterates through the {@code charArray} in
     *     reverse order using the {@code arrayUpload} method to populate the {@code numberArray} with the numeric
     *     values of the digits.</li>
     *     <li>If {@code compareValue} is less than or equal to <i>0</i>, the loop iterates similarly, but for all
     *     digits except the last one. This is because the last digit is considered to be the sign of the number,
     *     and the {@code negative} variable is set to <i>1</i>.</li>
     *     <li>After populating the {@code numberArray}, the method calls {@code hunNumberNameResult} to construct
     *     the final textual representation of the number in Hungarian.</li>
     *     <li>The method returns the Hungarian textual representation of the input number.</li>
     * </ul>
     * {@code hunNumberName} method serves as the entry point for converting a long integer into its Hungarian
     * textual representation, using the provided arrays and helper methods to handle different aspects of
     * the conversion process.
     * @param       number The long integer to be converted to a textual representation.
     * @return      Hungarian textual representation of the input number.
     * @see         github.magyarzoli.NumberConvert#arrayUpload(int) arrayUpload(int)
     * @see         NumberConvert#hunNumberNameResult() hunNumberNameResult()
     */
    private String hunNumberName(long number) {
        compareValue = number;
        if (compareValue != 0) {
            charArray = String.valueOf(number).toCharArray();
            numberArray = new int[charArray.length];
            count = 0;
            negative = 0;
            if (compareValue > 0) {
                for (int i = (charArray.length - 1); i >= 0; i--) {
                    arrayUpload(i); 
                }
            } else {
                for (int i = (charArray.length - 1); i > 0; i--) {
                    arrayUpload(i); 
                }
                negative = 1;
            }
            return hunNumberNameResult();
        } else {
            return hun5[0];
        }
    }

    /**
     * {@code arrayUpload} method is a utility function that takes an index {@code i} as a parameter and extracts
     * a numeric value from the {@code charArray} at the specified index. It then stores that numeric value in the
     * {@code numberArray} and increments the count variable.
     * <ul>
     *     <li>{@link java.lang.Character Character}.{@link java.lang.Character#getNumericValue(char)
     *     getNumericValue}(charArray[i]) is used to extract the numeric value from the character at index {@code i}
     *     in the {@code charArray}. This is done assuming that the character at index {@code i} represents a
     *     digit <i>(0-9)</i>.</li>
     *     <li>The extracted numeric value is stored in the {@code numberArray} at the current value of the
     *     {@code count} variable.</li>
     *     <li>After storing the value, the {@code count} variable is incremented.</li>
     * </ul>
     * {@code arrayUpload} method is used within the context of the {@code hunNumberName} method, where it populates
     * the {@code numberArray} with the individual numeric values of the digits in the original number. The method
     * ensures that each digit is correctly placed in the {@code numberArray} and the {@code count} variable is
     * incremented accordingly for the next digit.
     * @param       i The index from which a numeric value needs to be extracted from charArray.
     */
    private void arrayUpload(int i) {
        numberArray[count++] = Character.getNumericValue(charArray[i]);
    }

    /**
     * {@code hunNumberNameResult} method is responsible for constructing the textual representation of the number in
     * Hungarian. It uses various helper methods, including {@code reverseArray} and {@code reviewNumber},
     * to generate the final result.
     * <ul>
     *     <li>Inside the {@code hunNumberNameResult} method, a new array called {@code reverseArray} is created
     *     by calling the {@code reverseArray} method on the {@code numberArray}</li>
     *     <li>The method returns a concatenated string that represents the textual representation of the number
     *     in Hungarian. The concatenation is done by using several calls to the reviewNumber method, each
     *     representing a part of the number: billions, millions, thousands, and units.</li>
     *     <li>Each {@code reviewNumber} call is made with the appropriate indices from the {@code reverseArray}
     *     and the corresponding extension {@code hun6} based on the position of the number (billions, millions,
     *     thousands, or units). The {@code hyphen} method is used to determine if a hyphen is needed before the
     *     constructed text.</li>
     *     <li>The concatenated string includes the negative keyword {@code hun7} at the beginning if the negative
     *     value is set to <i>1</i>. The negative value is set based on whether the original compareValue is
     *     negative.</li>
     * </ul>
     * {@code hunNumberNameResult} method uses helper methods and the provided arrays to construct the textual
     * representation of a number in Hungarian, taking into account different parts of the number and their
     * respective positions. It effectively combines the results obtained from various calls to the
     * {@code reviewNumber} method to create the final output string.
     * @return      generate the final result.
     * @see         github.magyarzoli.NumberConvert#reverseArray(int[]) reverseArray(int[])
     * @see         github.magyarzoli.NumberConvert#reviewNumber(int, int, int, int[], int, String)
     *              reviewNumber(int, int, int, int[], int, String)
     */
    private String hunNumberNameResult(){
        int[] reverseArray = reverseArray(numberArray);
        return (hun7[negative] +
                reviewNumber(9, 10, 11, reverseArray, 12, hun6[3]) +
                reviewNumber(6, 7, 8, reverseArray, 9, hun6[2]) +
                reviewNumber(3, 4, 5, reverseArray, 6, hun6[1]) +
                reviewNumber(0, 1, 2, reverseArray, 3, null)
        );
    }

    /**
     * {@code reverseArray} method in takes an array of integers called {@code numberArray} and returns a new array
     * of integers called {@code reverseArray} that is constructed by reversing the values in the input array.
     * <ul>
     *     <li>Inside the method, a new array {@code reverseArray} is initialized with a length of <i>12</i>.
     *     This new array will hold the reversed values from the {@code numberArray}.</li>
     *     <li>A loop is started that iterates from index <i>0</i> to index <i>11</i> (inclusive) of the
     *     {@code reverseArray}.</li>
     *     <li>A {@code try} block is used to access the corresponding element at index {@code i} in the
     *     {@code numberArray}.</li>
     *     <li>If the index {@code i} is within the bounds of the {@code numberArray}, the value at
     *     {@code numberArray[i]} is assigned to {@code reverseArray[i]}.</li>
     *     <li>If the index {@code i} is outside the bounds of the {@code numberArray}, an
     *     {@link java.lang.ArrayIndexOutOfBoundsException ArrayIndexOutOfBoundsException} is caught, and the
     *     corresponding element in {@code reverseArray} is set to <i>0</i>.</li>
     *     <li>After the loop completes, the {@code reverseArray} containing the reversed values is returned.</li>
     * </ul>
     * {@code reverseArray} method ensures that even if the {@code numberArray} does not have <i>12</i> elements,
     * it creates a new array of length <i>12</i> and populates it with the reversed values from {@code numberArray}.
     * If there are fewer elements in {@code numberArray}, the remaining elements in {@code reverseArray} are
     * filled with zeros.
     * @param       numberArray An array of integers that holds the values of the number's digits.
     * @return      The reversed values.
     */
    private int[] reverseArray(int[] numberArray) {
        int[] reverseArray = new int[12];
        for(int i = 0; i < reverseArray.length; i++){
            try{
                reverseArray[i] = numberArray[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                reverseArray[i] = 0;
            }
        }
        return reverseArray;
    }

    /**
     * {@code reviewNumber} method is a utility function that consolidates the values of the hundreds, tens, and ones
     * places along with some additional information and then passes them to the {@code branches} method.
     * <ul>
     *     <li>Inside the method, it calls the {@code hyphen} method with {@code array} and {@code index} to determine
     *     whether a hyphen is needed before the constructed text. This hyphen value is then used as an argument for
     *     the {@code branches} method.</li>
     *     <li>The branches method is called with the following arguments:</li>
     *     <li>{@code array[hundreds]}: The value at the hundreds place in the {@code array}.</li>
     *     <li>{@code array[tens]}: The value at the tens place in the {@code array}.</li>
     *     <li>{@code array[ones]}: The value at the ones place in the {@code array}.</li>
     *     <li>The hyphen value returned by the {@code hyphen} method.</li>
     *     <li>{@code extension}: The provided extension value.</li>
     *     <li>The result of the {@code branches} method call is returned as the result of the
     *     {@code reviewNumber} method.</li>
     * </ul>
     * {@code reviewNumber} method prepares the relevant values and calls the {@code branches} method, which handles
     * different cases of constructing the text representation of numbers based on the provided values and conditions.
     * The use of the hyphen method helps determine if a hyphen is needed before the text constructed by the
     * {@code branches} method.
     * @param       hundreds The index representing the hundreds place in the array.
     * @param       tens The index representing the tens place in the array.
     * @param       ones The index representing the ones place in the array.
     * @param       array The array of integers that stores the values of the number's digits.
     * @param       index The index from which the hyphen method should start checking for hyphen requirements.
     * @param       extension An extension that can be added to the textual representation if necessary.
     * @return      As the result of the reviewNumber.
     * @see         github.magyarzoli.NumberConvert#branches(int, int, int, String, String)
     *              branches(int, int, int, String, String)
     * @see         github.magyarzoli.NumberConvert#hyphen(int[], int) hyphen(int[], int)
     */
    private String reviewNumber(int hundreds, int tens, int ones, int[] array, int index, String extension) {
        return branches(array[hundreds], array[tens], array[ones], hyphen(array, index), extension);
    }

    /**
     * {@code hyphen} method used to determine whether a hyphen should be included before the constructed text in
     * certain cases. It takes an array of integers {@code array} and an {@code index} as parameters.
     * <ul>
     *     <li>It initializes an empty string hyphen.</li>
     *     <li>The method iterates through the elements of the {@code array} starting from the specified
     *     {@code index}.</li>
     *     <li>If the loop index {@code i} is <i>3</i> (representing the thousands place in the context of this code),
     *     it checks if the value at {@code array[3]} is not <i>0</i> and not <i>1</i>. If this condition is met,
     *     it sets {@code hyphen} to "-" and exits the loop.</li>
     *     <li>If the value at {@code array[i]} is not <i>0</i>, it sets hyphen to "-" and exits the loop.</li>
     *     <li>Finally, the method returns the value of hyphen.</li>
     * </ul>
     * {@code hyphen} method is used to determine whether a hyphen should be added before certain parts of the
     * constructed text. It primarily checks whether a hyphen is needed before thousands (array index 3) and
     * any subsequent non-zero values. If the value at index 3 is not 0 or 1, a hyphen is added to indicate thousands.
     * Additionally, if any subsequent value is non-zero, a hyphen is added to separate the parts.
     * @param       array iterates through the elements of the array
     * @param       index starting from the specified index.
     * @return      The value of hyphen.
     */
    private String hyphen(int[] array, int index) {
        String hyphen = "";
        for (int i = index; i < array.length; i++) {
            if (i == 3) {
                if (array[3] != 0 && array[3] != 1) {
                    hyphen = "-";
                    break;
                }
            } else if (array[i] != 0) {
                hyphen = "-";
                break;
            }
        }
        return hyphen;
    }

    /**
     * {@code branches} method to be responsible for handling different cases when constructing parts of the number
     * name string. It considers the values of the ones, tens, and hundreds places of the number, along with other
     * conditions, to determine the appropriate text to include in the final output.
     * <ul>
     *     <li>If all the ones, tens, and hundreds places are zeros, it returns the {@code split} value.</li>
     *     <li>If hundreds and tens are zeros, and ones is not zero, it constructs the text.</li>
     *     <li>If hundreds and ones are zeros, and tens is <i>1</i>, it constructs the text.</li>
     *     <li>If hundreds is zero and tens is not zero, it constructs the text.</li>
     *     <li>If hundreds is not zero and tens and ones are zeros, it constructs the text.</li>
     *     <li>If hundreds is not zero, tens is <i>1</i>, and ones is zero, it constructs the text</li>
     *     <li>If hundreds is not zero, tens is <i>2</i>, and ones is zero, it constructs the text </li>
     *     <li>For all other cases, it constructs the text</li>
     * </ul>
     * The hyphen parameter determines whether a hyphen should be included before the constructed text, and the
     * split parameter is used to introduce separation between different parts of the constructed text.
     * {@code branches} this method plays a significant role in constructing the textual representation of numbers
     * in the Hungarian language based on the provided ones, tens, and hundreds values.
     * @param       ones provided values.
     * @param       tens provided values.
     * @param       hundreds provided values.
     * @param       hyphen determines whether a hyphen should be included before the constructed text.
     * @param       extension further extension.
     * @return      A string of number name.
     */
    private String branches(int ones, int tens, int hundreds, String hyphen, String extension) {
        if (ones == 0 && tens==0 && ones == 0) {
            return split + "";
        } else if (hundreds == 0 && tens == 0 && ones != 0) {
            return hyphen + split
                    + ((extension == null) ? hun1[ones] : (hun2[ones] + extension));
        } else if (hundreds == 0 && tens == 1 && ones == 0) {
            return hyphen + split + hun5[1]
                    + ((extension == null) ? "" : extension);
        } else if (hundreds == 0 && tens == 2 && ones == 0) {
            return hyphen + split + hun5[2]
                    + ((extension == null) ? "" : extension);
        } else if (hundreds == 0 && tens != 0) {
            return hyphen + split + hun4[tens] + hun1[ones]
                    + ((extension == null) ? "" : extension);
        } else if (hundreds != 0 && tens == 0 && ones == 0) {
            return hyphen + split + hun2[hundreds] + hun6[0]
                    + ((extension == null) ? "" : extension);
        } else if (hundreds != 0 && tens == 1 && ones == 0) {
            return hyphen + split + hun2[hundreds] + hun6[0] + hun5[1]
                    + ((extension == null) ? "" : extension);
        } else if (hundreds != 0 && tens == 2 && ones == 0) {
            return hyphen + split + hun2[hundreds] + hun6[0] + hun5[2]
                    + ((extension == null) ? "" : extension);
        } else {
            return hyphen + split + hun2[hundreds] + hun6[0] + hun4[tens]
                    + ((extension == null) ? hun1[ones] : (hun3[ones] + extension));
        }
    }

    /**
     * {@code languages()} method in the provided code is used to concatenate the elements of the {@code languages}
     * array into a single string, separated by a space.
     * <ul>
     *     <li>{@code languages} is an array of strings that holds the available languages.</li>
     *     <li>{@link java.util.Arrays Arrays}.{@link java.util.Arrays#stream(Object[]) stream()} creates a
     *     stream of the elements in the {@code languages} array.</li>
     *     <li>.{@link java.util.stream.Stream#collect(Collector) collect}
     *     ({@link java.util.stream.Collectors}.{@link java.util.stream.Collectors#joining(CharSequence) joining}(" "))
     *     collects the elements of the stream and joins them into a single string. The parameter " " (a single space)
     *     is used as the delimiter between the elements.</li>
     * </ul>
     * @return      List of supported languages.
     */
    private String languages() {
        return Arrays.stream(languages).collect(Collectors.joining(" "));
    }
}