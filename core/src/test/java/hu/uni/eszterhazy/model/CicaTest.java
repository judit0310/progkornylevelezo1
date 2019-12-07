package hu.uni.eszterhazy.model;

import hu.uni.eszterhazy.exceptions.NemJoChipSzam;
import hu.uni.eszterhazy.exceptions.NemJoDatum;
import hu.uni.eszterhazy.exceptions.NemJoKor;
import hu.uni.eszterhazy.exceptions.NemJoNev;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CicaTest {

    @Test(expected = NemJoKor.class)
    public void setKorInvalidNegative() throws NemJoKor {
        int kor =-1;
        Cica c = new Cica();
        c.setKor(kor);
    }

    @Test(expected = NemJoKor.class)
    public void setKorInvalidTooMuch() throws NemJoKor {
        int kor =180;
        Cica c = new Cica();
        c.setKor(kor);
    }

    @Test
    public void setKorValid() throws NemJoKor {
        int kor = 4;
        Cica c = new Cica();
        c.setKor(kor);
    }


    @Test(expected = NemJoNev.class)
    public void setNevInvalid() throws NemJoNev {
        String nev = "";
        Cica c = new Cica();
        c.setNev(nev);
    }


    @Test
    public void setNevValid() throws NemJoNev {
        String nev="Cirmi";
        Cica c = new Cica();
        c.setNev(nev);
    }


    @Test
    public void setGazda_neve() {
    }

    @Test
    public void setOltas_idejeValid() throws NemJoKor, NemJoDatum {
        int kor=3;
        LocalDate date = LocalDate.of(2018, 2, 5);
        Cica c = new Cica();
        c.setKor(kor);
        c.setOltas_ideje(date);
    }

    @Test(expected = NemJoDatum.class)
    public void setOltas_idejeInvalidBeforeBornDate() throws NemJoKor, NemJoDatum {
        int kor=3;
        LocalDate date = LocalDate.of(2012, 2, 5);
        Cica c = new Cica();
        c.setKor(kor);
        c.setOltas_ideje(date);
    }

    @Test(expected = NemJoDatum.class)
    public void setOltas_idejeInvalidAfterToday() throws NemJoKor, NemJoDatum {
        int kor=3;
        LocalDate date = LocalDate.of(2020, 2, 5);
        Cica c = new Cica();
        c.setKor(kor);
        c.setOltas_ideje(date);
    }

    @Test(expected = NemJoChipSzam.class)
    public void setChipInvalidChipStartswithZero() throws NemJoChipSzam {
        String chip = "0123456789123456";
        Cica c = new Cica();
        c.setChip(chip);
    }

    @Test(expected = NemJoChipSzam.class)
    public void setChipInvalidChipLengthIsSmall() throws NemJoChipSzam {
        String chip = "3456";
        Cica c = new Cica();
        c.setChip(chip);
    }

    @Test(expected = NemJoChipSzam.class)
    public void setChipInvalidChipLetters() throws NemJoChipSzam {
        String chip = "aaaaaaaaaaaaaaa";
        Cica c = new Cica();
        c.setChip(chip);
    }

    @Test
    public void setChipValid() throws NemJoChipSzam {
        String chip = "1234567891234567";
        Cica c = new Cica();
        c.setChip(chip);
    }
}