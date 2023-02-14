package modulo_datas;

import java.time.LocalDate;

public class DatasEmJava8 {

	public static void main(String[] args) {
		
		LocalDate dataBase = LocalDate.parse("2022-10-05");
		
		System.out.println("Data inícial -> " + dataBase);
		System.out.println("Data inícial + 5 dias -> " + (dataBase = dataBase.plusDays(5)));
		System.out.println("==========================================");
		
		System.out.println("Data + 5 semanas -> " + (dataBase = dataBase.plusWeeks(5)));
		System.out.println("==========================================");
		
		System.out.println("Data + 2 meses -> " + (dataBase = dataBase.plusMonths(2)));
		System.out.println("==========================================");

		System.out.println("Data + 4 anos -> " + (dataBase = dataBase.plusYears(4)));
		System.out.println("==========================================");
		
		System.out.println("Data - 1  ano -> " + (dataBase = dataBase.minusYears(1)));
		System.out.println("==========================================");
		
		System.out.println("Data - 2 meses -> " + (dataBase = dataBase.minusMonths(2)));
		System.out.println("==========================================");
		
		System.out.println("Data - 20 dias -> " + (dataBase = dataBase.minusDays(20)));
		System.out.println("==========================================");

	}

}
