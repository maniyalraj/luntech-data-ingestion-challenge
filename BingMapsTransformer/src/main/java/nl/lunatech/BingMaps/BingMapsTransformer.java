package nl.lunatech.BingMaps;

import java.util.HashMap;
import java.util.Map;

public class BingMapsTransformer {

    private static final double EARTH_RADIUS = 6378137;
    private static final double MIN_LATITUDE = -85.05112878;
    private static final double MAX_LATITUDE = 85.05112878;
    private static final double MIN_LONGITUDE = -180;
    private static final double MAX_LONGITUDE = 180;

    public static final String PIXELX = "PIXELX";
    public static final String PIXELY = "PIXELY";
    public static final String TILEX = "TILEX";
    public static final String TILEY = "TILEY";


    /// <summary>
    /// Clips a number to the specified minimum and maximum values.
    /// </summary>
    /// <param name="n">The number to clip.</param>
    /// <param name="minValue">Minimum allowable value.</param>
    /// <param name="maxValue">Maximum allowable value.</param>
    /// <returns>The clipped value.</returns>
    private static double Clip(double n, double minValue, double maxValue)
    {
        return Math.min(Math.max(n, minValue), maxValue);
    }

    /// <summary>
    /// Determines the map width and height (in pixels) at a specified level
    /// of detail.
    /// </summary>
    /// <param name="levelOfDetail">Level of detail, from 1 (lowest detail)
    /// to 23 (highest detail).</param>
    /// <returns>The map width and height in pixels.</returns>
    public static int MapSize(int levelOfDetail)
    {
        return 256 << levelOfDetail;
    }


    /// <summary>
    /// Converts a point from latitude/longitude WGS-84 coordinates (in degrees)
    /// into pixel XY coordinates at a specified level of detail.
    /// </summary>
    /// <param name="latitude">Latitude of the point, in degrees.</param>
    /// <param name="longitude">Longitude of the point, in degrees.</param>
    /// <param name="levelOfDetail">Level of detail, from 1 (lowest detail)
    /// to 23 (highest detail).</param>
    /// <param name="pixelX">Output parameter receiving the X coordinate in pixels.</param>
    /// <param name="pixelY">Output parameter receiving the Y coordinate in pixels.</param>
    public static Map<String, Integer> LatLongToPixelXY(double latitude, double longitude, int levelOfDetail){

        Map<String, Integer> result = new HashMap<>();

        result.put(PIXELX, 0);
        result.put(PIXELY, 0);

        latitude = Clip(latitude, MIN_LATITUDE, MAX_LATITUDE);
        longitude = Clip(longitude, MIN_LONGITUDE, MAX_LONGITUDE);

        double x = (longitude + 180) / 360;
        double sinLatitude = Math.sin(latitude * Math.PI / 180);
        double y = 0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);

        int mapSize = MapSize(levelOfDetail);
        int pixelX = (int) Clip(x * mapSize + 0.5, 0, mapSize - 1);
        int pixelY = (int) Clip(y * mapSize + 0.5, 0, mapSize - 1);

        result.put(PIXELX, pixelX);
        result.put(PIXELY, pixelY);

        return result;
    }

    /// <summary>
    /// Converts pixel XY coordinates into tile XY coordinates of the tile containing
    /// the specified pixel.
    /// </summary>
    /// <param name="pixelX">Pixel X coordinate.</param>
    /// <param name="pixelY">Pixel Y coordinate.</param>
    /// <param name="tileX">Output parameter receiving the tile X coordinate.</param>
    /// <param name="tileY">Output parameter receiving the tile Y coordinate.</param>
    public static Map<String, Integer> PixelXYToTileXY(int pixelX, int pixelY)
    {
        int tileX = pixelX / 256;
        int tileY = pixelY / 256;

        Map<String, Integer> result = new HashMap<>();

        result.put(TILEX, tileX);
        result.put(TILEY, tileY);

        return result;
    }

    /// <summary>
    /// Converts tile XY coordinates into a QuadKey at a specified level of detail.
    /// </summary>
    /// <param name="tileX">Tile X coordinate.</param>
    /// <param name="tileY">Tile Y coordinate.</param>
    /// <param name="levelOfDetail">Level of detail, from 1 (lowest detail)
    /// to 23 (highest detail).</param>
    /// <returns>A string containing the QuadKey.</returns>
    public static String TileXYToQuadKey(int tileX, int tileY, int levelOfDetail)
    {
        StringBuilder quadKey = new StringBuilder();
        for (int i = levelOfDetail; i > 0; i--)
        {
            char digit = '0';
            int mask = 1 << (i - 1);
            if ((tileX & mask) != 0)
            {
                digit++;
            }
            if ((tileY & mask) != 0)
            {
                digit++;
                digit++;
            }
            quadKey.append(digit);
        }
        return quadKey.toString();
    }






}
