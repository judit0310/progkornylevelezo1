package hu.uni.eszterhazy.exceptions;

public class NemJoChipSzam extends Throwable {
    public NemJoChipSzam(String chip) {
        super("A hibas chip szam: "+chip);
    }
}
