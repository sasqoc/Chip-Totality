package com.world.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public enum Upgrade {
	ptaahsClemency(4, 120), //base
	directConnection(6, 50),//base
	gorlojsWarmth(30, 50);	//holyMountains
	
	
	
	public boolean isBeingResearched;
	public boolean isAlreadyResearched;
	int researchTime;
	int cost;

	private Upgrade(int researchTime, int cost) {
		this.researchTime = researchTime;
		this.cost = cost;
	}

	
	
	static void scheduleResearch(final Upgrade upgrade, final Building building) {
		if (upgrade.isBeingResearched || upgrade.isAlreadyResearched || GameStateHolder.beings<upgrade.cost || building.isResearching==true) {
			Gdx.app.log("upgrade", "already is being or already researched/not enough beings/building is already researching something");
			return;
		}
		upgrade.isBeingResearched=true;
		building.isResearching=true;
		GameStateHolder.beings -= upgrade.cost;
		Timer.schedule(new Task() {

			@Override
			public void run() {		
				doUpgrade(upgrade, building);	
				
			}
		}, upgrade.researchTime);
	}
	
	
	private static void doUpgrade(Upgrade upgrade, Building building){	
		
		switch (upgrade) {
		case directConnection:
			Base.resourceDeliveryAmount += 10;
			break;
		case ptaahsClemency:		
			Base.maxHitpoints += 100;
			break;
		case gorlojsWarmth:
			Rafinery.efficiency++;
			break;
		default:
			return;
		}
		upgrade.isBeingResearched = false;
		upgrade.isAlreadyResearched = false;
		building.isResearching=false;
		
	}
	
	


}
