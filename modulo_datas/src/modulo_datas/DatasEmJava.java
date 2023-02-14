package modulo_datas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

public class DatasEmJava {

	public static void main(String[] args) throws ParseException {
		
		/*Calendar calendar = Calendar.getInstance();
		
		Date date = new Date();
		
		System.out.println("Data em milisegundos " + date.getTime());
		System.out.println("Calendar em milisegundos: " + calendar.getTimeInMillis());
		
		System.out.println("Dia do mês " + date.getDate());
		System.out.println("Calendar dia do mês; " + calendar.get(Calendar.DAY_OF_MONTH));
		
		System.out.println("Dia da semana " + date.getDay());
		System.out.println("Calendar do dia da semana " + (calendar.get(Calendar.DAY_OF_WEEK) - 1));
		
		System.out.println("Calendar Ano: " + calendar.get(Calendar.YEAR));*/
		
		/*-------------------------------------------------*/
		
		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Data atual em formato padrão e String: " + simpleDateFormat.format(date));
		System.out.println("Calendar Data atual em formato padrão e String: " + simpleDateFormat.format(calendar.getInstance().getTime()) );
		
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Data atual em formato para banco de dados: " + simpleDateFormat.format(date));
		
		System.out.println("Objeto Date: "+ simpleDateFormat.parse("2023-02-12")); */
				
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataVencimentoPrestacao = simpleDateFormat.parse("10/02/2024"); 
		
		Date dataAtualHoje = simpleDateFormat.parse("13/02/2023"); 
		
		if(dataVencimentoPrestacao.after(dataAtualHoje)) { //POSTERIOR OU MAIOR ou depois da data atual
			
			
			System.out.println("Prestação não vencida");
		}else {
			System.out.println("Prestação vencida - URGENTE");
		}
		
		
		if(dataVencimentoPrestacao.before(dataAtualHoje)) { //POSTERIOR OU MAIOR ou depois da data atual
			
			System.out.println("Prestação vencida - URGENTE");
		}else {
		}
		System.out.println("Prestação não vencida");
	}

}
