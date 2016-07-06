package ws.daley.genealogy.gedcom.structure.util;

public class Counts
{
	private Integer min;
	private Integer max;
	
	public Counts(int _minimum, int _maximum) {
		if (_minimum < 0)
			throw new RuntimeException("minimum("+_minimum+") count must be at least 0.");
		if (_minimum > _maximum)
			throw new RuntimeException("maximum("+_maximum+") count must be at least minimum("+_minimum+") count.");
		this.min = _minimum;
		this.max = _maximum;
	}

	public int getMin() {return this.min.intValue();}
	public void setMin(Integer minimum) {this.min = minimum;}
	public int getMax() {return this.max.intValue();}
	public void setMax(Integer maximum) {this.max = maximum;}
}
