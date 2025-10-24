package logistica;

public record Point(String name) {
    public static Point fromCenter(Center c) { return new Point(c.name()); }
    public static Point fromWaypoint(Waypoint w) { return new Point(w.name()); }
}
