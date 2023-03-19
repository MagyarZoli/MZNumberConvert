import java.lang.ArrayIndexOutOfBoundsException;

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
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class MZNumberConvert { 
    private static final String[] hun1 = new String[]{"","egy","kettő","három","négy","öt","hat","hét","nyolc","kilenc"};
    private static final String[] hun2 = new String[]{"","","két","három","négy","öt","hat","hét","nyolc","kilenc"};
    private static final String[] hun3 = new String[]{"","egy","két","három","négy","öt","hat","hét","nyolc","kilenc"};
    private static final String[] hun4 = new String[]{"","tizen","huszon","harminc","negyven","ötven","hatvan","hetven","nyolcvan","kilencven"};
    private static final String[] hun5 = new String[]{"nulla","tíz","húsz"};
    private static final String[] hun6 = new String[]{"száz","ezer","milllió","milliárd"};
    private static final String[] hun7 = new String[]{"","minusz "};
    private static String splitStr;

    public MZNumberConvert(String splitStr){
        if(splitStr==null){
            MZNumberConvert.splitStr="";
        }
        else{
            MZNumberConvert.splitStr=splitStr;
        }
    }
    
    public String conversionNumberName(int number, String language){
        if(language==("hu").toLowerCase()){
            return hunNumberName(number);
        }   
        return null;
    }

    private static String hunNumberName(int number){
        if(number!=0){
            char[] charArray = String.valueOf(number).toCharArray();
            int[] numberArray = new int[charArray.length];
            int j=0, negative=0;
            if(number>0){
                for(int i=charArray.length-1; i>=0; i--){
                    numberArray[j]=Character.getNumericValue(charArray[i]);
                    j++; 
                }
            }
            else{
                for(int i=charArray.length-1; i>0; i--){
                    numberArray[j]=Character.getNumericValue(charArray[i]);
                    j++; 
                }
                negative=1;
            }
            int[] reverseArray = new int[12];
            for(int i=0; i<reverseArray.length; i++){
                try{
                    reverseArray[i]=numberArray[i];
                }
                catch(ArrayIndexOutOfBoundsException e){
                    reverseArray[i]=0;
                }
            }
            return hun7[negative]+
            reviewNumber(reverseArray[9], reverseArray[10], reverseArray[11], reverseArray, 12, hun6[3])+
            splitStr+
            reviewNumber(reverseArray[6], reverseArray[7], reverseArray[8], reverseArray, 9, hun6[2])+
            splitStr+
            reviewNumber(reverseArray[3], reverseArray[4], reverseArray[5], reverseArray, 6, hun6[1])+
            splitStr+
            reviewNumber(reverseArray[0], reverseArray[1], reverseArray[2], reverseArray, 3);
        }
        else{
            return hun5[0];
        }
    }
    
    private static String reviewNumber(int a, int b, int c, int[] d, int index){
        String hyphen = "";
        for(int i=index; i<d.length; i++){
            if(i==3){
                if(d[3]!=0 && d[3]!=1){
                    hyphen = "-";
                    break;
                }
                continue;
            }
            else if(d[i]!=0){
                hyphen = "-";
                break;
            }
        }
        if(c==0 && b==0 && a==0){
            return "";
        }
        else if(c==0 && b==0 && a!=0){
            return hyphen+hun1[a];
        }
        else if(c==0 && b==1 && a==0){
            return hyphen+hun5[1];
        }
        else if(c==0 && b==2 && a==0){
            return hyphen+hun5[2];
        }
        else if(c==0 && b!=0){
            return hyphen+hun4[b]+hun1[a];
        }
        else if(c!=0 && b==0 && a==0){
            return hyphen+hun2[c]+hun6[0];
        }
        else if(c!=0 && b==1 && a==0){
            return hyphen+hun2[c]+hun6[0]+hun5[1];
        }
        else if(c!=0 && b==2 && a==0){
            return hyphen+hun2[c]+hun6[0]+hun5[2];
        }
        else{
            return hyphen+hun2[c]+hun6[0]+hun4[b]+hun1[a];
        }
    }

    private static String reviewNumber(int a, int b, int c, int[] d, int index, String x){
        String hyphen = "";
        for(int i=index; i<d.length; i++){
            if(d[i]!=0){
                hyphen = "-";
                break;
            }
        }
        if(c==0 && b==0 && a==0){
            return "";
        }
        else if(c==0 && b==0 && a!=0){
            return hyphen+hun2[a]+x;
        }
        else if(c==0 && b==1 && a==0){
            return hyphen+hun5[1]+x;
        }
        else if(c==0 && b==2 && a==0){
            return hyphen+hun5[2]+x;
        }
        else if(c==0 && b!=0){
            return hyphen+hun4[b]+hun1[a]+x;
        }
        else if(c!=0 && b==0 && a==0){
            return hyphen+hun2[c]+hun6[0]+x;
        }
        else if(c!=0 && b==1 && a==0){
            return hyphen+hun2[c]+hun6[0]+hun5[1]+x;
        }
        else if(c!=0 && b==2 && a==0){
            return hyphen+hun2[c]+hun6[0]+hun5[2]+x;
        }
        else{
            return hyphen+hun2[c]+hun6[0]+hun4[b]+hun3[a]+x;
        }
    }
}
