package hu.uni.eszterhazy.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.uni.eszterhazy.dao.ICicaDAO;
import hu.uni.eszterhazy.exceptions.CicaNotFound;
import hu.uni.eszterhazy.exceptions.EzAChipMarSzerepel;
import hu.uni.eszterhazy.model.Cica;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CicaDAO implements ICicaDAO {
    private File jsonfile;
    ObjectMapper mapper;

    Collection<Cica> cicak;

    public CicaDAO(String jsonfile) throws IOException {
        this.jsonfile = new File(jsonfile);
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        if(!this.jsonfile.exists()){
            this.jsonfile.createNewFile();
            FileWriter writer = new FileWriter(this.jsonfile);
            writer.write("[]");
            writer.close();
        }
        cicak=readAllCica();
    }

    public Collection<Cica> readAllCica() throws IOException {
        Collection<Cica> result = new ArrayList<>();
        TypeReference reference = new TypeReference<ArrayList<Cica>>(){};
        result = mapper.readValue(jsonfile,reference);
        return result;
    }

    public void addCica(Cica cica) throws IOException, EzAChipMarSzerepel {
        //Megnezni van e mar benne ilyen
        try {
            readCicaById(cica.getChip());
            throw new EzAChipMarSzerepel(cica.getChip());
        } catch (CicaNotFound cicaNotFound) {
            this.cicak.add(cica);
            mapper.writeValue(jsonfile, this.cicak);
        }

    }

    public Cica readCicaById(String id) throws CicaNotFound {
        System.out.println(this.cicak);
        for (Cica c: this.cicak) {
            if (c.getChip().equalsIgnoreCase(id)) {
                return c;
            }

        }
        throw new CicaNotFound(id);

    }

    protected void clearDB(){
        try {
            mapper.writeValue(jsonfile, new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
