import java.util.ArrayList;

public class LevelInfo {
	public static Point getStarting(int level) {
		switch (level) {
			case 1:
				return new Point(300, 450);
			case 2:
				return new Point(200, 700);
			case 3:
				return new Point(200, 700);
			case 4:
				return new Point(200, 700);
			case 5: 
				return new Point(50, 800);
			case 6:
				return new Point(200, 450);
			case 7:
				return new Point(300, 700);
			case 8:
				return new Point(100, 450);
			case 9:
				return new Point(150, 650);
			case 10:
				return new Point(100, 650);
			case 11:
				return new Point(200, 200);
			case 12:
				return new Point(200, 450);
			case 13:
				return new Point(75, 450);
			case 14:
				return new Point(100, 825);
			case 15:
				return new Point(150, 800);
			case 16:
				return new Point(75, 825);
			case 17:
				return new Point(100, 700);
			case 18:
				return new Point(150, 750);
			case 19:
				return new Point(100, 800);
			case 20:
				return new Point(200, 450);
		}
		
		return new Point(300, 450);
	}
	
	public static ArrayList<Tank> getTanks(int level) {
		ArrayList<Tank> tanks = new ArrayList<Tank>();
		switch (level) {
			case 1:
				tanks.add(new BrownTank(1300, 450, null));
				break;
			case 2:
				tanks.add(new GreyTank(1400, 200, null));
				break;
			case 3:
				tanks.add(new GreyTank(100, 100, null));
				tanks.add(new BrownTank(900, 400, null));
				tanks.add(new GreyTank(1300, 400, null));
				break;
			case 4:
				tanks.add(new GreyTank(800, 450, null));
				tanks.add(new GreyTank(1400, 200, null));
				tanks.add(new BrownTank(800, 200, null));
				tanks.add(new BrownTank(1400, 450, null));
				break;
			case 5:
				tanks.add(new TealTank(1200, 100, null));
				tanks.add(new TealTank(1500, 550, null));
				break;
			case 6:
				tanks.add(new GreyTank(1300, 200, null));
				tanks.add(new GreyTank(1300, 700, null));
				tanks.add(new TealTank(1300, 400, null));
				tanks.add(new TealTank(1300, 500, null));
				break;
			case 7:
				tanks.add(new TealTank(1400, 700, null));
				tanks.add(new TealTank(300, 375, null));
				tanks.add(new TealTank(300, 125, null));
				tanks.add(new TealTank(1400, 125, null));
				break;
			case 8: 
				tanks.add(new YellowTank(900, 200, null));
				tanks.add(new YellowTank(900, 700, null));
				tanks.add(new YellowTank(1500, 450, null));
				tanks.add(new TealTank(1575, 150, null));
				tanks.add(new TealTank(1575, 750, null));
				break;
			case 9:
				tanks.add(new YellowTank(200, 200, null));
				tanks.add(new BrownTank(650, 50, null));
				tanks.add(new BrownTank(1450, 200, null));
				tanks.add(new BrownTank(1000, 300, null));
				tanks.add(new BrownTank(1050, 850, null));
				tanks.add(new YellowTank(1450, 700, null));
				break;
			case 10:
				tanks.add(new RedTank(800, 100, null));
				tanks.add(new RedTank(1400, 200, null));
				break;
			case 11:
				tanks.add(new BrownTank(300, 800, null));
				tanks.add(new TealTank(450, 25, null));
				tanks.add(new RedTank(750, 450, null));
				tanks.add(new BrownTank(925, 100, null));
				tanks.add(new RedTank(1400, 300, null));
				tanks.add(new TealTank(1400, 850, null));
				break;
			case 12:
				tanks.add(new RedTank(850, 100, null));
				tanks.add(new RedTank(750, 800, null));
				tanks.add(new GreenTank(1400, 250, null));
				tanks.add(new GreenTank(1250, 850, null));
				break;
			case 13:
				tanks.add(new YellowTank(400, 75, null));
				tanks.add(new YellowTank(500, 550, null));
				tanks.add(new YellowTank(200, 850, null));
				tanks.add(new TealTank(1550, 75, null));
				tanks.add(new TealTank(1575, 450, null));
				tanks.add(new TealTank(1550, 825, null));
				break;
			case 14:
				tanks.add(new GreenTank(1500, 50, null));
				tanks.add(new RedTank(100, 400, null));
				tanks.add(new RedTank(800, 500, null));
				tanks.add(new RedTank(1200, 50, null));
				tanks.add(new GreenTank(1200, 500, null));
				tanks.add(new GreenTank(400, 400, null));
				break;
			case 15:
				tanks.add(new PurpleTank(500, 300, null));
				tanks.add(new PurpleTank(1500, 150, null));
				tanks.add(new PurpleTank(1500, 850, null));
				break;
			case 16:
				tanks.add(new GreenTank(600, 600, null));
				tanks.add(new PurpleTank(600, 75, null));
				tanks.add(new GreenTank(1550, 75, null));
				tanks.add(new PurpleTank(1550, 600, null));
				tanks.add(new PurpleTank(900, 450, null));
				break;
			case 17:
				tanks.add(new GreenTank(250, 100, null));
				tanks.add(new GreenTank(800, 450, null));
				tanks.add(new GreenTank(1500, 475, null));
				tanks.add(new GreenTank(1500, 250, null));
				tanks.add(new GreenTank(1450, 800, null));
				tanks.add(new GreenTank(100, 450, null));
				break;
			case 18:
				tanks.add(new TealTank(350, 150, null));
				tanks.add(new PurpleTank(1050, 150, null));
				tanks.add(new RedTank(575, 250, null));
				tanks.add(new GreenTank(800, 450, null));
				tanks.add(new TealTank(1450, 750, null));
				tanks.add(new PurpleTank(1050, 800, null));
				break;
			case 19:
				tanks.add(new PurpleTank(1300, 800, null));
				tanks.add(new PurpleTank(1500, 650, null));
				tanks.add(new PurpleTank(100, 450, null));
				tanks.add(new PurpleTank(700, 300, null));
				tanks.add(new PurpleTank(1450, 325, null));
				tanks.add(new PurpleTank(1400, 100, null));
				tanks.add(new PurpleTank(650, 100, null));
				tanks.add(new PurpleTank(100, 100, null));
				break;
			case 20:
				tanks.add(new WhiteTank(1450, 450, null));
				tanks.add(new WhiteTank(1300, 375, null));
				break;
		}
		return tanks;
	}
	
