package com.company;

class leet2299 {
    public boolean strongPasswordChecker(String password) {
        if (password.length() < 8) {
            return false;
        }
        char[] chars = password.toCharArray();
        return cc.length > 7 && hasTowSameWord(cc) && hasLowerWord(cc) && hasUpperWord(cc) && hasSpeWord(cc) && hasNumWord(cc);
    }

    public boolean hasTowSameWord(char[] chars) {
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean hasLowerWord(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                return true;
            }
        }
        return false;
    }

    public boolean hasUpperWord(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                return true;
            }
        }
        return false;
    }

    public boolean hasSpeWord(char[] chars) {
        final String spe = "!@#$%^&*()-+";
        char[] spes = spe.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < spes.length; j++) {
                if (chars[i] == spes[j]) {
                    return true;
                }
            }
        }
            return false;
    }

    public boolean hasNumWord(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                return true;
            }
        }
        return false;
    }
}
