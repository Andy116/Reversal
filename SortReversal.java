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
			System.out.println("�� ������ ���� � �����!");
			System.out.println("��������� ������� ���������: ");
			System.out.println("- java SortBytes \"��� �������� �����(�����������)\" \"��� ��������� �����(�������������)\" \"��� ���������(byte/bit - �������������)\"");
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
					System.out.println("������� ���� �� ������!");
				else
					selectReversalType(typeReversal, inputPath);
			} finally {
				try {
					if (fileInput != null)
						fileInput.close();
				} catch (IOException e) {
					System.out.println("������ �������� �������� �����.");
				}
				try {
					if (fileOutput != null)
						fileOutput.close();
				} catch (IOException e) {
					System.out.println("������ �������� ��������� �����");
				}
			}
		} catch (IOException e) {
			System.out.println("������ �����-������: " + e);
		}
	}

	private static void selectReversalType(String typeReversal, String inputPath) throws IOException {
		if (typeReversal.equals("byte")) {
			System.out.println("��� ���������: " + typeReversal);
			sortBytes(inputPath);
		}
//		else if(typeReversal.equals("bit")) {
//			System.out.println("��� ���������: " + typeReversal);
//			sortBits(inputPath);
//		}
		else {
			System.out.println("��� ��������� �� ������/������ �� ���������, ����� �������� ��� ��������� �� ���������: byte.");
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
			System.out.println("�������� ���� �� ������, ����� ����������� ������� ����.");
			fileOutput = new FileOutputStream(inputPath);
			for (Integer x : list)
				fileOutput.write(x);
			System.out.println("���������� ���������");
		} else {
			System.out.println("�������� ���� ������, ������ ������.");
			for (Integer x : list)
				fileOutput.write(x);
			System.out.println("������ ���������.");
		}
	}
}
