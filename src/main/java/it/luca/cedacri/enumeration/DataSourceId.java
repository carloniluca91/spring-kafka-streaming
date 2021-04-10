package it.luca.cedacri.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataSourceId {

    BANCLL_01("BANCLL_01"),
    BANCLL_34("BANCLL_34");

    private final String id;
}
