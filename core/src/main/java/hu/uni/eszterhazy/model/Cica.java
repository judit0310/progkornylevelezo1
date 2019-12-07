package hu.uni.eszterhazy.model;

import hu.uni.eszterhazy.exceptions.NemJoChipSzam;
import hu.uni.eszterhazy.exceptions.NemJoDatum;
import hu.uni.eszterhazy.exceptions.NemJoKor;
import hu.uni.eszterhazy.exceptions.NemJoNev;

import java.time.LocalDate;
import java.util.Objects;

public class Cica {
    private int kor;
    private Szin szin;
    private String nev;
    private String gazda_neve;
    private LocalDate oltas_ideje;
    private String chip;
    private Nem nem;
    private boolean ivartalanitott;
    private String fajta;

    public Cica() {
    }

    public Cica(int kor, Szin szin, String nev, String gazda_neve,
                LocalDate oltas_ideje, String chip, Nem nem,
                boolean ivartalanitott, String fajta) throws NemJoNev, NemJoKor, NemJoDatum, NemJoChipSzam {
        setKor(kor);
        this.szin = szin;
        setNev(nev);
        setGazda_neve(gazda_neve);
        setOltas_ideje(oltas_ideje);
        setChip(chip);
        this.nem = nem;
        this.ivartalanitott = ivartalanitott;
        this.fajta = fajta;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) throws NemJoKor {
        if (kor < 0 || kor >= 40) {
            throw new NemJoKor();
        }
        this.kor = kor;
    }

    public Szin getSzin() {
        return szin;
    }

    public void setSzin(Szin szin) {
        this.szin = szin;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) throws NemJoNev {
        if (nev.isEmpty()) {
            throw new NemJoNev();
        }
        this.nev = nev;
    }

    public String getGazda_neve() {
        return gazda_neve;
    }

    public void setGazda_neve(String gazda_neve) throws NemJoNev {
        if (gazda_neve.isEmpty()) {
            throw new NemJoNev();
        }
        this.gazda_neve = gazda_neve;
    }

    public LocalDate getOltas_ideje() {
        return oltas_ideje;
    }

    public void setOltas_ideje(LocalDate oltas_ideje) throws NemJoDatum {
        if (oltas_ideje.isBefore(LocalDate.now().minusYears(this.kor)) ||
                oltas_ideje.isAfter(LocalDate.now())) {
        throw new NemJoDatum(oltas_ideje);
        }
        this.oltas_ideje = oltas_ideje;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) throws NemJoChipSzam {
        if(!chip.matches("[1-9]\\d{15}")){
            throw new NemJoChipSzam(chip);
        }
        this.chip = chip;
    }

    public Nem getNem() {
        return nem;
    }

    public void setNem(Nem nem) {
        this.nem = nem;
    }

    public boolean isIvartalanitott() {
        return ivartalanitott;
    }

    public void setIvartalanitott(boolean ivartalanitott) {
        this.ivartalanitott = ivartalanitott;
    }

    public String getFajta() {
        return fajta;
    }

    public void setFajta(String fajta) {
        this.fajta = fajta;
    }

    @Override
    public String toString() {
        return "Cica{" +
                "kor=" + kor +
                ", szin=" + szin +
                ", nev='" + nev + '\'' +
                ", gazda_neve='" + gazda_neve + '\'' +
                ", oltas_ideje=" + oltas_ideje +
                ", chip='" + chip + '\'' +
                ", nem=" + nem +
                ", ivartalanitott=" + ivartalanitott +
                ", fajta='" + fajta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cica cica = (Cica) o;
        return kor == cica.kor &&
                ivartalanitott == cica.ivartalanitott &&
                szin == cica.szin &&
                Objects.equals(nev, cica.nev) &&
                Objects.equals(gazda_neve, cica.gazda_neve) &&
                Objects.equals(oltas_ideje, cica.oltas_ideje) &&
                Objects.equals(chip, cica.chip) &&
                nem == cica.nem &&
                Objects.equals(fajta, cica.fajta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kor, szin, nev, gazda_neve, oltas_ideje, chip, nem, ivartalanitott, fajta);
    }
}
