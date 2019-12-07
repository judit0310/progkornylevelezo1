package hu.uni.eszterhazy.service;

import hu.uni.eszterhazy.dao.ICicaDAO;
import hu.uni.eszterhazy.exceptions.CicaNotFound;
import hu.uni.eszterhazy.exceptions.EzAChipMarSzerepel;
import hu.uni.eszterhazy.model.Cica;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class CicaService {
    ICicaDAO dao;

    public CicaService(ICicaDAO dao) {
        this.dao = dao;
    }

    public void addCica(Cica cica) throws IOException, EzAChipMarSzerepel {
        dao.addCica(cica);
    }

    public Collection<Cica> listCicak() throws IOException {
        return dao.readAllCica();

    }

    public Cica getCicaByChip(String chip) throws CicaNotFound {
        return dao.readCicaById(chip);
    }

    public Collection<Cica> getCicaBetweenKor(int min, int max) throws IOException {
        Collection<Cica> cicak = listCicak();
        Collection<Cica> result = new ArrayList<>();
        for (Cica c : cicak) {
            if (c.getKor() >= min && c.getKor() <= max) {
                result.add(c);
            }

        }
        return result;
    }

    public Collection<Cica> getCicaByNev(String nev) throws IOException {
        Collection<Cica> cicak = listCicak();
        Collection<Cica> result = new ArrayList<>();
        for (Cica c : cicak
        ) {
            if (c.getNev().toLowerCase().contains(nev.toLowerCase())) {
                result.add(c);
            }
        }
        return result;

    }

    public Collection<Cica> getCicaDueDateToUjraOltas(int gyakorisag_evben) throws IOException {
        Collection<Cica> cicak = listCicak();
        Collection<Cica> result = new ArrayList<>();
        for (Cica c : cicak
        ) {
            if (c.getOltas_ideje().plusYears(gyakorisag_evben).isBefore(LocalDate.now())) {
                result.add(c);
            }
        }
        return result;
    }
}
