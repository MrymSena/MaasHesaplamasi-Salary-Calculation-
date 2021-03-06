import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MaasHesaplamasi {
	
//X liralık net maaş için ne kadarlık brüt maaş gerekir?
	
	double kesintiler;
	double vergiler;
	double netMaas;
	double brütMaas;
	long counter=0;
	
	ArrayList<Double>netMaaslar= new ArrayList<>();
	
	double[][] netMaasTahminAralığı= new double[1][2];
	
	public double netMaasHesapla(double brütMaas){
		kesintiler=brütMaas*0.1;
		vergiler=brütMaas*0.2;
		netMaas=brütMaas-(kesintiler+vergiler);
		return  netMaas;
	}
	
	public double brütMaasHesapla(double netMaas){
		counter++;
		netMaaslar.add(netMaas);
		double aralık=netMaas-netMaas*0.1;
		
		if(counter==1){
		
			netMaasTahminAralığı[0][0]=netMaaslar.get(0);
			netMaasTahminAralığı[0][1]=aralık;
		}
		brütMaas=netMaas*(1.5);
		double tahminNetMaas=netMaasHesapla(brütMaas);
		
		if(tahminNetMaas>netMaaslar.get(0)){
			
			if(counter>1){
				netMaasTahminAralığı[0][0]=netMaaslar.get((int)counter-1);
			}
			netMaas=ThreadLocalRandom.current().nextDouble(netMaasTahminAralığı[0][1],netMaasTahminAralığı[0][0]);
			return brütMaasHesapla(netMaas);
			
		}else if(tahminNetMaas<netMaaslar.get(0)){
				netMaasTahminAralığı[0][1]=netMaaslar.get((int)counter-1);
				netMaas=ThreadLocalRandom.current().nextDouble(netMaasTahminAralığı[0][1],netMaasTahminAralığı[0][0]);
				return brütMaasHesapla(netMaas);
				}
		
		return brütMaas;
	
	}
	
	public static void main(String[] args) {
		
		MaasHesaplamasi maasHesaplamasi = new MaasHesaplamasi();
		
		System.out.println(maasHesaplamasi.brütMaasHesapla(5.000));
	
	
	}

}
