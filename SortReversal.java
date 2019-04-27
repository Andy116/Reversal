import java.io.*;
import java.util.*;


public class SortReversal {
	private static FileInputStream fileInput;
	private static FileOutputStream fileOutput;
	private static String inputPath;
	private static String outputPath = "";
	private static String typeReversal = "nope";

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Не указан путь к файлу!");
			System.out.println("Параметры запуска программы: ");
			System.out.println("- java SortBytes \"имя входного файла(обязательно)\" \"имя выходного файла(необязательно)\" \"тип разворота(byte/bit - необязательно)\"");
			return;
		} else if (args.length == 1) {
			inputPath = args[0];
		} else if (args.length == 2) {
			inputPath = args[0];
			outputPath = args[1];
		} else if (args.length > 2) {
			inputPath = args[0];
			outputPath = args[1];
			typeReversal = args[2];
		}

		try {
			try {
				fileInput = new FileInputStream(inputPath);

				if (!new File(outputPath).exists())
					throw new FileNotFoundException();
				else {
					fileOutput = new FileOutputStream(outputPath);
					selectReversalType(typeReversal, null);
				}
			} catch (FileNotFoundException e) {
				if (fileInput == null)
					System.out.println("Входной файл не найден!");
				else
					selectReversalType(typeReversal, inputPath);
			} finally {
				try {
					if (fileInput != null)
						fileInput.close();
				} catch (IOException e) {
					System.out.println("Ошибка закрытия входного файла.");
				}
				try {
					if (fileOutput != null)
						fileOutput.close();
				} catch (IOException e) {
					System.out.println("Ошибка закрытия выходного файла");
				}
			}
		} catch (IOException e) {
			System.out.println("Ошибка ввода-вывода: " + e);
		}
	}

	private static void selectReversalType(String typeReversal, String inputPath) throws IOException {
		if (typeReversal.equals("byte")) {
			System.out.println("Тип разворота: " + typeReversal);
			sortBytes(inputPath);
		}
//		else if(typeReversal.equals("bit")) {
//			System.out.println("Тип разворота: " + typeReversal);
//			sortBits(inputPath);
//		}
		else {
			System.out.println("Тип разворота не указан/указан не правильно, будет выполнен тип разворота по умолчания: byte.");
			sortBytes(inputPath);
		}
	}

	private static void sortBytes(String inputPath) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (fileInput.available() != 0) {
			list.add(fileInput.read());
		}
		Collections.reverse(list);

		if (inputPath != null) {
			System.out.println("Выходной файл не найден, будет перезаписан входной файл.");
			fileOutput = new FileOutputStream(inputPath);
			for (Integer x : list)
				fileOutput.write(x);
			System.out.println("Перезапись выполнена");
		} else {
			System.out.println("Выходной файл найден, начало записи.");
			for (Integer x : list)
				fileOutput.write(x);
			System.out.println("Запись выполнена.");
		}
	}
}
