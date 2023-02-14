package modulo_datas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DatasEmJava4 {

	public static void main(String[] args) {
		
		/*Nova API de data a partir do java 8*/
		
		LocalDate dataAtual = LocalDate.now();
		
		System.out.println("Data atual -> " + dataAtual);
		
		LocalTime horaAtual = LocalTime.now();
		System.out.println("Hora atual -> " + horaAtual.format(DateTimeFormatter.ofPattern(" hh:mm:ss")));
		
		LocalDateTime dataAtualHoraAtual = LocalDateTime.now();
		System.out.println("Data e hora atual -> " + dataAtualHoraAtual.format(DateTimeFormatter.ISO_DATE));
		System.out.println("Data e hora atual -> " + 
							dataAtualHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));

	}

}
