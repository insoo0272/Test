package com.example.testproject.domain.institute;

public enum InstituteCode {
    NHUF("주택도시기금", "public01"),
    KB("국민은행", "bank01"),
    WOORI("우리은행", "bank02"),
    SHINHAN("신한은행", "bank03"),
    KOREA_CITY("한국시티은행", "bank04"),
    HANA("하나은행", "bank05"),
    NH_SH("농협은행/수협은행", "bank06"),
    KEB("외환은행", "bank08"),
    ETC_BANK("기타은행", "bank99");

    private final String name;
    private final String code;

    InstituteCode(final String name, final String code) {
        this.name = name;
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean containsName(final String name) {
        return name.contains(this.name);
    }
}
