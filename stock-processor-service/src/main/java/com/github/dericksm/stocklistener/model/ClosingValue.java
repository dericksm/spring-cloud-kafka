package com.github.dericksm.stocklistener.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClosingValue {
    UP("UP"),
    DOWN("DOWN"),
    NEUTRAL("NEUTRAL");

    private String value;
}