	public static ArrayList<Obstacle> getObstacles(int level) {
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		switch (level) {
			case 1:
				obstacles.add(new Obstacle(775, 350));
				obstacles.add(new Obstacle(775, 400));
				obstacles.add(new Obstacle(775, 450));
				obstacles.add(new Obstacle(775, 500));
				obstacles.add(new Obstacle(775, 550));
				break;
			case 2:
				for(int i = 300; i <= 1250; i += 50) {
					obstacles.add(new Obstacle(i, 300));
					obstacles.add(new Obstacle(i, 550));
				}
				break;
			case 3:
				for(int i = 225; i <= 775; i += 50) {
					obstacles.add(new Obstacle(i, 225));
				}
				for(int i = 275; i <= 625; i += 50) {
					obstacles.add(new Obstacle(775, i));
				}
				for(int i = 775; i <= 1325; i += 50) {
					obstacles.add(new Obstacle(i, 625));
				}
				break;
			case 4:
				break;
			case 5:
				obstacles.add(new Obstacle(125, 725));
				obstacles.add(new Obstacle(175, 725));
				obstacles.add(new Obstacle(175, 775));
				obstacles.add(new Obstacle(1425, 125));
				obstacles.add(new Obstacle(1425, 175));
				obstacles.add(new Obstacle(1475, 175));
				break;
			case 6:
				for(int i = 300; i <= 1300; i += 50) {
					obstacles.add(new Obstacle(i, 150));
					obstacles.add(new Obstacle(i, 750));
				}
				break;
			case 7:
				for(int i = 25; i <= 625; i += 50) {
					obstacles.add(new Obstacle(i, 250));
					obstacles.add(new Obstacle(i, 500));
				}
				for(int i = 975; i <= 1575; i += 50) {
					obstacles.add(new Obstacle(i, 300));
					obstacles.add(new Obstacle(i, 550));
				}
				break;
			case 8:
				for(int i = 250; i <= 650; i += 50) {
					obstacles.add(new Obstacle(250, i));
					obstacles.add(new Obstacle(1350, i));
				}
				for(int i = 650; i <= 950; i += 50) {
					obstacles.add(new Obstacle(i, 250));
					obstacles.add(new Obstacle(i, 650));
				}
				break;
			case 9:
				for(int i = 25; i <= 525; i += 50) {
					obstacles.add(new Obstacle(i, 450));
					obstacles.add(new Obstacle(1600 - i, 450));
				}
				for(int i = 200; i <= 1400; i += 50) {
					obstacles.add(new Obstacle(775, i));
					obstacles.add(new Obstacle(825, i));
				}
				break;
			case 10:
				for(int i = 150; i <= 500; i += 50) {
					for(int j = 150; j <= 750; j += 300) {
						obstacles.add(new Obstacle(i, j));
						obstacles.add(new Obstacle(1600-i, j));
					}
				}
				for(int i = 650; i <= 950; i += 50) {
					obstacles.add(new Obstacle(i, 300));
					obstacles.add(new Obstacle(i, 600));
				}
				break;
			case 11:
				for(int i = 25; i <= 625; i += 50) {
					obstacles.add(new Obstacle(350, i));
					obstacles.add(new Obstacle(1250, 900-i));
				}
				break;
			case 12:
				for(int i = 500; i <= 1100; i += 50) {
					obstacles.add(new Obstacle(i, (1200-i)));
				}
				break;
			case 13:
				for(int i = 150; i <= 750; i += 300) {
					for(int j = 200; j <= 700; j += 50) {
						obstacles.add(new Obstacle(j, i));
					}
				}
				break;
			case 14:
				for(int i = 25; i <= 1375; i += 50) {
					if(i != 325 && i != 425 && i != 525) {
						obstacles.add(new Obstacle(i, 750));
						obstacles.add(new Obstacle(1600-i, 150));
					}
				}
				for(int i = 350; i <= 750; i += 50) {
					obstacles.add(new Obstacle(1375, i));
					obstacles.add(new Obstacle(225, 900 - i));
				}
				break;
			case 15:
				for(int i = 300; i <= 550; i += 50) {
					obstacles.add(new Obstacle(i, 150));
					obstacles.add(new Obstacle(i, 450));
					obstacles.add(new Obstacle(1600-i, 750));
					obstacles.add(new Obstacle(1600-i, 450));
				}
				
				for(int i = 150; i <= 350; i += 50) {
					obstacles.add(new Obstacle(300, i));
					obstacles.add(new Obstacle(1300, 900-i));
				}
				
				for(int i = 450; i <= 650; i += 50) {
					obstacles.add(new Obstacle(550, i));
					obstacles.add(new Obstacle(1050, 900-i));
				}
				break;
			case 16:
				for(int i = 200; i <= 750; i += 50) {
					obstacles.add(new Obstacle(300, i));
					obstacles.add(new Obstacle(1300, 900 - i));
				}
				for(int i = 300; i <= 950; i += 50) {
					obstacles.add(new Obstacle(i, 750));
					obstacles.add(new Obstacle(1600-i, 150));
				}
				break;
			case 17:
				for(int i = 25; i <= 675; i += 50) {
					obstacles.add(new Obstacle(i, 525));
					obstacles.add(new Obstacle(1600-i, 375));
				}
				for(int i = 25; i <= 325; i += 50) {
					obstacles.add(new Obstacle(675, i));
					obstacles.add(new Obstacle(925, 900-i));
				}
				break;
			case 18:
				for(int i = 275; i <= 625; i += 50) {
					obstacles.add(new Obstacle(i, 375));
					obstacles.add(new Obstacle(i, 525));
				}
				for(int i = 25; i <= 325; i += 50) {
					obstacles.add(new Obstacle(800, i));
					obstacles.add(new Obstacle(800, 900-i));
				}
				break;
			case 19:
				for(int i = 25; i <= 1425; i += 50) {
					obstacles.add(new Obstacle(i, 200));
					obstacles.add(new Obstacle(i, 725));
					obstacles.add(new Obstacle(1600-i, 450));
				}
				break;
			case 20:
				break;
		}
		return obstacles;
	}
}
