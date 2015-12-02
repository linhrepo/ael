package com.vn.ael.constants;

public enum VoucherType {
	PHIEUCHI(0), PHIEUTHU(1);

    private final int value;
    private VoucherType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}