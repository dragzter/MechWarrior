package com.vlad.main;

import java.util.Scanner;

import com.vlad.mech.MK1Factory;
import com.vlad.mech.Player;
import com.vlad.weapons.AutoCannon;
import com.vlad.weapons.Laser;
import com.vlad.weapons.MissilePod;
import com.vlad.weapons.Weapon;

public class TestMain {

	public static void main(String[] args) {

		TestMain main = new TestMain();

		main.playGame();

	}

	public void playGame(){
		MK1Factory m = new MK1Factory(11,25);
		MK1Factory m2 = new MK1Factory(16,28);
		MK1Factory megaboss = new MK1Factory(30,100);
		int level = 1;

		println("What is your name?");
		String name = processUserInput();

		Player me = new Player(name, 100, 2);

		println(me.getPlayerInfo());
		//Welcome stuff
		//Explain global options (help menu)
		println("Press any key to continue...");
		processUserInput();
		MK1Factory enemy = findWarrior(level);
		println("How do you want to proceed?");
		int i=3;
		if (me.getInitiative()>enemy.getInitiative()){
			i=2;
		}else{
			i=3;
		}
		while (enemy.getMechHitpoints()>0 && me.getPlayerHitpoints()>0){
			//determine who attacks first
			//add attack loop
			if (i%2==0){
				playerAttack(me,enemy);
				println(enemy.getMechInfo());
			}
			else{
				mechAttack(me, enemy);
				println(me.getPlayerInfo());
			}
			i++;
			
		}
		println("Enemy Mech Destroyed!");


	}

	public MK1Factory findWarrior(int level){
		println("An enemy Mech approaches...");
		int hp = 40 + (level*10);
		int accuracy = 40 + (level*2);
		MK1Factory mechEnemy = new MK1Factory(hp, accuracy);
		println(mechEnemy.getMechInfo());
		System.out.println(mechEnemy.getInitiative());
		return mechEnemy;
	}
	
	public void playerAttack(Player me, MK1Factory enemy){
		String input = processUserInput();
		boolean inputIsValid = false;
		Weapon weapon;
		while (!inputIsValid){
			//Laser
			if (input.equalsIgnoreCase("L")){
				inputIsValid = true;
				weapon = new Laser();
				me.setCurrentWeapon(weapon);
			}
			//Missle
			else if (input.equalsIgnoreCase("M")) {
				inputIsValid = true;
				weapon = new MissilePod();
				me.setCurrentWeapon(weapon);
			}
			//Auto-Cannon
			else if (input.equalsIgnoreCase("A")) {
				inputIsValid = true;
				weapon = new AutoCannon();
				me.setCurrentWeapon(weapon);
			}
			//Disengage
			else if (input.equalsIgnoreCase("D")) {					
				inputIsValid = true;
			}
			else {
				println("Invalid input...Try again:");
				input = processUserInput();
			}
		}
		int fireDamage = me.getCurrentWeapon().fireWeapon(me.getPlayerAccuracy());
		System.out.println("Fire Damage:"+fireDamage);
		enemy.setMechHitpoints(enemy.getMechHitpoints()-fireDamage);
	}
	
	public void mechAttack(Player me, MK1Factory enemy){
		int fireDamage = enemy.attack();
		System.out.println("Damage taken:"+fireDamage);
		me.setPlayerHitpoints(me.getPlayerHitpoints()-fireDamage);
	}

	public String processUserInput(){
		Scanner sc = new Scanner(System.in);
		//Help command filter

		//Exit command filter

		//Print Stats

		return sc.next();
	}

	private void println(String s){
		System.out.println(s);
	}

}
