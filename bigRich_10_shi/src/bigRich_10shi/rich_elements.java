package bigRich_10shi;

import java.awt.Font;

import javax.swing.JLabel;

class playerNum{
	private static int playerNumber = 0;

	public static int getPlayerNumber() {
		return playerNumber;
	}

	public static void setPlayerNumber(int playerNumber) {
		playerNum.playerNumber = playerNumber;
	}
	
}

class mapElements extends JLabel{
	private String owener = "";
	private int elementType =TYPE.START ;
	private int level = 0;
	private int  propType = 0;
	private int minePoints = 0;
	private boolean isFloor = true;
	
	public boolean isFloor() {
		return isFloor;
	}

	public void setFloor(boolean isFloor) {
		this.isFloor = isFloor;
	}

	public int getMinePoints() {
		return minePoints;
	}

	public void setMinePoints(int minePoints) {
		this.minePoints = minePoints;
	}

	public int getPropType() {
		return propType;
	}

	public void setPropType(int propType) {
		this.propType = propType;
	}

	public String getOwener() {
		return owener;
	}

	public void setOwener(String owener) {
		this.owener = owener;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getElementType() {
		return elementType;
	}

	public void setElementType(int elementType) {
		this.elementType = elementType;
	}
}

class Player{
	private String name;
	private String nameCode;
	private int location=0;
	private int money=2000;
	private int points=200;
	private boolean isPoor=false;
	private int colorType = 0;
	private int isJump = 0;
	private int toolBombs=1;
	private int toolRobots=0;
	private int toolRoadBlock=0;
	
	
	public int getToolBombs() {
		return toolBombs;
	}
	public void setToolBombs(int toolBombs) {
		this.toolBombs = toolBombs;
	}
	public int getToolRobots() {
		return toolRobots;
	}
	public void setToolRobots(int toolRobots) {
		this.toolRobots = toolRobots;
	}
	public int getToolRoadBlock() {
		return toolRoadBlock;
	}
	public void setToolRoadBlock(int toolRoadBlock) {
		this.toolRoadBlock = toolRoadBlock;
	}
	public int getIsJump() {
		return isJump;
	}
	public void setIsJump(int isJump) {
		this.isJump = isJump;
	}
	public String getName() {
		return name;
	}
	public int getColorType() {
		return colorType;
	}
	public void setColorType(int colorType) {
		this.colorType = colorType;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameCode() {
		return nameCode;
	}
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public boolean isPoor() {
		return isPoor;
	}
	public void setPoor(boolean isPoor) {
		this.isPoor = isPoor;
	}
	
}
