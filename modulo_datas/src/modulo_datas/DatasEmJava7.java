package modulo_datas;

import java.time.LocalDate;
import java.time.Period;




public class DatasEmJava7 {

	public static void main(String[] args) {
		
		LocalDate dataAntiga = LocalDate.of(2020, 2, 7);
		
		LocalDate dataNova = LocalDate.of(2023, 2, 12);
		
		System.out.println("Data antiga é maior que a data nova? " + dataAntiga.isAfter(dataNova));
		
		System.out.println("Data antiga é anterior à data nova? " + dataAntiga.isBefore(dataNova));
		
		System.out.println("Data antiga é igual à data nova? " + dataAntiga.isEqual(dataNova));
		
		System.out.println("====================================");
		
		Period periodo = Period.between(dataAntiga, dataNova);
		
		System.out.println("Data antiga -> " + dataAntiga);
		System.out.println("Data antiga -> " + dataNova);
		System.out.println("Quantos dias entre as datas -> " + periodo.getDays());
		System.out.println("====================================");
		
		System.out.println("Data antiga -> " + dataAntiga);
		System.out.println("Data antiga -> " + dataNova);
		System.out.println("Quantos meses entre as datas -> " + periodo.getMonths());
		System.out.println("=====================================");
		
		System.out.println("Data antiga -> " + dataAntiga);
		System.out.println("Data antiga -> " + dataNova);
		System.out.println("Quantos anos entre as datas -> " + periodo.getYears());
		System.out.println("=====================================================");
		
		System.out.println("O período entre as datas é " + periodo.getYears() + " anos " + 
							periodo.getMonths() + " meses " + periodo.getDays() + " e dias");

	}

}
