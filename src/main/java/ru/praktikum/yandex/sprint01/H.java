package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class H {
    private static String sumOfBinaries(String a, String b) {
        int aRight = a.length() - 1;
        int bRight = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int minLength = Math.min(aRight, bRight);
        int carry = 0;


        for (int i = minLength; i >= 0; i--) {
            if (a.charAt(aRight) == '1' && b.charAt(bRight) == '1') {
                if (carry > 0) {
                    sb.append("1");
                } else {
                    sb.append("0");
                    carry = 1;
                }
            } else if ((a.charAt(aRight) == '0' && b.charAt(bRight) == '1')
                    || (a.charAt(aRight) == '1' && b.charAt(bRight) == '0')) {
                if (carry > 0) {
                    sb.append("0");
                } else {
                    sb.append("1");
                }
            } else if (a.charAt(aRight) == '0' && b.charAt(bRight) == '0') {
                if (carry > 0) {
                    sb.append("1");
                    carry = 0;
                } else {
                    sb.append("0");
                }
            }
            aRight--;
            bRight--;
        }

        if (aRight < 0) {
            for (int i = bRight; i >= 0; i--) {
                if (b.charAt(i) == '1') {
                    if (carry > 0) {
                        sb.append("0");
                    } else {
                        sb.append("1");
                    }
                } else if (b.charAt(i) == '0') {
                    if (carry > 0) {
                        sb.append("1");
                        carry = 0;
                    } else {
                        sb.append("0");
                    }
                }
            }
        }

        if (bRight < 0) {
            for (int i = aRight; i >= 0; i--) {
                if (a.charAt(i) == '1') {
                    if (carry > 0) {
                        sb.append("0");
                    } else {
                        sb.append("1");
                    }
                } else if (a.charAt(i) == '0') {
                    if (carry > 0) {
                        sb.append("1");
                        carry = 0;
                    } else {
                        sb.append("0");
                    }
                }
            }
        }

        if (carry > 0) {
            sb.append("1");
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //String a = reader.readLine();
            //String b = reader.readLine();

            String a = "1101110101000001000100101000100101100010101100001111001101011111111";
            String b = "10101001011000011110101111101000101111110111110011000101111000011000101001000010101000";

            System.out.println(sumOfBinaries(a, b));
            System.out.println("10101001011000100000011110010000111000011100110111110010001101111010100010101110100111");
        }
    }
}
