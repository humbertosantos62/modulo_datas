package modulo_datas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatasEmJava3 {

	public static void main(String[] args) throws ParseException {
		
		Date dataInicial =  new SimpleDateFormat("dd/MM/yyyy").parse("10/02/2023");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInicial);
		
		for(int prestacao = 1; prestacao <= 12; prestacao++) {
			calendar.add(Calendar.MONTH, 1);
			
			System.out.println("Prestação número " + prestacao + " vencimento em -> " +
						new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
		}

	}

}
