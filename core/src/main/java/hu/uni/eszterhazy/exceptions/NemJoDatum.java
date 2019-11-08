package hu.uni.eszterhazy.exceptions;

import java.time.LocalDate;

public class NemJoDatum extends Throwable {
    public NemJoDatum(LocalDate oltas_ideje) {
        super(oltas_ideje.toString());
    }
}
