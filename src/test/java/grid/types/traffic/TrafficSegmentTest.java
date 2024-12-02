package grid.types.traffic;

import de.giuberlin.grid.types.traffic.HorizontalTrafficSegment;
import de.giuberlin.grid.types.traffic.TrafficSegment;
import de.giuberlin.grid.types.traffic.VerticalTrafficSegment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrafficSegmentTest {

    @Test
    void setNegativeTrafficFails() {
        TrafficSegment test = new VerticalTrafficSegment();
        assertThrows(IllegalArgumentException.class, () -> test.setTraffic(-1));
    }

    @Test
    void setPositiveTrafficSucceeds() {
        TrafficSegment test = new HorizontalTrafficSegment();
        test.setTraffic(2);

        assertEquals(2, test.getTraffic());
    }
}