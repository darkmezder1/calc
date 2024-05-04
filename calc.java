import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неправильный формат ввода");
        }

        int num1, num2;
        try {
            num1 = convert(parts[0]);
            num2 = convert(parts[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Недопустимые числа");
        }

        int result;
        switch (parts[1]) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль недопустимо");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Недопустимая операция");
        }

        if (isRoman(parts[0]) && isRoman(parts[2])) {
            return convertBack(result);
        } else {
            return String.valueOf(result);
        }
    }

    static int convert(String str) {
        Map<String, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);

        if (isRoman(str)) {
            return romanNumerals.get(str);
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Недопустимые числа");
            }
        }
    }

    static String convertBack(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Отрицательные числа и ноль недопустимы для римских цифр");
        }

        String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        if (number <= 10) {
            return romanNumerals[number - 1];
        } else if (number == 100) {
            return "C";
        } else {
            StringBuilder result = new StringBuilder();
            int remainder = number;
            while (remainder > 0) {
                if (remainder >= 100) {
                    result.append("C");
                    remainder -= 100;
                } else if (remainder >= 90) {
                    result.append("XC");
                    remainder -= 90;
                } else if (remainder >= 50) {
                    result.append("L");
                    remainder -= 50;
                } else if (remainder >= 40) {
                    result.append("XL");
                    remainder -= 40;
                } else if (remainder >= 10) {
                    result.append("X");
                    remainder -= 10;
                }
            }
            return result.toString();
        }
    }

    static boolean isRoman(String str) {
        return str.matches("^[IVXLCDM]+$");
    }
}