package flightPlanner.AirportTransaction;

public class Airport {
    private String id;
    private int latitudeDegrees;
    private int latitudeMinutes;
    private int longitudeDegrees;
    private int longitudeMinutes;

    public Airport (String id, int latitudeDegrees, 
    int latitudeMinutes, int longitudeDegrees, int longitudeMinutes) {
        this.latitudeDegrees = latitudeDegrees;
        this.latitudeMinutes = latitudeMinutes;
        this.longitudeDegrees = longitudeDegrees;
        this.longitudeMinutes = longitudeMinutes;
    }

    public String getId() {
        return id;
    }

    public int getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public int getLatitudeMinutes() {
        return latitudeMinutes;
    }

    public int getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public int getLongitudeMinutes() {
        return longitudeMinutes;
    }

    public static float calculateDistance(Airport a1, Airport a2) {
        final float PI_F = 3.1415926535f;
        final float DEG_TO_RAD = 180.0f / PI_F;
        final float EARTH_RADIUS =  3963.0f;
        float lat1 = (float)a1.latitudeDegrees + (float)a1.latitudeMinutes / 60.0f;
        lat1 /= DEG_TO_RAD;
        float long1 = -(float)a1.longitudeDegrees + (float)a1.longitudeMinutes / 60.0f;
        long1 /= DEG_TO_RAD;
        float lat2 = (float)a2.latitudeDegrees + (float)a2.latitudeMinutes / 60.0f;
        lat2 /= DEG_TO_RAD;
        float long2 = -(float)a2.longitudeDegrees + (float)a2.longitudeMinutes / 60.0f;

        float x = (float)(
            (Math.sin(lat1) * Math.sin(lat2)) + 
            (Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1))
        );
        float x2 = (float)(Math.sqrt(1.0 - (x*x)) / x);
        float distance = (float)(EARTH_RADIUS * Math.atan(x2));
        return distance;
    }
}
