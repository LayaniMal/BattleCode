
import java.lang.*;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;

public class Codians extends CharlieBot {
	int dist = 50;

	public void run() {

		setBodyColor(Color.red);
		setGunColor(Color.orange);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.red);


		while (true) {
			ahead(500);
			turnGunRight(180);
			scan();
			//turnRight(180);
			turnGunRight(180);
			back(100);
			//turnGunRight(5);
			
			//turnGunRight(360);
			scan();
			
		}
	}


	public void onRobotDetected(ScannedRobotEvent e) {
		 
		//if(e.getHeading()==0){
		//ahead(100);
		//turnGunLeft(Math.tan(e.getDistance()/100));
		//}
		//turnGunLeft(e.getBearing());
	double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing + (getHeading()-getRadarHeading()));

		// If it's close enough, fire!
		if (e.getDistance() < 50 && getEnergy() > 50) {
		turnGunRight(bearingFromGun-180);
			fire(3);
		}
		else {
		turnGunRight(bearingFromGun-180);
			fire(1);
			run();
		}
	
		if (bearingFromGun == 0) {
			scan();
		}		

		if (e.getDistance() < 50 && getEnergy() > 50) {
			fire(3);
		} // otherwise, fire 1.
		else {
			fire(1);
			
		}
		//getEnergy(100.0);
	
		scan();
		
	}


	public void onHitByBullet(HitByBulletEvent e) {
		//turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
		
			double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getHeading());
		//ahead(dist);
		//dist *= -1;
	//	double angle=e.getBearing();		
		turnRight(bearingFromGun);
		//ahead(100);
		//turnGunRight(90- angle);
			if ( getEnergy() > 50) {
			fire(3);
			turnRight(90+absoluteBearing);
			ahead(50);
		} // otherwise, fire 1.
		else {
			fire(1);
			turnRight(90+absoluteBearing);
			ahead(50);
		}
		scan();
	}


	public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
	
	}
	public void onHitWall(HitWallEvent e){
	double hit=normalRelativeAngleDegrees(e.getBearing());
	if(Math.abs(hit)>90){
		turnRight(hit);
		ahead(100);
		
		
	}else if(hit==0){ 
		turnRight(90);
		ahead(100);
	}
	
	}


}
