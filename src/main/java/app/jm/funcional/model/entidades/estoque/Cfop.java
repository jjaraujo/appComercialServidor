package app.jm.funcional.model.entidades.estoque;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.entidades.Entidade;

public class Cfop extends Entidade implements Serializable {
    private String nome_cfop;
    private Cst cst;
    private Csons csons;
    private TipoItem tipoItem;

    public String getNome_cfop() {
        return nome_cfop;
    }

    public void setNome_cfop(String nome_cfop) {
        this.nome_cfop = nome_cfop;
    }

    public Cst getCst() {
        return cst;
    }

    public void setCst(Cst cst) {
        this.cst = cst;
    }

    public Csons getCsons() {
        return csons;
    }

    public void setCsons(Csons csons) {
        this.csons = csons;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }


	@Override
	public Type typeParaJson() {
		return new TypeToken<List<Cfop>>(){}.getType();
	}
    
    @Override
    public String toString() {
        return id + " " + nome_cfop;
    }
}
