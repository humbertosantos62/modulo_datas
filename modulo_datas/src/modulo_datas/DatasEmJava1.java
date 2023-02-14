package modulo_datas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatasEmJava1 {

	public static void main(String[] args) throws ParseException {
		
		Calendar calendar = Calendar.getInstance();//Pega a data atual
		
		/*Simular que a data vem de um banco de dados*/
		
		calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("10-01-2023"));//Definindo uma data qualquer
		
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		System.out.println("Somando o dia do mês: " + new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
		
		calendar.add(Calendar.MONTH, 5);
		System.out.println("Somando o mês: " + new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
		
		calendar.add(Calendar.YEAR, 1);
		System.out.println("Somando o ano: " + new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));

	}

}
