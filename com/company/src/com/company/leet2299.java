package com.company;

class leet2299 {
    public boolean strongPasswordChecker(String password) {
        if (password.length() < 8) {
            return false;
        }
        char[] cc = password.toCharArray();
        return cc.length > 7
                && hasTowSameWord(cc)
                && hasLowerWord(cc)
                && hasUpperWord(cc)
                && hasSpeWord(cc)
                && hasNumWord(cc);
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
        for (char aChar : chars) {
            if (aChar >= 'a' && aChar <= 'z') {
                return true;
            }
        }
        return false;
    }

    public boolean hasUpperWord(char[] chars) {
        for (char aChar : chars) {
            if (aChar >= 'A' && aChar <= 'Z') {
                return true;
            }
        }
        return false;
    }

    public boolean hasSpeWord(char[] chars) {
        final String spe = "!@#$%^&*()-+";
        char[] spes = spe.toCharArray();
        for (char aChar : chars) {
            for (char c : spes) {
                if (aChar == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasNumWord(char[] chars) {
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                return true;
            }
        }
        return false;
    }
}
