package nth.meyn.cx.sysmac.converter.cx.ladder.model;

public class CxLocation {
private final int x,y;

public CxLocation(int x, int y) {
	this.x = x;
	this.y = y;
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

@Override
	public boolean equals(Object obj) {
	  
		if (this == obj) {
          return true;
      }
	  
      if (obj instanceof CxLocation) {
          CxLocation location=(CxLocation) obj;
              return location.getX()==x && location.getY()==y;
      }
      return false;
	}

@Override
	public int hashCode() {
		int hashCode = x+y*100000;
		return hashCode;
	}

@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
