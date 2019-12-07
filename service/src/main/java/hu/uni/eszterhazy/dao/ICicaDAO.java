package hu.uni.eszterhazy.dao;

import hu.uni.eszterhazy.exceptions.CicaNotFound;
import hu.uni.eszterhazy.exceptions.EzAChipMarSzerepel;
import hu.uni.eszterhazy.model.Cica;

import java.io.IOException;
import java.util.Collection;

public interface ICicaDAO {
    public Collection<Cica> readAllCica() throws IOException;

    public void addCica(Cica cica) throws IOException, EzAChipMarSzerepel;

    public Cica readCicaById(String id) throws CicaNotFound;

}
