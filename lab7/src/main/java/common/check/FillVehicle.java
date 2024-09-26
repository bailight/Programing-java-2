package common.check;

import common.datamodule.*;
import common.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class FillVehicle extends Fill<Vehicle>{


    public Vehicle build() throws InvalidInput, IOException, IllegaValue {
        return new Vehicle(readName(), readCoordinates(), LocalDate.now(), readEnginePower(), readCapacity(), readDistanceTravelled(), readFuelType());
    }

    private final int MAX_X = 787;

	public static boolean checkID(ArrayList<Vehicle> vehicles, String id){
        for (Vehicle e : vehicles) {
            String uid = String.valueOf(e.getID());
            if (!uid.equals("0") & uid.equals(id)) {
                return true;
            }
        }
        return false;
    }

	public static boolean isNumeric(String str) {
	    if (str == null || str.length() == 0) {
	        return false;
	    }
	    for (int i = 0; i < str.length(); i++) {
	        if (!Character.isDigit(str.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}

	public static boolean isDouble(String str) {
	    if (str == null || str.length() == 0) {
	        return false;
	    }
	    Object obj = str;
	    return obj instanceof Double;
	}

	public int readX() throws IOException, IllegaValue, NumberFormatException{
		while (true) {
            System.out.println("Введите координату x: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String text = reader.readLine();

			if (!isNumeric(text)) {
				IllegaValue e = new IllegaValue("Назначение должно быть числа.");
				System.out.println(e.getMessage());
			}else {
				int x = Integer.parseInt(text);
				if (x >= MAX_X) {
					IllegaValue e = new IllegaValue("Назначение должно быть меньше " + MAX_X);
					System.out.println(e.getMessage());
		        }else {
		        	return x;
		        }
			}
		}
    }

	public Long readY() throws IOException, IllegaValue, NumberFormatException{
		while (true) {
			System.out.println("Введите координату y: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String text = reader.readLine();
			if (!isNumeric(text)) {
				IllegaValue e = new IllegaValue("Назначение должно быть числа.");
				System.out.println(e.getMessage());
			}else{
				Long y = Long.parseLong(text);
				if (y.equals(null)) {
					IllegaValue e = new IllegaValue("Назначие нельзя быть null");
					System.out.println(e.getMessage());
		        }else {
		        	return y;
		        }
			}
		}
    }

	public Coordinates readCoordinates() throws IOException, IllegaValue {
		Coordinates cool = new Coordinates(readX(),readY());
        return cool;
    }

	public String readName() throws IOException, IllegaValue {
		while (true) {
			System.out.println("Введите имя: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String name = reader.readLine();
			if(name == null | name.isEmpty()) {
				IllegaValue e = new IllegaValue("Назначие нельзя быть null или пустой");
				System.out.println(e.getMessage());
		    }else {
		    	return name;
		    }
		}
    }

	public Float readEnginePower() throws IOException, IllegaValue, InvalidInput {
		while (true) {
			System.out.println("Введите мощность двигателя: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String text = reader.readLine();
			if (!isNumeric(text)) {
				IllegaValue e = new IllegaValue("Назначие должно быть числа");
				System.out.println(e.getMessage());
			}else {
				Float enginePower = Float.parseFloat(text);
				if(enginePower <= 0) {
					IllegaValue e = new IllegaValue("Назначие должно быть больше 0");
					System.out.println(e.getMessage());
				}
				else {
					return enginePower;
			    }
			}
		}
    }

	public double readCapacity() throws IOException, IllegaValue, InvalidInput{
		while (true) {
			System.out.println("Введите ёмкость двигателя: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String text = reader.readLine();
			if (!isNumeric(text)) {
				IllegaValue e = new IllegaValue("Назначение должно быть числа.");
				System.out.println(e.getMessage());
			}else{
				double capacity = Double.parseDouble(text);
				if(capacity <= 0) {
					IllegaValue e = new IllegaValue("Назначие должно быть больше 0");
					System.out.println(e.getMessage());
				}else {
					return capacity;
				}
			}
		}
    }

	public float readDistanceTravelled() throws IOException, IllegaValue, InvalidInput {
		while (true) {
			System.out.println("Введите пройденное расстояние двигателя: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String text = reader.readLine();
			if (!isNumeric(text)) {
				IllegaValue e = new IllegaValue("Назначение должно быть числа.");
				System.out.println(e.getMessage());
			}else {
				float  distanceTravelled = Float.parseFloat(text);
				if(distanceTravelled <= 0) {
					IllegaValue e = new IllegaValue("Назначие должно быть больше 0");
					System.out.println(e.getMessage());
				}else {
					return distanceTravelled;
				}
			}
		}
    }

	public FuelType readFuelType() throws IOException, IllegaValue, InvalidInput {
		while (true) {
			System.out.println("Тип топлива\n"
					+ "1-АЛКОГОЛЬ\n"
					+ "2-АНТИМАТЕРИЯ\n"
					+ "3-КЕРОСИН\n"
					+ "4-ЧЕЛОВЕЧЕСКАЯ СИЛА\n"
					+ "Выберите тип топлива: ");
			BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
			String text = reader.readLine();
			if (!isNumeric(text)) {
				IllegaValue e = new IllegaValue("Назначение должно быть числа.");
				System.out.println(e.getMessage());
			}else {
				int fuelType = Integer.parseInt(text);
				FuelType fuel = null;
				if(fuelType == 1) {
					fuel = FuelType.ALCOHOL;
				}else if(fuelType == 2) {
					fuel = FuelType.ANTIMATTER;
				}else if(fuelType == 3) {
					fuel = FuelType.KEROSENE;
				}else if(fuelType >= 4) {
					fuel = FuelType.MANPOWER;
				}
				return fuel;
			}
		}
    }

}
