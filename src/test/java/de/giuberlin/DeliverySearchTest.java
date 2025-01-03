package de.giuberlin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliverySearchTest {
    boolean visualize = true;

    @Nested
    class testCase1 {
        DeliverySearch deliverySearch;
        String initialState = "5;3;2;1;3,2,3,0;1,2;2,0,1,1;";
        String traffic = "0,1,0,0,3;0,2,0,1,0;1,0,0,0,3;1,1,0,1,1;1,1,1,0,3;1,2,0,2,0;1,2,1,1,1;2,0,1,0,1;2,1,1,1,0;2,1,2,0,1;2,2,1,2,0;2,2,2,1,0;3,0,2,0,3;3,1,2,1,2;3,1,3,0,3;3,2,2,2,0;3,2,3,1,2;4,0,3,0,0;4,1,3,1,0;4,1,4,0,2;4,2,3,2,1;4,2,4,1,2;";

        @BeforeEach
        void setup() {
            deliverySearch = new DeliverySearch();
        }

        @Test
        void withBF() {
            String plan = deliverySearch.plan(initialState, traffic,"BF", visualize);
            System.out.print(plan);
        }

        @Test
        void withDF() {
            String plan = deliverySearch.plan(initialState, traffic,"DF", visualize);
            System.out.print(plan);
        }

        @Test
        void withID() {
            String plan = deliverySearch.plan(initialState, traffic,"ID", visualize);
            System.out.print(plan);
        }

        @Test
        void withUC() {
            String plan = deliverySearch.plan(initialState, traffic,"UC", visualize);
            System.out.print(plan);
        }

        @Test
        void withAS1() {
            String plan = deliverySearch.plan(initialState, traffic,"AS1", visualize);
            System.out.print(plan);
        }

        @Test
        void withAS2() {
            String plan = deliverySearch.plan(initialState, traffic,"AS2", visualize);
            System.out.print(plan);
        }

        @Test
        void withGS1() {
            String plan = deliverySearch.plan(initialState, traffic,"GR1", visualize);
            System.out.print(plan);
        }

        @Test
        void withGS2() {
            String plan = deliverySearch.plan(initialState, traffic,"GR2", visualize);
            System.out.print(plan);
        }
    }

    @Nested
    class testCase2 {
        DeliverySearch deliverySearch;
        String initialState = "8;4;5;3;4,0,1,3,2,1,0,0,6,2;0,1,3,0,0,2;5,2,7,1;";
        String traffic = "0,1,0,0,2;0,2,0,1,2;0,3,0,2,2;1,0,0,0,2;1,1,0,1,2;1,1,1,0,3;1,2,0,2,1;1,2,1,1,0;1,3,0,3,3;1,3,1,2,3;2,0,1,0,3;2,1,1,1,3;2,1,2,0,2;2,2,1,2,1;2,2,2,1,1;2,3,1,3,0;2,3,2,2,0;3,0,2,0,3;3,1,2,1,3;3,1,3,0,3;3,2,2,2,3;3,2,3,1,1;3,3,2,3,3;3,3,3,2,0;4,0,3,0,3;4,1,3,1,0;4,1,4,0,0;4,2,3,2,0;4,2,4,1,0;4,3,3,3,1;4,3,4,2,2;5,0,4,0,2;5,1,4,1,1;5,1,5,0,1;5,2,4,2,1;5,2,5,1,0;5,3,4,3,2;5,3,5,2,0;6,0,5,0,3;6,1,5,1,2;6,1,6,0,1;6,2,5,2,2;6,2,6,1,2;6,3,5,3,1;6,3,6,2,2;7,0,6,0,3;7,1,6,1,0;7,1,7,0,3;7,2,6,2,3;7,2,7,1,0;7,3,6,3,2;7,3,7,2,3;";

        @BeforeEach
        void setup() {
            deliverySearch = new DeliverySearch();
        }

        @Test
        void withBF() {
            String plan = deliverySearch.plan(initialState, traffic,"BF", visualize);
            System.out.print(plan);
        }

        @Test
        void withDF() {
            String plan = deliverySearch.plan(initialState, traffic,"DF", visualize);
            System.out.print(plan);
        }

        @Test
        void withID() {
            String plan = deliverySearch.plan(initialState, traffic,"ID", visualize);
            System.out.print(plan);
        }

        @Test
        void withUC() {
            String plan = deliverySearch.plan(initialState, traffic,"UC", visualize);
            System.out.print(plan);
        }

        @Test
        void withAS1() {
            String plan = deliverySearch.plan(initialState, traffic,"AS1", visualize);
            System.out.print(plan);
        }

        @Test
        void withAS2() {
            String plan = deliverySearch.plan(initialState, traffic,"AS2", visualize);
            System.out.print(plan);
        }

        @Test
        void withGS1() {
            String plan = deliverySearch.plan(initialState, traffic,"GR1", visualize);
            System.out.print(plan);
        }

        @Test
        void withGS2() {
            String plan = deliverySearch.plan(initialState, traffic,"GR2", visualize);
            System.out.print(plan);
        }
    }
}