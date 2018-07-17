package app.jm.funcional.model.entidades.vendas;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import app.jm.funcional.model.Dispositivo;
import app.jm.funcional.model.Tabela;

public class HorariosCaixa extends Tabela {
	
	private String diaSemana;
	private Time abertura;
	private Time encerramento;
	
	@Override
	public long getId() {

		return id;
	}
	

	@Override
	public boolean getPrecisaRegistroInicial() {
		return true;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Time getAbertura() {
		return abertura;
	}

	public void setAbertura(Time abertura) {
		this.abertura = abertura;
	}

	public Time getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(Time encerramento) {
		this.encerramento = encerramento;
	}
	
	@Override
	public boolean isInsertSomenteDispositivos() {
		// TODO Auto-generated method stub
		return true;
	}
		
	@Override
	public List<Tabela> getListValoresIniciais() {
		List<Tabela> horarios = new ArrayList<>();
		HorariosCaixa horarioDomingo = new HorariosCaixa();
		HorariosCaixa horarioSegunda = new HorariosCaixa();
		HorariosCaixa horarioTerca = new HorariosCaixa();
		HorariosCaixa horarioQuarta = new HorariosCaixa();
		HorariosCaixa horarioQuinta  = new HorariosCaixa();
		HorariosCaixa horarioSexta = new HorariosCaixa();
		HorariosCaixa horarioSabado = new HorariosCaixa();
		
		Time inicioPadrao = new Time(00, 00, 00);
		Time fimPadrao = new Time(23, 59, 59);
		
		horarioDomingo.setId(1);
		horarioDomingo.setDiaSemana("Domingo");
		horarioDomingo.setAbertura(inicioPadrao);
		horarioDomingo.setEncerramento(fimPadrao);
		horarios.add(horarioDomingo);
		
		horarioSegunda.setId(2);
		horarioSegunda.setDiaSemana("Segunda-Feira");
		horarioSegunda.setAbertura(inicioPadrao);
		horarioSegunda.setEncerramento(fimPadrao);
		horarios.add(horarioSegunda);
	
		horarioTerca.setId(3);
		horarioTerca.setDiaSemana("Terca-Feira");
		horarioTerca.setAbertura(inicioPadrao);
		horarioTerca.setEncerramento(fimPadrao);
		horarios.add(horarioTerca);
		
		horarioQuarta.setId(4);
		horarioQuarta.setDiaSemana("Quarta-Feira");
		horarioQuarta.setAbertura(inicioPadrao);
		horarioQuarta.setEncerramento(fimPadrao);
		horarios.add(horarioQuarta);
		
		horarioQuinta.setId(5);
		horarioQuinta.setDiaSemana("Quinta-Feira");
		horarioQuinta.setAbertura(inicioPadrao);
		horarioQuinta.setEncerramento(fimPadrao);
		horarios.add(horarioQuinta);
		
		horarioSexta.setId(6);
		horarioSexta.setDiaSemana("Sexta-Feira");
		horarioSexta.setAbertura(inicioPadrao);
		horarioSexta.setEncerramento(fimPadrao);
		horarios.add(horarioSexta);
		
		horarioSabado.setId(7);
		horarioSabado.setDiaSemana("Sabado");
		horarioSabado.setAbertura(inicioPadrao);
		horarioSabado.setEncerramento(fimPadrao);
		horarios.add(horarioSabado);

		return horarios;
	}
	

	@Override
	public Type typeParaJson() {
		return new TypeToken<List<HorariosCaixa>>(){}.getType();
	}




	

}
