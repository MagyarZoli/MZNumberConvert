package mz;

import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * This program converts the requested number into its Hungarian word representation using the defined rules and mappings. 
 * {@code hunNumberName}
 * The program first defines the mappings places. 
 * The conversion function then breaks the number into groups of three digits and converts each group separately, 
 * appending the corresponding place to each group as necessary. 
 * {@code reviewNumber}
 * Finally, the converted words for each group are combined and returned as a single string.
 * @param number number you want to convert
 * @param language language selection, according to which spelling rules to convert the number
 * @see  java.lang.ArrayIndexOutOfBoundsException
 * @see  java.math.BigInteger
 * @since 1.3
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class MZNumberConvert { 

    /**
     * list of the 1st digit of an integer
     */
    private final String[] hun1 = new String[]{"","egy","kettő","három","négy","öt","hat","hét","nyolc","kilenc"};
    
    /**
     * list of the 4th / 7th / 10th digits of an integer if the next value is not 0 or 1.
     */
    private final String[] hun2 = new String[]{"","","két","három","négy","öt","hat","hét","nyolc","kilenc"};
    
    /**
     * list of 4th/7th/10th digits of an integer
     */
    private final String[] hun3 = new String[]{"","egy","két","három","négy","öt","hat","hét","nyolc","kilenc"};
    
    /**
     * list of 2nd/ 5th/ 8th digits of an integer
     */
    private final String[] hun4 = new String[]{"","tizen","huszon","harminc","negyven","ötven","hatvan","hetven","nyolcvan","kilencven"};
    
    /**
     * list of the 2nd/5th/8th digit of an integer if the number behind it is 0.
     * The value of zero is also loaded here.
     */
    private final String[] hun5 = new String[]{"nulla","tíz","húsz"};
    
    /**
     * addition, depending on the size of the number.
     */
    private final String[] hun6 = new String[]{"száz","ezer","milllió","milliárd"};
    
    /**
     * positive or negative keywords of number.
     */
    private final String[] hun7 = new String[]{"","minusz "};
    
    /**
     * storing languages in an array
     */
    private final String[] languages = new String[]{"hu"};
    
    /**
     * splitting number into characters storing in array 
     */
    private char[] charArray;

    /**
     * converting and loading the characters of a chunked number into the array.
     */
    private int[] numberArray;

    /**
     * Compares this BigInteger with the specified BigInteger.
     * @see BigInteger#compareTo
     */
    private long compareValue;

    /**
     * counter counts the digits of the number, discards a negative sign '-' if there is one.
     */
    private int count;

    /**
     * if the number is negative, it is filled with a negative keyword
     */
    private int negative;

    /**
     * the character or character string that divides the number.
     */
    private String split;

    /**
     * By default, it does not split the number name.
     */
    public MZNumberConvert(){
        this.split="";
    }
    
    /**
     * Separates the name of the number into several pieces based on the specified character/string.
     * If the argument is null, it does not split the name of the number.
     * @param splitStr separates the name of the number
     */
    public MZNumberConvert(String split){
        if(split==null){
            this.split="";
        }
        this.split = split;
    }

    /**
     * Char/String can be specified, which separates the name of the number into several pieces.
     * @param split separates the name of the number
     */
    public void setSplit(String split){
        this.split = split;
    }

    /**
     * Returns which char or String the character is separated from.
     * If not specified, the value returns an empty character.
     * @return get char/String separates the name of the number
     */
    public String getSplit(){
        return this.split;
    }

    /**
     * Converts the number into a text number name according to the specified number and specified language.
     * <p>list of available languages:</p>
     * <ul>
     *  <li>"hu" - Hungarian</li>
     * </ul>
     * @param number of type long
     * @param language 
     * @see MZNumberConvert#hunNumberName(long)
     * @see MZNumberConvert#languages()
     * @throws Error if the specified language is not available.
     */
    public String conversionNumberName(long number, String language){
        if(language==("hu").toLowerCase()){
            return hunNumberName(number);
        } 
        throw new Error(language+" specified language nothing! choose from the language list: "+languages()); 
    }

    /**
     * Converts the number into a text number name according to the specified number and specified language.
     * <p>list of available languages:</p>
     * <ul>
     *  <li>"hu" - Hungarian</li>
     * </ul>
     * @param number of type BigInteger
     * @param language 
     * @see java.math.BigInteger
     * @see MZNumberConvert#hunNumberNameBig(BigInteger)
     * @see MZNumberConvert#languages()
     * @throws Error if the specified language is not available.
     */
    public String conversionNumberName(BigInteger number, String language){
        if(language==("hu").toLowerCase()){
            return hunNumberNameBig(number);
        }   
        throw new Error(language+" specified language nothing! choose from the language list: "+languages());
    }

    /**
     * Converts to a number in Hungarian
     * @param number of type long
     * @see MZNumberConvert#arrayUpload(int)
     * @see MZNumberConvert#hunNumberNameResult()
     * @return number name 
     */
    private String hunNumberName(long number){
        compareValue = number;
        if(compareValue>0 || compareValue<0){
            charArray = String.valueOf(number).toCharArray();
            numberArray = new int[charArray.length];
            count=0; 
            negative=0;
            if(compareValue>0){
                for(int i=charArray.length-1; i>=0; i--){
                    arrayUpload(i); 
                }
            }
            else{
                for(int i=charArray.length-1; i>0; i--){
                    arrayUpload(i); 
                }
                negative=1;
            }
            return hunNumberNameResult();
        }
        else{
            return hun5[0];
        }
    }

    /**
     * Converts to a number in Hungarian
     * @param number of type BigInteger
     * @see java.math.BigInteger
     * @see MZNumberConvert#arrayUpload(int)
     * @see MZNumberConvert#hunNumberNameResult()
     * @return number name 
     */
    private String hunNumberNameBig(BigInteger number){
        compareValue = number.compareTo(new BigInteger("0"));
        if(compareValue>0 || compareValue<0){
            charArray = String.valueOf(number).toCharArray();
            numberArray = new int[charArray.length];
            count=0; 
            negative=0;
            if(compareValue>0){
                for(int i=charArray.length-1; i>=0; i--){
                    arrayUpload(i); 
                }
            }
            else{
                for(int i=charArray.length-1; i>0; i--){
                    arrayUpload(i); 
                }
                negative=1;
            }
            return hunNumberNameResult();
        }
        else{
            return hun5[0];
        }
    }

    /**
     * Array uploading and counting
     * Character that carries a number conversion to int type
     * @param i for loop counter value
     */
    private void arrayUpload(int i){
        numberArray[count]=Character.getNumericValue(charArray[i]);
        count++; 
    }

    /**
     * Returns the final result but only applied at the end of the corresponding method.
     * @see MZNumberConvert#reverseArray(int[])
     * @see MZNumberConvert#reviewNumber(int, int, int, int[], int, String)
     * @return final result
     */
    private String hunNumberNameResult(){
        int[] reverseArray = reverseArray(numberArray);
        return (hun7[negative]+
            reviewNumber(9, 10, 11, reverseArray, 12, hun6[3])+
            reviewNumber(6, 7, 8, reverseArray, 9, hun6[2])+
            reviewNumber(3, 4, 5, reverseArray, 6, hun6[1])+
            reviewNumber(0, 1, 2, reverseArray, 3, null)
        );
    }

    /**
     * Returns the elements of the desired array in reverse order in an array of 12 elements.
     * Empty elements assume the value 0 by definition.
     * @param numberArray
     * @see MZNumberConvert#arrayUpload(int)
     * @see java.lang.ArrayIndexOutOfBoundsException
     * @return numberArray revers version of 12 elements
     */
    private int[] reverseArray(int[] numberArray){
        int[] reverseArray = new int[12];
        for(int i=0; i<reverseArray.length; i++){
            try{
                reverseArray[i]=numberArray[i];
            }
            catch(ArrayIndexOutOfBoundsException e){
                reverseArray[i]=0;
            }
        }
        return reverseArray;
    }

    /**
     * Converts the original number to 3 decimals,
     * then to hundreds and tens, and reverses single-decimal
     * values in accordance with Hungarian grammar rules
     * @param hundreds element of array with hundredth index should be hundreds
     * @param tens element of array with index should be tens
     * @param ones element of array with index should be ones
     * @param array array from which the elements are taken out for examination
     * @param index how many items to review
     * @param extension can be specified to contain the text "thousands", "millions", "billions".
     * @see MZNumberConvert#reverseArray(int[])
     * @see MZNumberConvert#hyphen(int[], int)
     * @see MZNumberConvert#branches(int, int, int, String, String)
     * @return number name with hyphens
     */
    private String reviewNumber(int hundreds, int tens, int ones, int[] array, int index, String extension){
        return branches(array[hundreds], array[tens], array[ones], hyphen(array, index), extension);
    }

    /**
     * Decides whether a hyphen must be between the digits of the number name.
     * If the number is greater than 2000 and is not rounded to 3 decimal places, a hyphen is used
     * @param array
     * @param index 
     * @see MZNumberConvert#reverseArray(int[])
     * @return if without hyphen else with hyphen
     */
    private String hyphen(int[] array, int index){
        String hyphen = "";
        for(int i=index; i<array.length; i++){
            if(i==3){
                if(array[3]!=0 && array[3]!=1){
                    hyphen = "-";
                    break;
                }
                continue;
            }
            else if(array[i]!=0){
                hyphen = "-";
                break;
            }
        }
        return hyphen;
    }
    
    /**
     * Given 3-decimal number, checking its digits in a complicated logical branching, plus the hyphen and extension must be decided
     * @param ones
     * @param tens
     * @param hundreds
     * @param hyphen substitutes a given hyphen into the decimal points of the number name if necessary.
     * @param extension can be specified to contain the text "thousands", "millions", "billions".
     * @see MZNumberConvert#reviewNumber(int, int, int, int[], int, String)
     * @see MZNumberConvert#hyphen()
     * @return return number name from complex branching
     */
    private String branches(int ones, int tens, int hundreds, String hyphen, String extension){
        if(ones==0 && tens==0 && ones==0){
            return split+"";
        }
        else if(hundreds==0 && tens==0 && ones!=0){
            return hyphen+split+((extension==null)? hun1[ones]:hun2[ones]+extension);
        }
        else if(hundreds==0 && tens==1 && ones==0){
            return hyphen+split+hun5[1]+((extension==null)? "":extension);
        }
        else if(hundreds==0 && tens==2 && ones==0){
            return hyphen+split+hun5[2]+((extension==null)? "":extension);
        }
        else if(hundreds==0 && tens!=0){
            return hyphen+split+hun4[tens]+hun1[ones]+((extension==null)? "":extension);
        }
        else if(hundreds!=0 && tens==0 && ones==0){
            return hyphen+split+hun2[hundreds]+hun6[0]+((extension==null)? "":extension);
        }
        else if(hundreds!=0 && tens==1 && ones==0){
            return hyphen+split+hun2[hundreds]+hun6[0]+hun5[1]+((extension==null)? "":extension);
        }
        else if(hundreds!=0 && tens==2 && ones==0){
            return hyphen+split+hun2[hundreds]+hun6[0]+hun5[2]+((extension==null)? "":extension);
        }
        else{
            return hyphen+split+hun2[hundreds]+hun6[0]+hun4[tens]+((extension==null)? hun1[ones]:hun3[ones]+extension);
        }
    }

    /**
     * Lists the existing languages through which the number can be translated number name
     * @return list of languages
     */
    private String languages(){
        String results = "";
        for(String language : languages){
            results += language+" ";
        }
        return results;
    }
}