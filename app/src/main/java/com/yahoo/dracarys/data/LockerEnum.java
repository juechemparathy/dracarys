package com.yahoo.dracarys.data;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jue on 2/16/15.
 */
public class LockerEnum {

    public static enum LendState {
        LEND_REQUESTED(1,"LEND_REQUESTED"),
        LEND_ACCEPTED(2,"LEND_ACCEPTED"),
        LEND_REJECTED(3,"LEND_REJECTED"),
        LEND_RETURNED(4,"LEND_RETURNED");

        private final int status;
        private final String name;

        LendState(final int status, final String name) {
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }
        public String getName() { return name; }
        private static  Map<Integer,LendState> enumMap = new HashMap<Integer, LendState>();
        static {
            for(LendState s : EnumSet.allOf(LendState.class))
                enumMap.put(s.getStatus(), s);
        }
        public static LendState getStatus(int code){
            return enumMap.get(code);
        }
    }


    public static enum DisplayState {
        PRIVATE(0,"PRIVATE"),
        PUBLIC(1,"PUBLIC");
        private final int status;
        private final String name;

        DisplayState(final int status, final String name) {
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }
        public String getName() { return name; }
        private static  Map<Integer,DisplayState> enumMap = new HashMap<Integer, DisplayState>();
        static {
            for(DisplayState s : EnumSet.allOf(DisplayState.class))
                enumMap.put(s.getStatus(), s);
        }
        public static DisplayState getStatus(int code){
            return enumMap.get(code);
        }
    }

    public static enum State {
        PRIVATE(0,"INACTIVE"),
        PUBLIC(1,"ACTIVE");
        private final int status;
        private final String name;

        State(final int status, final String name) {
            this.status = status;
            this.name = name;
        }

        public int getStatus() {
            return status;
        }
        public String getName() { return name; }
        private static  Map<Integer,DisplayState> enumMap = new HashMap<Integer, DisplayState>();
        static {
            for(DisplayState s : EnumSet.allOf(DisplayState.class))
                enumMap.put(s.getStatus(), s);
        }
        public static DisplayState getStatus(int code){
            return enumMap.get(code);
        }
    }

}
