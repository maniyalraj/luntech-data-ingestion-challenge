package nl.lunatech.BingMaps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Given BingsMaptransformer")
class BingMapsTransformerTest {

    @DisplayName("When converting lat and long are extreme, Then pixel X and Y should be 10")
    @Test
    void latLongToPixelXY() {


        assertEquals(10,
                BingMapsTransformer.LatLongToPixelXY(84.89714695160268, -178.2421875, 3).get(BingMapsTransformer.PIXELX));

        assertEquals(10,
                BingMapsTransformer.LatLongToPixelXY(84.89714695160268, -178.2421875, 3).get(BingMapsTransformer.PIXELY));

    }

    @Test
    void pixelXYToTileXY() {

        assertEquals(0, BingMapsTransformer.PixelXYToTileXY(1, 1).get(BingMapsTransformer.TILEX));
        assertEquals(0, BingMapsTransformer.PixelXYToTileXY(1, 1).get(BingMapsTransformer.TILEY));

        assertEquals(8, BingMapsTransformer.PixelXYToTileXY(2048, 2048).get(BingMapsTransformer.TILEX));
        assertEquals(8, BingMapsTransformer.PixelXYToTileXY(2048, 2048).get(BingMapsTransformer.TILEY));
    }

    @Test
    void tileXYToQuadKey() {
        assertEquals("00", BingMapsTransformer.TileXYToQuadKey(0,0,2));
        assertEquals("000", BingMapsTransformer.TileXYToQuadKey(0,0,3));
        assertEquals("0000", BingMapsTransformer.TileXYToQuadKey(0,0,4));

        assertEquals("33", BingMapsTransformer.TileXYToQuadKey(1024,1024,2));
        assertEquals("333", BingMapsTransformer.TileXYToQuadKey(2048,2048,3));
        assertEquals("3333", BingMapsTransformer.TileXYToQuadKey(4096,4096,4));
    }
}