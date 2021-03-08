package mediatek2021;

public class Doc implements Document{
	
	private Object[] data;

	public Doc(Object data[]) {
		this.data=data;
	}
	
	@Override
	public Object[] data() {
		return data;
	}
}
