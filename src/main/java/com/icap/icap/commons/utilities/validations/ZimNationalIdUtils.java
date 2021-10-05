package com.icap.icap.commons.utilities.validations;

import lombok.val;

import java.util.HashMap;
import java.util.Map;


public class ZimNationalIdUtils {

    private static HashMap<Integer, Character> lettersMap;

    public static String formatNationalId(String idNumber) {
        idNumber = idNumber.toUpperCase();
        val temp = new StringBuilder();
        for (int i = 0; i < idNumber.length(); i++) {
            Character c = idNumber.charAt(i);
            if (Character.isDigit(c) || Character.isLetter(c)) {
                temp.append(c);
            }
        }
        return temp.toString();

    }

    public static boolean validateNationalID(String nationalID) {
        val buf = new StringBuilder();
        Character character;
        if (lastCharacterIsLetter(nationalID)) {
            return false;
        }

        nationalID = nationalID.toUpperCase();
        int digitsCount = 0;
        for (int i = 0; i < nationalID.length(); i++) {
            Character c = nationalID.charAt(i);

            if (Character.isLetter(c)) {
                character = c;
                for (int j = i; j < nationalID.length(); j++) {
                    if (Character.isDigit(nationalID.charAt(j))) {
                        digitsCount++;
                    }
                }
                if (digitsCount != 2) {
                    return false;
                } else {
                    try {
                        long number = Long.parseLong(buf.toString());
                        int checksum = (int) (number % 23);
                        Character correct = getLettersMap().get(checksum);
                        if (character.equals(correct)) {
                            return true;
                        } else {
                            String withoutLeadingZeroes = removeLeadingZeroes(buf.substring(2));
                            number = Long.parseLong(buf.substring(0, 2) + withoutLeadingZeroes);
                            checksum = (int) (number % 23);
                            correct = getLettersMap().get(checksum);
                            return character.equals(correct);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            } else if (Character.isDigit(c)) {
                buf.append(c);
            }

        }
        return false;
    }

    private static boolean lastCharacterIsLetter(String nationalID) {
        val c = nationalID.charAt(nationalID.length() - 1);
        return Character.isLetter(c);
    }


    public static Map<Integer, Character> getLettersMap() {
        if (lettersMap == null) {
            lettersMap = new HashMap<>();
            lettersMap.put(0, 'Z');
            lettersMap.put(1, 'A');
            lettersMap.put(2, 'B');
            lettersMap.put(3, 'C');
            lettersMap.put(4, 'D');
            lettersMap.put(5, 'E');
            lettersMap.put(6, 'F');
            lettersMap.put(7, 'G');
            lettersMap.put(8, 'H');
            lettersMap.put(9, 'J');
            lettersMap.put(10, 'K');
            lettersMap.put(11, 'L');
            lettersMap.put(12, 'M');
            lettersMap.put(13, 'N');
            lettersMap.put(14, 'P');
            lettersMap.put(15, 'Q');
            lettersMap.put(16, 'R');
            lettersMap.put(17, 'S');
            lettersMap.put(18, 'T');
            lettersMap.put(19, 'V');
            lettersMap.put(20, 'W');
            lettersMap.put(21, 'X');
            lettersMap.put(22, 'Y');
        }
        return lettersMap;
    }

    public static String removeLeadingZeroes(String value) {
        while (value.startsWith("0")) {
            value = value.substring(1);
        }
        return value;
    }

}
