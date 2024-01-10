package controller.verificacoes;

import java.time.LocalDate;
import java.time.LocalTime;

import controller.exceptions.ExcecaoConversaoToInteger;
import controller.exceptions.ExcecaoDataHora;

public class Verificacoes {
	public void ehInteiro(String valor) throws ExcecaoConversaoToInteger{
		if(!valor.matches("[0-9]+")) {
			throw new ExcecaoConversaoToInteger("Conversão para inteiro falhou!");
		}
	}
	public void ehHoraValida(LocalTime hora) throws ExcecaoDataHora {
		System.out.println(hora.toString());
		if(!hora.toString().matches("^([01]\\d|2[0-3]):([0-5]\\d)$")){
			throw new ExcecaoDataHora("Formato de hora incorreto!");
		}
	}
	
	public void ehDataValida(LocalDate date) throws ExcecaoDataHora {
		String pattern;
		switch(date.getMonth()) {
		case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER:
			pattern = "^((19|20)\\d{2}|2023)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
			break;
		case FEBRUARY:
			if(date.isLeapYear()) pattern = "^((19|20)\\d{2}|2023)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9])$";
			else pattern = "^((19|20)\\d{2}|2023)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-8])$";
			break;
		default:
			pattern = "^((19|20)\\d{2}|2023)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0])$";
			break;
		
		}
		String data = date.toString();
		if( !data.matches(pattern)){
			throw new ExcecaoDataHora("Formato de data incorreto!");
		}

	}
	
	public String ehDiaMenorQueDez(String dia) {
		if(Integer.parseInt(dia) < 10) { return "0" + dia;}
		return dia;
	}
	
	public String ehMesMenorQueDez(String mes) {
		if(Integer.parseInt(mes) < 10) { return "0" + mes;}
		return mes;
	}
}
