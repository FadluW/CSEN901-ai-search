package de.giuberlin;

import de.giuberlin.search.strategies.StrategyCode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeliveryPlannerTest {

    @Test
    void withRandomGridMeasured() {
        DeliveryPlanner.withRandomGrid().measureAndExecute();
    }

    @Nested
    class testCase1 {
        String initialState = "5;3;2;1;3,2,3,0;1,2;2,0,1,1;";
        String traffic = "0,1,0,0,3;0,2,0,1,0;1,0,0,0,3;1,1,0,1,1;1,1,1,0,3;1,2,0,2,0;1,2,1,1,1;2,0,1,0,1;2,1,1,1,0;2,1,2,0,1;2,2,1,2,0;2,2,2,1,0;3,0,2,0,3;3,1,2,1,2;3,1,3,0,3;3,2,2,2,0;3,2,3,1,2;4,0,3,0,0;4,1,3,1,0;4,1,4,0,2;4,2,3,2,1;4,2,4,1,2;";

        @Test
        void withGridMeasured() {
            DeliveryPlanner.withGrid(initialState, traffic).measureAndExecute();
        }
    }

    @Nested
    class testCase2 {
        String initialState = "8;4;5;3;4,0,1,3,2,1,0,0,6,2;0,1,3,0,0,2;5,2,7,1;";
        String traffic = "0,1,0,0,2;0,2,0,1,2;0,3,0,2,2;1,0,0,0,2;1,1,0,1,2;1,1,1,0,3;1,2,0,2,1;1,2,1,1,0;1,3,0,3,3;1,3,1,2,3;2,0,1,0,3;2,1,1,1,3;2,1,2,0,2;2,2,1,2,1;2,2,2,1,1;2,3,1,3,0;2,3,2,2,0;3,0,2,0,3;3,1,2,1,3;3,1,3,0,3;3,2,2,2,3;3,2,3,1,1;3,3,2,3,3;3,3,3,2,0;4,0,3,0,3;4,1,3,1,0;4,1,4,0,0;4,2,3,2,0;4,2,4,1,0;4,3,3,3,1;4,3,4,2,2;5,0,4,0,2;5,1,4,1,1;5,1,5,0,1;5,2,4,2,1;5,2,5,1,0;5,3,4,3,2;5,3,5,2,0;6,0,5,0,3;6,1,5,1,2;6,1,6,0,1;6,2,5,2,2;6,2,6,1,2;6,3,5,3,1;6,3,6,2,2;7,0,6,0,3;7,1,6,1,0;7,1,7,0,3;7,2,6,2,3;7,2,7,1,0;7,3,6,3,2;7,3,7,2,3;";

        @Test
        @Disabled
        void withGridMeasured() {
            DeliveryPlanner.withGrid(initialState, traffic).measureAndExecute();
        }

        @Test
        void forBF() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.BF).measureAndExecute();
        }

        @Test
        void forDF() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.DF).measureAndExecute();
        }

        @Test
        void forUC() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.UC).measureAndExecute();
        }

        @Test
        void forID() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.ID).measureAndExecute();
        }

        @Test
        void forAS1() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.AS1).measureAndExecute();
        }

        @Test
        void forAS2() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.AS2).measureAndExecute();
        }

        @Test
        void forGR1() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.GR1).measureAndExecute();
        }

        @Test
        void forGR2() {
            DeliveryPlanner.withGridWithStrategy(initialState, traffic, StrategyCode.GR2).measureAndExecute();
        }
    }

    @Nested
    class testCase3 {
        String initialState = "7;8;3;2;2,1,4,2,1,4;0,3,0,7;4,7,1,3;";
        String traffic = "0,1,0,0,2;0,2,0,1,1;0,3,0,2,3;0,4,0,3,1;0,5,0,4,2;0,6,0,5,0;0,7,0,6,3;1,0,0,0,1;1,1,0,1,2;1,1,1,0,2;1,2,0,2,2;1,2,1,1,1;1,3,0,3,0;1,3,1,2,3;1,4,0,4,3;1,4,1,3,3;1,5,0,5,0;1,5,1,4,0;1,6,0,6,1;1,6,1,5,0;1,7,0,7,1;1,7,1,6,2;2,0,1,0,1;2,1,1,1,2;2,1,2,0,0;2,2,1,2,0;2,2,2,1,3;2,3,1,3,0;2,3,2,2,3;2,4,1,4,2;2,4,2,3,2;2,5,1,5,3;2,5,2,4,1;2,6,1,6,3;2,6,2,5,2;2,7,1,7,1;2,7,2,6,2;3,0,2,0,3;3,1,2,1,1;3,1,3,0,1;3,2,2,2,0;3,2,3,1,1;3,3,2,3,2;3,3,3,2,2;3,4,2,4,0;3,4,3,3,1;3,5,2,5,3;3,5,3,4,3;3,6,2,6,2;3,6,3,5,0;3,7,2,7,1;3,7,3,6,0;4,0,3,0,0;4,1,3,1,3;4,1,4,0,3;4,2,3,2,1;4,2,4,1,0;4,3,3,3,1;4,3,4,2,3;4,4,3,4,2;4,4,4,3,3;4,5,3,5,0;4,5,4,4,2;4,6,3,6,0;4,6,4,5,1;4,7,3,7,0;4,7,4,6,1;5,0,4,0,3;5,1,4,1,0;5,1,5,0,2;5,2,4,2,1;5,2,5,1,3;5,3,4,3,1;5,3,5,2,3;5,4,4,4,2;5,4,5,3,3;5,5,4,5,2;5,5,5,4,0;5,6,4,6,0;5,6,5,5,3;5,7,4,7,1;5,7,5,6,2;6,0,5,0,2;6,1,5,1,2;6,1,6,0,2;6,2,5,2,2;6,2,6,1,1;6,3,5,3,0;6,3,6,2,0;6,4,5,4,0;6,4,6,3,0;6,5,5,5,3;6,5,6,4,1;6,6,5,6,0;6,6,6,5,0;6,7,5,7,2;6,7,6,6,1;";

        @Test
        void withGridMeasured() {
            DeliveryPlanner.withGrid(initialState, traffic).measureAndExecute();
        }
    }
}