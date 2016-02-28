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

		Player me = new Player(name, 100, 1);
		//need a way to increment player stats with each subsequent level
		//player accuracy++; something like that with every combat loop
		println(me.getPlayerInfo());
		System.out.println("Welcome to Mech Warrior: DAUNTLESS ASSAULT!");
		System.out.println();
		System.out.println("Your Objective is to survive as many waves of enemies as you can!, 
		if your hitpoints reach 0, you will be destroyed");

		//Welcome stuff
		//Explain global options (help menu)
		println("Press any key to continue...(Start Game)");
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
		println("Hostile MECH detected, uploading combat profile...");
		println();
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
				System.out.println("Firing Laser Cannons...");
				System.out.println();
			}
			//Missle
			else if (input.equalsIgnoreCase("M")) {
				inputIsValid = true;
				weapon = new MissilePod();
				me.setCurrentWeapon(weapon);
				System.out.println("Firing Missile...");
				System.out.println();
			}
			//Auto-Cannon
			else if (input.equalsIgnoreCase("A")) {
				inputIsValid = true;
				weapon = new AutoCannon();
				me.setCurrentWeapon(weapon);
				System.out.println("Firing Auto-Cannon...");
				System.out.println();
			}
			//Disengage
			else if (input.equalsIgnoreCase("D")) {					
				inputIsValid = true;
			}
			else {
				println("Invalid WEAPON CHOICE select (A) (M) (L) (D)");
				input = processUserInput();
			}
		}
		int fireDamage = me.getCurrentWeapon().fireWeapon(me.getPlayerAccuracy());
		System.out.println("Damage dealt:"+fireDamage);
		enemy.setMechHitpoints(enemy.getMechHitpoints()-fireDamage);
	}
	
	public void mechAttack(Player me, MK1Factory enemy){
		int fireDamage = enemy.attack();
		System.out.println("Damage sustained:"+fireDamage);
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
