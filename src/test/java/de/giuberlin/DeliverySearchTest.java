package de.giuberlin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliverySearchTest {

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
            String plan = deliverySearch.plan(initialState, traffic,"BF", true);
            System.out.print(plan);
        }

        @Test
        void withDF() {
            String plan = deliverySearch.plan(initialState, traffic,"DF", true);
            System.out.print(plan);
        }

        @Test
        void withIDS() {
            String plan = deliverySearch.plan(initialState, traffic,"ID", true);
            System.out.print(plan);
        }
    }
}