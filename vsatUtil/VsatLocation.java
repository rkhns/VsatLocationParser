package vsatUtil;

public class VsatLocation
{
    private transient String date;
    private String siteId;
    private transient int beamId;
    private double longitude;
    private double latitude;
	
	public VsatLocation (String date, String siteId, int beamId, double longitude, double latitude) {
		this.beamId = beamId;
		this.date=date;
		this.siteId = siteId;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getDate () {
		return date;
	}
	
	public int getBeamId() {
		return beamId;
	}
}