package com.vodafone.eatwithrandom.utils;

public final class Validators {
    public static final String EMAIL_PATTERN_VF = "(\\W|^)[\\w.+\\-]*@vodafone\\.com(\\W|$)";
    public static final String EMAIL_PATTERN_CORP = "(\\W|^)[\\w.+\\-]*@corp\\.vodafone\\.com(\\W|$)";

    private Validators() {
        // Nothing to do
    }
}